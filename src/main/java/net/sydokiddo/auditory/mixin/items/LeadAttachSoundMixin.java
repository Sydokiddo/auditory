package net.sydokiddo.auditory.mixin.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.server.level.ServerLevel;
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
import net.sydokiddo.auditory.Auditory;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Lead Attaching Sounds

@Mixin(Mob.class)
public abstract class LeadAttachSoundMixin extends LivingEntity {

    @Shadow
    @Nullable
    private Entity leashHolder;

    @Shadow
    @Nullable
    private CompoundTag leashInfoTag;

    protected LeadAttachSoundMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    @Nullable
    public abstract Entity getLeashHolder();

    @Shadow
    public abstract void dropLeash(boolean bl, boolean bl2);

    @Inject(at = @At("TAIL"), method = "setLeashedTo")
    public void setLeashedTo(Entity entity, boolean bl, CallbackInfo ci) {
        this.leashHolder = entity;
        this.leashInfoTag = null;
        if (!this.level.isClientSide && bl && this.level instanceof ServerLevel) {

            if (Auditory.getConfig().item_sounds.lead_sounds) {
                level.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.LEASH_KNOT_PLACE, SoundSource.NEUTRAL, 0.5F, 0.8f + this.level.random.nextFloat() * 0.4F);
            }

            ((ServerLevel) this.level).getChunkSource().broadcast(this, new ClientboundSetEntityLinkPacket(this, this.leashHolder));
        }

        if (this.isPassenger()) {
            this.stopRiding();
        }
    }

    @Inject(at = @At("TAIL"), method = "interact", cancellable = true)
    public final void interact(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        if (!this.isAlive()) {

            cir.setReturnValue(InteractionResult.PASS);

        } else if (this.getLeashHolder() == player) {

            this.dropLeash(true, !player.getAbilities().instabuild);

            if (Auditory.getConfig().item_sounds.lead_sounds) {
                level.playSound(player, this.getX(), this.getY(), this.getZ(), SoundEvents.LEASH_KNOT_BREAK, SoundSource.NEUTRAL, 0.5F, 0.8f + this.level.random.nextFloat() * 0.4F);
            }

            cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
        }
    }
}
