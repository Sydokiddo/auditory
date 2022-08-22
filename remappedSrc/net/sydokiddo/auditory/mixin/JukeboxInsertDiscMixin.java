package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Plays a sound whenever a Music Disc is inserted into a Jukebox

@Environment(EnvType.CLIENT)
@Mixin(JukeboxBlock.class)
public class JukeboxInsertDiscMixin
{
    @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/util/TypedActionResult;use(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"), method = "setRecord")
    public void setRecord(Entity user, WorldAccess world, BlockPos pos, BlockState state, ItemStack stack, CallbackInfo ci) {
        if (Auditory.getConfig().interaction_sounds) {
            MinecraftClient client = MinecraftClient.getInstance();
            assert client.player != null;
            client.player.playSound(ModSoundEvents.BLOCK_JUKEBOX_USE, SoundCategory.BLOCKS, 0.8F, 1.0F);
        }
    }
}