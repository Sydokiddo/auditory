package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Plays a sound whenever a boat is placed

@Mixin(BoatItem.class)
public abstract class BoatPlaceSoundMixin extends Item {

    public BoatPlaceSoundMixin(Properties properties) {
        super(properties);
    }

    // Only need one local, so it's easier and more stable to use ModifyVariable instead of Inject with LocalCapture
    @ModifyVariable(method = "use",
        at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;gameEvent(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/gameevent/GameEvent;Lnet/minecraft/core/BlockPos;)V",
            shift = Shift.AFTER
        )
    )
    private Boat auditory_placeSound(Boat boat, Level level, Player player, InteractionHand interactionHand) {
        if (Auditory.getConfig().item_sounds.boat_sounds) {
            if (boat.getBoatType().name().equals("BAMBOO")) {
                level.playSound(null, boat, SoundEvents.BAMBOO_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
            } else {
                level.playSound(null, boat, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
            }
        }
        return boat;
    }
}