package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class FallOnBlockMixin {

    // Plays block stepping sound when entities fall onto any block while not crouching

    @Shadow public abstract SoundType getSoundType(BlockState blockState);

    @Inject(method = "fallOn", at = @At("RETURN"))
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f, CallbackInfo ci) {
        if (!entity.isCrouching() && entity instanceof LivingEntity) {
            level.playSound(null, blockPos, this.getSoundType(blockState).getStepSound(), SoundSource.BLOCKS, 0.1f, 1.0f);
        }
    }
}