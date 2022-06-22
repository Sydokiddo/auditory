package net.sydokiddo.auditory.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Plays a sound whenever a boat is placed

@Environment(EnvType.CLIENT)
@Mixin(BoatItem.class)
public class BoatPlaceSoundMixin
{
    @Inject(at = @At(value = "RETURN", target = "Lnet/minecraft/util/TypedActionResult;use(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;"), method = "createEntity")
    public void createEntity(World world, HitResult hitResult, CallbackInfoReturnable<BoatEntity> cir) {
            MinecraftClient client = MinecraftClient.getInstance();
            assert client.player != null;
            client.player.playSound(ModSoundEvents.ENTITY_BOAT_PLACE, SoundCategory.BLOCKS, 0.8F, 1.0F);
        }
    }