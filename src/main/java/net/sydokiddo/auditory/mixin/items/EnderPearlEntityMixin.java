package net.sydokiddo.auditory.mixin.items;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Plays a sound to the player when they teleport with an Ender Pearl

@Mixin(ThrownEnderpearl.class)
abstract class EnderPearlSoundMixin extends ThrowableItemProjectile {

    public EnderPearlSoundMixin(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "onHit",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;teleportTo(DDD)V",
            shift = Shift.AFTER
        )
    )
    private void auditory_teleportSound(HitResult hitResult, CallbackInfo ci) {
        if (Auditory.getConfig().item_sounds.ender_pearl_sounds &&
            (!FabricLoader.getInstance().isModLoaded("endlessencore")) &&
            this.getOwner() instanceof Player player) {

                player.playNotifySound(SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 0.8f, 1.0f);
        }
    }
}