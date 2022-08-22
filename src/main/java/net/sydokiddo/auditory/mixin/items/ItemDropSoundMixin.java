package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
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

    @Inject(method = "drop(Lnet/minecraft/world/item/ItemStack;ZZ)Lnet/minecraft/world/entity/item/ItemEntity;", at = @At("HEAD"), cancellable = true)
    public void drop(ItemStack itemStack, boolean bl, boolean bl2, CallbackInfoReturnable<ItemEntity> cir) {
        if (itemStack.isEmpty()) {
            cir.setReturnValue(null);
        } else {
            if (this.level.isClientSide) {
                this.swing(InteractionHand.MAIN_HAND);
            }

            double d = this.getEyeY() - 0.30000001192092896D;
            ItemEntity itemEntity = new ItemEntity(this.level, this.getX(), d, this.getZ(), itemStack);
            itemEntity.setPickUpDelay(40);
            if (bl2) {
                itemEntity.setThrower(this.getUUID());
            }

            float f;
            float g;
            if (bl) {
                f = this.random.nextFloat() * 0.5F;
                g = this.random.nextFloat() * 6.2831855F;
                itemEntity.setDeltaMovement(-Mth.sin(g) * f, 0.20000000298023224D, Mth.cos(g) * f);
            } else {
                g = Mth.sin(this.getXRot() * 0.017453292F);
                float h = Mth.cos(this.getXRot() * 0.017453292F);
                float i = Mth.sin(this.getYRot() * 0.017453292F);
                float j = Mth.cos(this.getYRot() * 0.017453292F);
                float k = this.random.nextFloat() * 6.2831855F;
                float l = 0.02F * this.random.nextFloat();
                itemEntity.setDeltaMovement((double)(-i * h * 0.3F) + Math.cos(k) * (double)l, -g * 0.3F + 0.1F + (this.random.nextFloat() - this.random.nextFloat()) * 0.1F, (double)(j * h * 0.3F) + Math.sin(k) * (double)l);
            }

            this.playNotifySound(ModSoundEvents.ENTITY_PLAYER_DROP_ITEM, SoundSource.PLAYERS, 0.4F, 1.0F  + level.random.nextFloat() * 0.4F);
            cir.setReturnValue(itemEntity);
        }
    }
}
