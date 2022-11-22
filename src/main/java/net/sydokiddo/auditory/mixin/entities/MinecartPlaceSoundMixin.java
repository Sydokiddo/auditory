package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Plays a sound whenever a minecart is placed

@Mixin(MinecartItem.class)
public class MinecartPlaceSoundMixin {

    // Only need one local, so it's easier and more stable to use ModifyVariable instead of Inject with LocalCapture
    @ModifyVariable(method = "useOn",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;gameEvent(Lnet/minecraft/world/level/gameevent/GameEvent;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/gameevent/GameEvent$Context;)V",
            shift = Shift.AFTER
        )
    )
    private AbstractMinecart auditory_placeSound(AbstractMinecart abstractMinecart, UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        if (Auditory.getConfig().item_sounds.minecart_sounds) {
            level.playSound(null, abstractMinecart, SoundEvents.NETHERITE_BLOCK_PLACE, SoundSource.BLOCKS, 1.0f, 1.2f + level.random.nextFloat() * 0.4F);
        }
        return abstractMinecart;
    }
}
