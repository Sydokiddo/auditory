package net.sydokiddo.auditory.mixin.entities;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Plays a sound to the player when they teleport with an Ender Pearl

@Mixin(ThrownEnderpearl.class)
abstract class EnderPearlSoundMixin extends ThrowableItemProjectile {

    public EnderPearlSoundMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onHit", at = @At("HEAD"))
    protected void onHit(HitResult hitResult, CallbackInfo ci) {
        if (Auditory.getConfig().item_use_sounds && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayer serverPlayer) {
                if (serverPlayer.connection.getConnection().isConnected() && serverPlayer.level == this.level && !serverPlayer.isSleeping()) {
                    if (this.random.nextFloat() < 0.05F && this.level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING)) {
                        Endermite endermite = EntityType.ENDERMITE.create(this.level);
                        assert endermite != null;
                        endermite.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        this.level.addFreshEntity(endermite);
                    }
                    if (entity.isPassenger()) {
                        serverPlayer.dismountTo(this.getX(), this.getY(), this.getZ());
                    } else {
                        entity.teleportTo(this.getX(), this.getY(), this.getZ());
                        ((Player) entity).playNotifySound(SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 0.8f, 1.0f);
                    }
                    entity.resetFallDistance();
                    entity.hurt(DamageSource.FALL, 5.0F);
                }
            } else if (entity != null) {
                entity.teleportTo(this.getX(), this.getY(), this.getZ());
                ((Player) entity).playNotifySound(SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 0.8f, 1.0f);
                entity.resetFallDistance();
            }
            this.discard();
        }
    }

    @Override
    public Item getDefaultItem() {
        return Items.ENDER_PEARL;
    }
}