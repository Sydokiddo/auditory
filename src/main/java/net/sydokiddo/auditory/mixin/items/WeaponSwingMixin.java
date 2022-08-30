package net.sydokiddo.auditory.mixin.items;

import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Weapon Swinging Sounds

@Mixin(LivingEntity.class)
public abstract class WeaponSwingMixin extends Entity {

    @Shadow public boolean swinging;

    @Shadow public int swingTime;

    @Shadow protected abstract int getCurrentSwingDuration();

    @Shadow public InteractionHand swingingArm;

    @Shadow public abstract ItemStack getItemInHand(InteractionHand interactionHand);

    public WeaponSwingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static void playSwordSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.ITEM_SWORD_SWING, SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playAxeSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.ITEM_AXE_SWING, SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playToolSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.ITEM_TOOL_SWING, SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playLightweightToolSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.ITEM_TOOL_SWING, SoundSource.PLAYERS, 0.1F, 1.4f + entity.level.random.nextFloat() * 0.8F);
    }

    @Unique
    private static void playEmptyHandSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Inject(at = @At("HEAD"), method = "swing(Lnet/minecraft/world/InteractionHand;Z)V")
    public void swing(InteractionHand interactionHand, boolean bl, CallbackInfo ci) {
        if (!this.swinging || this.swingTime >= this.getCurrentSwingDuration() / 2 || this.swingTime < 0) {
            this.swingTime = -1;
            this.swinging = true;
            this.swingingArm = interactionHand;
            ItemStack itemStack = this.getItemInHand(interactionHand);
            if (this.level instanceof ServerLevel) {
                ClientboundAnimatePacket clientboundAnimatePacket = new ClientboundAnimatePacket(this, interactionHand == InteractionHand.MAIN_HAND ? 0 : 3);
                ServerChunkCache serverChunkCache = ((ServerLevel) this.level).getChunkSource();

                // Sword Sounds

                if (itemStack.getItem() instanceof SwordItem && Auditory.getConfig().weapon_sounds.sword_swinging_sounds) {
                    playSwordSounds(this);
                }

                // Axe Sounds

                if (itemStack.getItem() instanceof AxeItem && Auditory.getConfig().weapon_sounds.axe_swinging_sounds) {
                    playAxeSounds(this);
                }

                // Trident Sounds

                if (itemStack.getItem() instanceof TridentItem && Auditory.getConfig().weapon_sounds.trident_swinging_sounds) {
                    playSwordSounds(this);
                }

                // Pickaxe Sounds

                if (itemStack.getItem() instanceof PickaxeItem && Auditory.getConfig().weapon_sounds.pickaxe_swinging_sounds) {
                    playToolSounds(this);
                }

                // Shovel Sounds

                if (itemStack.getItem() instanceof ShovelItem && Auditory.getConfig().weapon_sounds.shovel_swinging_sounds) {
                    playToolSounds(this);
                }

                // Hoe Sounds

                if (itemStack.getItem() instanceof HoeItem && Auditory.getConfig().weapon_sounds.hoe_swinging_sounds) {
                    playToolSounds(this);
                }

                // Shear Sounds

                if (itemStack.getItem() instanceof ShearsItem && Auditory.getConfig().weapon_sounds.shear_swinging_sounds) {
                    playLightweightToolSounds(this);
                }

                // Empty Hand Sounds

                if (itemStack.isEmpty() && Auditory.getConfig().weapon_sounds.fist_swinging_sounds) {
                    playEmptyHandSounds(this);
                }

                if (bl) {
                    serverChunkCache.broadcastAndSend(this, clientboundAnimatePacket);
                } else {
                    serverChunkCache.broadcast(this, clientboundAnimatePacket);
                }
            }
        }
    }
}
