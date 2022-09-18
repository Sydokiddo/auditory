package net.sydokiddo.auditory.core.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.sydokiddo.auditory.core.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Endermite.class)
public class EndermiteMixin {

    @Inject(method = "getMovementEmission",at = @At("TAIL"), cancellable = true)
    public void getMovementEmission(CallbackInfoReturnable<Entity.MovementEmission> cir) {
        if (Auditory.CLIENT_CONFIG.silverfishStepSounds.get()) {
            cir.setReturnValue(Entity.MovementEmission.ALL);
        }
    }
}
