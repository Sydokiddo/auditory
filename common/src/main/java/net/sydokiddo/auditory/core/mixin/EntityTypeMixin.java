package net.sydokiddo.auditory.core.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public class EntityTypeMixin {

    @Inject(method = "spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;", at = @At("RETURN"))
    public void spawn(ServerLevel serverLevel, ItemStack itemStack, Player player, BlockPos blockPos, MobSpawnType mobSpawnType, boolean bl, boolean bl2, CallbackInfoReturnable<Entity> cir) {
        if (itemStack.getItem() instanceof SpawnEggItem && Auditory.CLIENT_CONFIG.spawnEggSounds.get()) {
            serverLevel.playSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), AuditorySounds.ITEM_SPAWN_EGG_USE.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
        }
    }
}
