package net.sydokiddo.auditory.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever the player drops an item

@Mixin(PlayerEntity.class)
class ItemDropSoundMixin
{
    @Inject(at = @At("TAIL"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;")
    private void dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir)
    {
        if (retainOwnership && !stack.isEmpty())
        {
            float range = 0.1f;
            float n = 1f + range *
                    (1f * stack.getItem().getMaxCount() / stack.getCount()) - range / 2;
            MinecraftClient client = MinecraftClient.getInstance();
            client.player.playSound(ModSoundEvents.ENTITY_PLAYER_DROP_ITEM, SoundCategory.PLAYERS, 0.1F, 1.0F);
        }
    }
}