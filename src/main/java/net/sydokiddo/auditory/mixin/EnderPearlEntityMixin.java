package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Plays a sound whenever the player teleports with an Ender Pearl

@Environment(EnvType.CLIENT)
@Mixin(EnderPearlEntity.class)

public abstract class EnderPearlEntityMixin extends ThrownItemEntity {
    public EnderPearlEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at=@At(value="INVOKE", target="Lnet/minecraft/entity/projectile/thrown/EnderPearlEntity;discard()V"), method="onCollision(Lnet/minecraft/util/hit/HitResult;)V")
    public void teleportSound(HitResult hitResult, CallbackInfo ci) {
        world.playSound(null, getX(), getY(), getZ(), ModSoundEvents.ITEM_ENDER_PEARL_TELEPORT, SoundCategory.PLAYERS, 1, 1);
    }
}