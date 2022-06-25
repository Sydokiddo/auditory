package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever the player drops an item

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)

public abstract class ItemDropSoundMixin {

    private static int wait = 0;

    @Inject(at = @At("TAIL"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;")
    private void dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {

        if (Auditory.getConfig().item_drop_sounds) {

            if (retainOwnership && !stack.isEmpty() && (wait <= 0)) {

                wait = 1; // Waits about a second before playing the sound again
                MinecraftClient client = MinecraftClient.getInstance();
                assert client.player != null;
                client.player.playSound(ModSoundEvents.ENTITY_PLAYER_DROP_ITEM, SoundCategory.PLAYERS, 0.3F, 1.0F);
            } else {
                wait--; // Decreases the wait timer
                if (wait < 0) wait = 0; // Sets the wait timer back to 0 if it goes below 0
            }
        }
    }
}