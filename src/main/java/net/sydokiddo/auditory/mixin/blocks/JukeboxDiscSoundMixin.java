package net.sydokiddo.auditory.mixin.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound when a disc is inserted or ejected from a jukebox

@Mixin(JukeboxBlockEntity.class)
public abstract class JukeboxDiscSoundMixin extends BlockEntity {

    public JukeboxDiscSoundMixin(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    @Inject(method = "removeItem",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/JukeboxBlockEntity;setHasRecordBlockState(Lnet/minecraft/world/entity/Entity;Z)V",
                    shift = At.Shift.AFTER
            )
    )
    private void auditory_ejectDiscSound(int i, int j, CallbackInfoReturnable<ItemStack> cir) {
        if (Auditory.getConfig().block_sounds.jukebox_sounds) {
            if (this.level != null) {
                this.level.playSound(null, this.getBlockPos(), ModSoundEvents.BLOCK_JUKEBOX_EJECT, SoundSource.BLOCKS, 1.0F, 0.8f + level.random.nextFloat() * 0.4F);
            }
        }
    }


    @Inject(method = "setItem", at = @At("HEAD"))
    private void auditory_insertDiscSound(int i, ItemStack itemStack, CallbackInfo ci) {
        if (Auditory.getConfig().block_sounds.jukebox_sounds) {
            if (itemStack.is(ItemTags.MUSIC_DISCS) && this.level != null) {
                this.level.playSound(null, this.getBlockPos(), ModSoundEvents.BLOCK_JUKEBOX_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
    }
}
