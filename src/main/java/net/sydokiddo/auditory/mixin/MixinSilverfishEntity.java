package net.sydokiddo.auditory.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SilverfishEntity;

// Allows for Silverfish to play their stepping sound

@Mixin(SilverfishEntity.class)
public class MixinSilverfishEntity {

    @Inject(at=@At("TAIL"), method="getMoveEffect()Lnet/minecraft/entity/Entity$MoveEffect;", cancellable=true)
    public void canClimb(CallbackInfoReturnable<Entity.MoveEffect> cir) {
        cir.setReturnValue(Entity.MoveEffect.ALL);
    }
}