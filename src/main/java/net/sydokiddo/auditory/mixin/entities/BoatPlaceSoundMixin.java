package net.sydokiddo.auditory.mixin.entities;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.function.Predicate;

// Plays a sound whenever a boat is placed

@Mixin(BoatItem.class)
public abstract class BoatPlaceSoundMixin extends Item {

    @Shadow
    @Final
    private static Predicate<Entity> ENTITY_PREDICATE;

    @Shadow
    protected abstract Boat getBoat(Level level, HitResult hitResult);

    @Shadow
    @Final
    private Boat.Type type;

    public BoatPlaceSoundMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    public void use(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS) {
            cir.setReturnValue(InteractionResultHolder.pass(itemStack));
        } else {
            Vec3 vec3 = player.getViewVector(1.0F);
            List<Entity> list = level.getEntities(player, player.getBoundingBox().expandTowards(vec3.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);
            if (!list.isEmpty()) {
                Vec3 vec32 = player.getEyePosition();

                for (Entity entity : list) {
                    AABB aABB = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aABB.contains(vec32)) {
                        cir.setReturnValue(InteractionResultHolder.pass(itemStack));
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Boat boat = this.getBoat(level, hitResult);
                boat.setType(this.type);
                boat.setYRot(player.getYRot());
                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    cir.setReturnValue(InteractionResultHolder.fail(itemStack));
                } else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                        level.playSound(null, boat, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4F);
                        if (!player.getAbilities().instabuild) {
                            itemStack.shrink(1);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                    cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide()));
                }
            } else {
                cir.setReturnValue(InteractionResultHolder.pass(itemStack));
            }
        }
    }
}
