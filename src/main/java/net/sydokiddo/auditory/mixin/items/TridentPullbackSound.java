package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public abstract class TridentPullbackSound {

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack itemStack = player.getItemInHand(interactionHand);

        if (itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1) {
            cir.setReturnValue(InteractionResultHolder.fail(itemStack));

        } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && !player.isInWaterOrRain()) {
            cir.setReturnValue(InteractionResultHolder.fail(itemStack));

        } else if (EnchantmentHelper.getRiptide(itemStack) > 0 && player.isInWaterOrRain()) {

            if (Auditory.getConfig().item_sounds.bow_and_trident_pullback_sounds) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.ITEM_TRIDENT_SWING, SoundSource.PLAYERS, 0.1F, 0.8f + player.level.random.nextFloat() * 0.4F);
            }

        } else {
            player.startUsingItem(interactionHand);

            if (Auditory.getConfig().item_sounds.bow_and_trident_pullback_sounds) {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.ITEM_TRIDENT_SWING, SoundSource.PLAYERS, 0.1F, 0.8f + player.level.random.nextFloat() * 0.4F);
            }

            cir.setReturnValue(InteractionResultHolder.consume(itemStack));
        }
    }
}
