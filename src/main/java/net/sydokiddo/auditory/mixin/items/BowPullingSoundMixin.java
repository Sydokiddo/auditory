package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a pulling back sound whenever a bow is charging

@Mixin(BowItem.class)
public class BowPullingSoundMixin {

    @Inject(at = @At("TAIL"), method = "use")
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
            player.playNotifySound(ModSoundEvents.ITEM_BOW_PULLING, SoundSource.PLAYERS, 0.3f, 1.2f + level.random.nextFloat() * 0.4F);
        }
    }
