package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows for Endermites to play their stepping sound

@Mixin(Endermite.class)
public class MixinEndermiteEntity {

    @Inject(at = @At("TAIL"), method = "getMovementEmission", cancellable = true)
    public void playStepSounds(CallbackInfoReturnable<Entity.MovementEmission> cir) {
        if (Auditory.getConfig().misc_sounds.silverfish_step_sounds) {
            cir.setReturnValue(Entity.MovementEmission.ALL);
        }
    }
}
