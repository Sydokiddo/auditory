package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MinecartItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever a minecart is placed

@Environment(EnvType.CLIENT)
@Mixin(MinecartItem.class)
public class MinecartPlaceSoundMixin
{
    @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/util/TypedActionResult;use(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"), method = "useOnBlock")
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isIn(BlockTags.RAILS)) {
            MinecraftClient client = MinecraftClient.getInstance();
            assert client.player != null;
            client.player.playSound(ModSoundEvents.ENTITY_MINECART_PLACE, SoundCategory.BLOCKS, 0.8F, 1.0F);
        }
    }
}