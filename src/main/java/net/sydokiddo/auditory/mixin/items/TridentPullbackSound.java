package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public abstract class TridentPullbackSound {

    @Inject(method = "use", at = @At(value = "RETURN", target = "Lnet/minecraft/world/entity/player/Player;startUsingItem(Lnet/minecraft/world/InteractionHand;)V"))
    private void auditory_pullbackSound(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (Auditory.getConfig().weapon_sounds.trident_pullback_sounds) {
            level.playSound(null, player.getOnPos(), ModSoundEvents.ITEM_TRIDENT_PULLING, SoundSource.PLAYERS, 0.1F, 0.8f + player.level().random.nextFloat() * 0.4F);
        }
    }
}
