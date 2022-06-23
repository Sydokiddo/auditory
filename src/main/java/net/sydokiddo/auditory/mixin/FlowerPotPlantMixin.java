package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever a plant is inserted into a Flower Pot

@Environment(EnvType.CLIENT)
@Mixin(FlowerPotBlock.class)

public class FlowerPotPlantMixin {

    public final Block content;

    public FlowerPotPlantMixin(Block content) {
        this.content = content;
    }

    private boolean isEmpty() {
        return this.content == Blocks.AIR;
    }

    @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/util/TypedActionResult;use(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"), method = "onUse")
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        boolean bl2 = this.isEmpty();
            if (bl2) {
                client.player.playSound(SoundEvents.BLOCK_HANGING_ROOTS_PLACE, SoundCategory.BLOCKS, 0.8F, 1.0F);
            }
        }
    }