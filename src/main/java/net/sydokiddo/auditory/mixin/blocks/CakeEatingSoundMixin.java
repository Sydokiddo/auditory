package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays the eating sound when a cake is eaten

@Mixin(CakeBlock.class)
public abstract class CakeEatingSoundMixin extends Block {

    public CakeEatingSoundMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "eat", at = @At("RETURN"))
    private static void eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        if (player.canEat(false)) {
            if (Auditory.getConfig().block_sounds.cake_eating_sounds) {
                levelAccessor.playSound(player, blockPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0f, 0.8f + levelAccessor.getRandom().nextFloat() * 0.4F);
            }
        }
    }
}
