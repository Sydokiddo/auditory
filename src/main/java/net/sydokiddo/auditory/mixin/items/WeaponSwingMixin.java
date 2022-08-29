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
                ServerChunkCache serverChunkCache = ((ServerLevel)this.level).getChunkSource();

                if (Auditory.getConfig().item_sounds.weapon_swing_sounds) {

                    // Sword Sounds

                    if (itemStack.getItem() instanceof SwordItem) {
                        playSwordSounds(this);
                    }

                    // Axe Sounds

                    if (itemStack.getItem() instanceof AxeItem) {
                        playAxeSounds(this);
                    }

                    // Trident Sounds

                    if (itemStack.getItem() instanceof TridentItem) {
                        playSwordSounds(this);
                    }

                    // Pickaxe Sounds

                    if (itemStack.getItem() instanceof PickaxeItem) {
                        playToolSounds(this);
                    }

                    // Shovel Sounds

                    if (itemStack.getItem() instanceof ShovelItem) {
                        playToolSounds(this);
                    }

                    // Hoe Sounds

                    if (itemStack.getItem() instanceof HoeItem) {
                        playToolSounds(this);
                    }

                    // Shear Sounds

                    if (itemStack.getItem() instanceof ShearsItem) {
                        playLightweightToolSounds(this);
                    }

                    // Empty Hand Sounds

                    if (itemStack.isEmpty()) {
                        playEmptyHandSounds(this);
                    }

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
