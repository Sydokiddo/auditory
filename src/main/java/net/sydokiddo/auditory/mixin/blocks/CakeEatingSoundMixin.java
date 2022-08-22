package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays the eating sound when a cake is eaten

@Mixin(CakeBlock.class)
public abstract class CakeEatingSoundMixin extends Block {

    @Shadow @Final public static IntegerProperty BITES;

    public CakeEatingSoundMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "eat", at = @At("HEAD"), cancellable = true)
    private static void eat(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        if (!player.canEat(false)) {
            cir.setReturnValue(InteractionResult.PASS);
        } else {
            player.awardStat(Stats.EAT_CAKE_SLICE);
            if (Auditory.getConfig().block_sounds.cake_eating_sounds) {
                levelAccessor.playSound(player, blockPos, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 1.0f, 0.8f + levelAccessor.getRandom().nextFloat() * 0.4F);
            }
            player.getFoodData().eat(2, 0.1F);
            int i = blockState.getValue(BITES);
            levelAccessor.gameEvent(player, GameEvent.EAT, blockPos);
            if (i < 6) {
                levelAccessor.setBlock(blockPos, blockState.setValue(BITES, i + 1), 3);
            } else {
                levelAccessor.removeBlock(blockPos, false);
                levelAccessor.gameEvent(player, GameEvent.BLOCK_DESTROY, blockPos);
            }

            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}
