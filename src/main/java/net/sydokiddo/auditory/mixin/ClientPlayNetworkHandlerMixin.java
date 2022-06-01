package net.sydokiddo.auditory.mixin;

import java.util.ArrayList;
import java.util.List;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.LeashKnotEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityAttachS2CPacket;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin {
    private long lastTime = -1;
    final private List<Integer> leadKnotsDetatchedFromThisTickIds = new ArrayList<>();

    @Inject(method="onEntityAttach", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/mob/MobEntity;setHoldingEntityId(I)V"
    )
    )
    private void onEntityAttachSound(EntityAttachS2CPacket packet, CallbackInfo ci) {
        ClientPlayNetworkHandler clientPlayNetworkHandler = ((ClientPlayNetworkHandler) (Object) this);

        World world = clientPlayNetworkHandler.getWorld();
        MobEntity attachedEntity = (MobEntity) world.getEntityById(packet.getAttachedEntityId());
        Entity newHoldingEntity = world.getEntityById(packet.getHoldingEntityId());
        PlayerEntity p = MinecraftClient.getInstance().player;

        if (newHoldingEntity == null) {
            // Detaching Lead Sounds
            Entity currentHoldingEntity = attachedEntity.getHoldingEntity();

            if (!(currentHoldingEntity instanceof LeashKnotEntity)) {
                // Detach from Player Sound
                double x = attachedEntity.getX(), y = attachedEntity.getY(), z = attachedEntity.getZ();
                world.playSound(p, x, y, z, SoundEvents.ENTITY_LEASH_KNOT_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            } else {
                if (world.getLevelProperties().getTime() != lastTime) {
                    lastTime = world.getLevelProperties().getTime();
                    leadKnotsDetatchedFromThisTickIds.clear();
                }
                if (!leadKnotsDetatchedFromThisTickIds.contains(currentHoldingEntity.getId())) {
                    // Breaking Off Fence Lead Sound
                    double x = currentHoldingEntity.getX(), y = currentHoldingEntity.getY(), z = currentHoldingEntity.getZ();
                    world.playSound(p, x, y, z, SoundEvents.ENTITY_LEASH_KNOT_BREAK, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    leadKnotsDetatchedFromThisTickIds.add(currentHoldingEntity.getId());
                }
            }
        } else if (!(newHoldingEntity instanceof LeashKnotEntity)) {
            // Attach to Player Sound
            double x = attachedEntity.getX(), y = attachedEntity.getY(), z = attachedEntity.getZ();
            world.playSound(p, x, y, z, SoundEvents.ENTITY_LEASH_KNOT_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        } else if (newHoldingEntity.age != 0) {
            // Attach to Pre-Existing Fence Sound
            double x = newHoldingEntity.getX(), y = newHoldingEntity.getY(), z = newHoldingEntity.getZ();
            world.playSound(p, x, y, z, SoundEvents.ENTITY_LEASH_KNOT_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    @Inject(method="onGameJoin", at = @At("HEAD"))
    private void onGameJoin(CallbackInfo ci) {
        lastTime = 0;
        leadKnotsDetatchedFromThisTickIds.clear();
    }
}