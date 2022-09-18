package net.sydokiddo.auditory.core.mixin;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.core.Auditory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity {

    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    // Leash sounds

    @Inject(method = "setLeashedTo", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerChunkCache;broadcast(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/Packet;)V"))
    public void setLeashedTo(Entity entity, boolean bl, CallbackInfo ci) {
        if (Auditory.CLIENT_CONFIG.leadSounds.get()) {
            this.level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LEASH_KNOT_PLACE, SoundSource.NEUTRAL, 0.5F, 0.8f + this.level.random.nextFloat() * 0.4F);
        }
    }

    @Inject(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;dropLeash(ZZ)V", shift = At.Shift.AFTER))
    public void interact(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (Auditory.CLIENT_CONFIG.leadSounds.get()) {
            level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LEASH_KNOT_BREAK, SoundSource.NEUTRAL, 0.5F, 0.8f + this.level.random.nextFloat() * 0.4F);
        }
    }
}
