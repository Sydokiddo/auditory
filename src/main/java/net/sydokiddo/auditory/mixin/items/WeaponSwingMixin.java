package net.sydokiddo.auditory.mixin.items;

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

    @Shadow public abstract ItemStack getItemInHand(InteractionHand interactionHand);

    public WeaponSwingMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Unique
    private void auditory_playSwordSounds() {
        level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSoundEvents.ITEM_SWORD_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + this.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private void auditory_playAxeSounds() {
        level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSoundEvents.ITEM_AXE_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + this.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private void auditory_playToolSounds() {
        level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSoundEvents.ITEM_TOOL_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + this.level.random.nextFloat() * 0.4F);
    }

    @Unique
    private void auditory_playLightweightToolSounds() {
        level.playSound(null, this.getX(), this.getY(), this.getZ(), ModSoundEvents.ITEM_TOOL_SWING.get(), SoundSource.PLAYERS, 0.1F, 1.4f + this.level.random.nextFloat() * 0.8F);
    }

    @Unique
    private void auditory_playEmptyHandSounds() {
        level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, SoundSource.PLAYERS, 0.1F, 0.8f + this.level.random.nextFloat() * 0.4F);
    }

    @Inject(
        at = @At(value = "NEW",
            target = "net/minecraft/network/protocol/game/ClientboundAnimatePacket"),
        method = "swing(Lnet/minecraft/world/InteractionHand;Z)V"
    )
    private void auditory_swingSounds(InteractionHand interactionHand, boolean bl, CallbackInfo ci) {
        ItemStack itemStack = this.getItemInHand(interactionHand);

        // TODO Fabric tool tags support

        // Sword Sounds

        if (itemStack.getItem() instanceof SwordItem && Auditory.getConfig().weapon_sounds.sword_swinging_sounds) {
            auditory_playSwordSounds();
        }

        // Axe Sounds

        else if (itemStack.getItem() instanceof AxeItem && Auditory.getConfig().weapon_sounds.axe_swinging_sounds) {
            auditory_playAxeSounds();
        }

        // Trident Sounds

        else if (itemStack.getItem() instanceof TridentItem && Auditory.getConfig().weapon_sounds.trident_swinging_sounds) {
            auditory_playSwordSounds();
        }

        // Pickaxe Sounds

        else if (itemStack.getItem() instanceof PickaxeItem && Auditory.getConfig().weapon_sounds.pickaxe_swinging_sounds) {
            auditory_playToolSounds();
        }

        // Shovel Sounds

        else if (itemStack.getItem() instanceof ShovelItem && Auditory.getConfig().weapon_sounds.shovel_swinging_sounds) {
            auditory_playToolSounds();
        }

        // Hoe Sounds

        else if (itemStack.getItem() instanceof HoeItem && Auditory.getConfig().weapon_sounds.hoe_swinging_sounds) {
            auditory_playToolSounds();
        }

        // Shear Sounds

        else if (itemStack.getItem() instanceof ShearsItem && Auditory.getConfig().weapon_sounds.shear_swinging_sounds) {
            auditory_playLightweightToolSounds();
        }

        // Empty Hand Sounds

        else if (itemStack.isEmpty() && Auditory.getConfig().weapon_sounds.fist_swinging_sounds) {
            auditory_playEmptyHandSounds();
        }
    }
}