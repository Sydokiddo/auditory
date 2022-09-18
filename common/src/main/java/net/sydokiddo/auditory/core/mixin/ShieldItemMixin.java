package net.sydokiddo.auditory.core.mixin;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {

    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;startUsingItem(Lnet/minecraft/world/InteractionHand;)V"))
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (Auditory.CLIENT_CONFIG.shieldBlockingSounds.get()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), AuditorySounds.ITEM_SHIELD_RAISE.get(), SoundSource.PLAYERS, 0.1F, 0.8f + player.level.random.nextFloat() * 0.4F);
        }
    }
}
