package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Map;

// Plays a sound when a plant is put into a flower pot

@Mixin(FlowerPotBlock.class)
public abstract class FlowerPotPlantSoundMixin {

    @Shadow
    @Final
    private static Map<Block, Block> POTTED_BY_CONTENT;

    @Shadow
    protected abstract boolean isEmpty();

    @Shadow
    @Final
    private Block content;

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
            ItemStack itemStack = player.getItemInHand(interactionHand);
            Item item = itemStack.getItem();
            BlockState blockState2 = (item instanceof BlockItem ? POTTED_BY_CONTENT.getOrDefault(((BlockItem) item).getBlock(), Blocks.AIR) : Blocks.AIR).defaultBlockState();
            boolean bl = blockState2.is(Blocks.AIR);
            boolean bl2 = this.isEmpty();
            if (bl != bl2) {
                if (bl2) {
                    level.setBlock(blockPos, blockState2, 3);
                    player.awardStat(Stats.POT_FLOWER);
                    if (!player.getAbilities().instabuild) {
                        itemStack.shrink(1);
                    }
                } else {
                    ItemStack itemStack2 = new ItemStack(this.content);
                    if (itemStack.isEmpty()) {
                        player.setItemInHand(interactionHand, itemStack2);
                    } else if (!player.addItem(itemStack2)) {
                        player.drop(itemStack2, false);
                    }

                    level.setBlock(blockPos, Blocks.FLOWER_POT.defaultBlockState(), 3);
                }

                level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos);
                if (Auditory.getConfig().block_sounds.flower_pot_sounds) {
                    level.playSound(player, blockPos, SoundEvents.HANGING_ROOTS_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
                }
                cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
            } else {
                cir.setReturnValue(InteractionResult.CONSUME);
            }
        }
    }