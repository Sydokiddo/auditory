package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {

    @Shadow public abstract void setSoundEvent(SoundEvent soundEvent);

    public AbstractArrowMixin(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    // Fixes an issue with falling Tridents making default Arrow sounds

    @Inject(method = "onHitBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/AbstractArrow;setSoundEvent(Lnet/minecraft/sounds/SoundEvent;)V",
                    shift = At.Shift.AFTER
            )
    )
    private void auditory_fixTridentHittingBlockSound(BlockHitResult blockHitResult, CallbackInfo ci) {
        if (this.getType() == EntityType.TRIDENT) {
            this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND);
        }
    }
}
