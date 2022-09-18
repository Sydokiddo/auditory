package net.sydokiddo.auditory.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand interactionHand);

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private static void playSwordSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AuditorySounds.ITEM_SWORD_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playAxeSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AuditorySounds.ITEM_AXE_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playToolSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AuditorySounds.ITEM_TOOL_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private static void playLightweightToolSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), AuditorySounds.ITEM_TOOL_SWING.get(), SoundSource.PLAYERS, 0.1F, 1.4f + entity.level.random.nextFloat() * 0.8F);
    }

    @Unique
    private static void playEmptyHandSounds(Entity entity) {
        entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, SoundSource.PLAYERS, 0.1F, 0.8f + entity.level.random.nextFloat() * 0.4F);
    }

    @Inject(method = "swing(Lnet/minecraft/world/InteractionHand;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getChunkSource()Lnet/minecraft/server/level/ServerChunkCache;", shift = At.Shift.AFTER))
    public void swing(InteractionHand interactionHand, boolean bl, CallbackInfo ci) {
        ItemStack itemStack = this.getItemInHand(interactionHand);

        // Sword Sounds
        if (itemStack.getItem() instanceof SwordItem && Auditory.CLIENT_CONFIG.swordSwingingSounds.get()) {
            playSwordSounds(this);
        }

        // Axe Sounds
        if (itemStack.getItem() instanceof AxeItem && Auditory.CLIENT_CONFIG.axeSwingingSounds.get()) {
            playAxeSounds(this);
        }

        // Trident Sounds
        if (itemStack.getItem() instanceof TridentItem && Auditory.CLIENT_CONFIG.tridentSwingingSounds.get()) {
            playSwordSounds(this);
        }

        // Pickaxe Sounds
        if (itemStack.getItem() instanceof PickaxeItem && Auditory.CLIENT_CONFIG.pickaxeSwingingSounds.get()) {
            playToolSounds(this);
        }

        // Shovel Sounds
        if (itemStack.getItem() instanceof ShovelItem && Auditory.CLIENT_CONFIG.shovelSwingingSounds.get()) {
            playToolSounds(this);
        }

        // Hoe Sounds
        if (itemStack.getItem() instanceof HoeItem && Auditory.CLIENT_CONFIG.hoeSwingingSounds.get()) {
            playToolSounds(this);
        }

        // Shear Sounds
        if (itemStack.getItem() instanceof ShearsItem && Auditory.CLIENT_CONFIG.shearSwingingSounds.get()) {
            playLightweightToolSounds(this);
        }

        // Empty Hand Sounds
        if (itemStack.isEmpty() && Auditory.CLIENT_CONFIG.fistSwingingSounds.get()) {
            playEmptyHandSounds(this);
        }
    }
}
