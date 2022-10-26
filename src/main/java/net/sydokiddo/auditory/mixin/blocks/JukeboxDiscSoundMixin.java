package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound when a disc is inserted or ejected from a jukebox

@Mixin(JukeboxBlock.class)
public abstract class JukeboxDiscSoundMixin extends BaseEntityBlock {

    @Shadow
    @Final
    public static BooleanProperty HAS_RECORD;

    protected JukeboxDiscSoundMixin(Properties properties) {
        super(properties);
    }

    // Eject Disc Sound

    @Inject(method = "use", at = @At("HEAD"))
    public void use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (blockState.getValue(HAS_RECORD)) {
            if (Auditory.getConfig().block_sounds.jukebox_sounds) {
                level.playSound(player, blockPos, ModSoundEvents.BLOCK_JUKEBOX_EJECT, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
            }
        }
    }

    // Insert Disc Sound

    @Inject(method = "setRecord", at = @At("HEAD"))
    public void setRecord(Entity entity, LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, ItemStack itemStack, CallbackInfo ci) {
        if (Auditory.getConfig().block_sounds.jukebox_sounds) {
            levelAccessor.playSound(null, blockPos, ModSoundEvents.BLOCK_JUKEBOX_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
