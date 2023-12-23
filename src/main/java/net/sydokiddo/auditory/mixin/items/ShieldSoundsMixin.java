package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShieldItem.class)
public abstract class ShieldSoundsMixin extends Item implements Equipable {

    public ShieldSoundsMixin(Properties properties) {
        super(properties);
    }

    // Plays a sound when the user raises their shield

    @Inject(at = @At("HEAD"), method = "use")
    private void auditory_blockSound(Level level, Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (Auditory.getConfig().weapon_sounds.shield_blocking_sounds) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ModSoundEvents.ITEM_SHIELD_RAISE, SoundSource.PLAYERS, 0.1F, 0.8f + player.level().random.nextFloat() * 0.4F);
        }
    }

    // Shields now have a unique equipping sound

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return ModSoundEvents.ITEM_SHIELD_EQUIP;
    }
}
