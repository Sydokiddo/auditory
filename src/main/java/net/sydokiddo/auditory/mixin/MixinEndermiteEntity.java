package net.sydokiddo.auditory.mixin;

import net.minecraft.entity.mob.EndermiteEntity;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.Entity;

// Allows for Endermites to play their stepping sound

@Mixin(EndermiteEntity.class)
public class MixinEndermiteEntity {

    @Inject(at = @At("TAIL"), method = "getMoveEffect()Lnet/minecraft/entity/Entity$MoveEffect;", cancellable = true)
    public void canClimb(CallbackInfoReturnable<Entity.MoveEffect> cir) {
        if (Auditory.getConfig().entity_sounds) {
            cir.setReturnValue(Entity.MoveEffect.ALL);
        }
    }
}