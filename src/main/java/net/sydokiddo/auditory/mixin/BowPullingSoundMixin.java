package net.sydokiddo.auditory.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever the player uses a bow

@Mixin(BowItem.class)
public class BowPullingSoundMixin {

    private static int wait = 0;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/TypedActionResult;consume(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"), method = "use")
    void pull(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir)
    {
        if (wait <= 0) {
            wait = 1; // Waits about a second before playing the sound again
            MinecraftClient client = MinecraftClient.getInstance();
            assert client.player != null;
            client.player.playSound(ModSoundEvents.ITEM_BOW_PULLING, SoundCategory.PLAYERS, 0.3F, 1.2F);
        }
        else {
            wait--; // Decreases the wait timer
            if (wait < 0) wait = 0; // Sets the wait timer back to 0 if it goes below 0
        }
    }
}