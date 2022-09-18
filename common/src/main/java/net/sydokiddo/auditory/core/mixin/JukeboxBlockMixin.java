package net.sydokiddo.auditory.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxBlock.class)
public class JukeboxBlockMixin {

    // Eject sound
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/JukeboxBlock;dropRecording(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V", shift = At.Shift.AFTER))
    public void use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult, CallbackInfoReturnable<InteractionResult> cir) {
        if (Auditory.CLIENT_CONFIG.jukeboxSounds.get()) {
            level.playSound(player, blockPos, AuditorySounds.BLOCK_JUKEBOX_EJECT.get(), SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
        }
    }

    // Insert sound
    @Inject(method = "setRecord", at = @At("HEAD"))
    public void setRecord(LevelAccessor levelAccessor, BlockPos blockPos, BlockState blockState, ItemStack itemStack, CallbackInfo ci) {
        if (Auditory.CLIENT_CONFIG.jukeboxSounds.get()) {
            levelAccessor.playSound(null, blockPos, AuditorySounds.BLOCK_JUKEBOX_USE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }
}
