package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.sydokiddo.auditory.misc.AuditoryTags;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class FoodEatingSoundsMixin {

    @Shadow public abstract Item getItem();
    @Shadow public abstract boolean is(TagKey<Item> tagKey);

    @Inject(method = "getEatingSound", at = @At("HEAD"), cancellable = true)
    public void auditory_changeItemEatingSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.is(AuditoryTags.DRIED_KELP_SOUNDS)) {
            cir.setReturnValue(ModSoundEvents.DRIED_KELP_EAT);
        }
        else if (this.is(AuditoryTags.SOFT_FRUIT_SOUNDS)) {
            cir.setReturnValue(ModSoundEvents.SOFT_FRUIT_EAT);
        }
        else if (this.is(AuditoryTags.CRUNCHY_FRUIT_SOUNDS)) {
            cir.setReturnValue(ModSoundEvents.CRUNCHY_FRUIT_EAT);
        }
        else if (this.is(AuditoryTags.STEW_SOUNDS)) {
            cir.setReturnValue(ModSoundEvents.STEW_EAT);
        }
        else if (this.is(AuditoryTags.VEGETABLE_SOUNDS)) {
            cir.setReturnValue(ModSoundEvents.VEGETABLE_EAT);
        }
    }
}
