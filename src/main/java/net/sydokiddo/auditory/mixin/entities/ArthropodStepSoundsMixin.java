package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Silverfish;
import net.sydokiddo.auditory.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Allows for Endermites & Silverfish to play their stepping sound

@Mixin({Endermite.class, Silverfish.class})
public class ArthropodStepSoundsMixin {

    @Inject(at = @At("TAIL"), method = "getMovementEmission", cancellable = true)
    private void auditory_enableStepSounds(CallbackInfoReturnable<Entity.MovementEmission> cir) {
        if (Auditory.getConfig().misc_sounds.silverfish_step_sounds) {
            cir.setReturnValue(Entity.MovementEmission.ALL);
        }
    }
}
