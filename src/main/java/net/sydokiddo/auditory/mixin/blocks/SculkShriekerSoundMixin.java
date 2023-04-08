package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SculkShriekerBlock.class)
public abstract class SculkShriekerSoundMixin extends BaseEntityBlock implements SimpleWaterloggedBlock {

    @Shadow @Final public static BooleanProperty CAN_SUMMON;

    @Shadow @Final public static BooleanProperty SHRIEKING;

    protected SculkShriekerSoundMixin(Properties properties) {
        super(properties);
    }

    // Sculk Shriekers that can summon Wardens now make subtle ambient sounds

    @Override
    public void animateTick(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, RandomSource randomSource) {
        if (Auditory.getConfig().block_sounds.sculk_shrieker_ambient_sounds) {
            if (blockState.getValue(CAN_SUMMON) && !blockState.getValue(SHRIEKING) && (randomSource.nextInt(10) == 0)) {
                level.playLocalSound((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.5D, (double) blockPos.getZ() + 0.5D, ModSoundEvents.BLOCK_SCULK_SHRIEKER_AMBIENT, SoundSource.BLOCKS, 0.5F, randomSource.nextFloat() * 0.4F + 0.8F, false);
            }
        }
    }
}
