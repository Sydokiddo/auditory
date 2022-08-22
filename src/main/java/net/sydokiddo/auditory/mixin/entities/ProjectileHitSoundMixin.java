package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public class ProjectileHitSoundMixin {

    @Shadow @Final protected SoundType soundType;

    @Inject(at = @At("TAIL"), method = "onProjectileHit")
    @Deprecated
    public void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile, CallbackInfo ci) {
        if (!level.isClientSide && Auditory.getConfig().misc_sounds.projectile_hit_sounds) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            SoundEvent hitSound = soundType.getHitSound();
            level.playSound(null, blockPos, hitSound, SoundSource.BLOCKS, 0.1F, 0.5F + level.random.nextFloat() * 1.2F);
        }
    }
}
