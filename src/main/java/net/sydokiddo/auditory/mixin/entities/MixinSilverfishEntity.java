package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Silverfish;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows for Silverfish to play their stepping sound

@Mixin(Silverfish.class)
public class MixinSilverfishEntity {

    @Inject(at = @At("TAIL"), method = "getMovementEmission", cancellable = true)
    public void playStepSounds(CallbackInfoReturnable<Entity.MovementEmission> cir) {
        if (Auditory.getConfig().entity_sounds) {
            cir.setReturnValue(Entity.MovementEmission.ALL);
        }
    }
}
