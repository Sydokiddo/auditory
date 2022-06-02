package net.sydokiddo.auditory.mixin;

import net.minecraft.client.MinecraftClient;
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
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

// Plays a sound whenever the player teleports with an Ender Pearl

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ThrownItemEntity {
    public EnderPearlEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(at = @At("TAIL"),
            method="onCollision",
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    protected void onCollision(HitResult hitResult, CallbackInfo ci) {
        super.onCollision(hitResult);
        MinecraftClient client = MinecraftClient.getInstance();
        assert client.player != null;
        client.player.playSound(ModSoundEvents.ITEM_ENDER_PEARL_TELEPORT, SoundCategory.PLAYERS, 0.3F, 1.0F);
    }
}