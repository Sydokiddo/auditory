package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever a minecart is placed

@Mixin(MinecartItem.class)
public class MinecartPlaceSoundMixin {

    @Shadow @Final
    AbstractMinecart.Type type;

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    public void useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        if (!blockState.is(BlockTags.RAILS)) {
            cir.setReturnValue(InteractionResult.FAIL);
        } else {
            ItemStack itemStack = useOnContext.getItemInHand();
            if (!level.isClientSide) {
                RailShape railShape = blockState.getBlock() instanceof BaseRailBlock ? blockState.getValue(((BaseRailBlock)blockState.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
                double d = 0.0D;
                if (railShape.isAscending()) {
                    d = 0.5D;
                }

                AbstractMinecart abstractMinecart = AbstractMinecart.createMinecart(level, (double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.0625D + d, (double)blockPos.getZ() + 0.5D, this.type);
                if (itemStack.hasCustomHoverName()) {
                    abstractMinecart.setCustomName(itemStack.getHoverName());
                }

                level.addFreshEntity(abstractMinecart);
                level.gameEvent(GameEvent.ENTITY_PLACE, blockPos, GameEvent.Context.of(useOnContext.getPlayer(), level.getBlockState(blockPos.below())));
                level.playSound(null, blockPos, SoundEvents.NETHERITE_BLOCK_PLACE, SoundSource.BLOCKS, 1.0f, 1.2f + level.random.nextFloat() * 0.4F);
            }

            itemStack.shrink(1);
            cir.setReturnValue(InteractionResult.sidedSuccess(level.isClientSide));
        }
    }
}
