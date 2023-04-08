package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class FallOnBlockMixin {

    // Plays block stepping sound when entities fall onto any block while not crouching

    @Inject(method = "fallOn", at = @At("RETURN"))
    private void auditory_fallSound(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f, CallbackInfo ci) {
        if (Auditory.getConfig().block_sounds.falling_in_place_sound) {
            if (!entity.isCrouching() && entity instanceof LivingEntity && entity.getDeltaMovement().horizontalDistance() >= 0) {
                if (!blockState.isAir() && !blockState.is(BlockTags.FIRE) && !blockState.is(BlockTags.PORTALS) && (!blockState.getMaterial().isLiquid())) {
                    SoundType soundType = blockState.getSoundType();
                    level.playSound(null, blockPos, soundType.getStepSound(), SoundSource.BLOCKS, soundType.getVolume() * 0.15F, soundType.getPitch());
                }
            }
        }
    }
}