package net.sydokiddo.auditory.mixin;

import java.util.HashMap;
import java.util.Map;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.decoration.LeashKnotEntity;

@Mixin(LeashKnotEntity.class)
public abstract class LeashKnotEntityMixin {

    final private Map<Integer, Boolean> leadsSilence = new HashMap<>();

    @Inject(method="onBreak", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/decoration/LeashKnotEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"
    )
    )
    private void silenceBeforeBreakSound(CallbackInfo ci) {
        LeashKnotEntity lead = ((LeashKnotEntity) (Object) this);
        leadsSilence.put(lead.getId(), lead.isSilent());
        lead.setSilent(true);
    }

    @Inject(method="onBreak", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/decoration/LeashKnotEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V",
            shift = At.Shift.AFTER
    )
    )
    private void resetSilenceAfterBreakSound(CallbackInfo ci) {
        LeashKnotEntity lead = ((LeashKnotEntity) (Object) this);
        lead.setSilent(leadsSilence.get(lead.getId()));
        leadsSilence.remove(lead.getId());
    }
}