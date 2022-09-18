package net.sydokiddo.auditory.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.common.block.BlockSoundOverrides;
import net.sydokiddo.auditory.core.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Shadow
    public abstract SoundType getSoundType(BlockState blockState);

    // Block sound overrides, automatically sets the new sound if any match is found in the registry map.
    @Inject(method = "getSoundType", at = @At("HEAD"), cancellable = true)
    public void getSoundType(BlockState blockState, CallbackInfoReturnable<SoundType> cir) {
        if (Auditory.CLIENT_CONFIG.blockSounds.get()) {
            BlockSoundOverrides.REGISTRY.keySet().forEach(t -> {
                if (blockState.is(t))
                    cir.setReturnValue(BlockSoundOverrides.REGISTRY.get(t));
            });
        }
    }

    // Plays block stepping sound when entities fall onto any block while not crouching
    @Inject(method = "fallOn", at = @At("RETURN"))
    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, float f, CallbackInfo ci) {
        if (!entity.isCrouching() && entity instanceof LivingEntity) {
            level.playSound(null, blockPos, this.getSoundType(blockState).getStepSound(), SoundSource.BLOCKS, 0.1f, 1.0f);
        }
    }
}
