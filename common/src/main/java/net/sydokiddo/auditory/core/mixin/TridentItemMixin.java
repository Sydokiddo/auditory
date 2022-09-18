package net.sydokiddo.auditory.core.mixin;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TridentItem.class)
public class TridentItemMixin {

    @Inject(at = @At("HEAD"), method = "use")
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (Auditory.CLIENT_CONFIG.tridentPullbackSounds.get()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), AuditorySounds.ITEM_TRIDENT_SWING.get(), SoundSource.PLAYERS, 0.1F, 0.8f + player.level.random.nextFloat() * 0.4F);
        }
    }
}
