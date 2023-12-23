package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a plopping sound when the player drops an item

@Mixin(Player.class)
public abstract class ItemDropSoundMixin extends LivingEntity {

    @Shadow public abstract void playNotifySound(SoundEvent soundEvent, SoundSource soundSource, float f, float g);

    protected ItemDropSoundMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    // TAIL injects before the last return
    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("TAIL"))
    private void auditory_itemDropSound(ItemStack itemStack, boolean bl, boolean bl2, CallbackInfoReturnable<ItemEntity> cir) {
        if (!this.isDeadOrDying() && !this.isRemoved() && Auditory.getConfig().misc_sounds.item_drop_sounds) {
            this.playNotifySound(ModSoundEvents.ENTITY_PLAYER_DROP_ITEM, SoundSource.PLAYERS, 0.4F, 1.0F + level().random.nextFloat() * 0.4F);
        }
    }
}
