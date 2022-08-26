package net.sydokiddo.auditory.mixin.items;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.InstrumentItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Changes instruments to play on the Players sound category instead of the Records category
// This fixes issues with other mods that can cause instrument sounds to cut off music abruptly

@Mixin(InstrumentItem.class)
public class InstrumentSoundMixin {

    @Inject(at = @At("TAIL"), method = "play")
    private static void play(Level level, Player player, Instrument instrument, CallbackInfo ci) {
        SoundEvent soundEvent = instrument.soundEvent();
        float f = instrument.range() / 16.0F;
        level.playSound(player, player, soundEvent, SoundSource.PLAYERS, f, 1.0F);
        level.gameEvent(GameEvent.INSTRUMENT_PLAY, player.position(), GameEvent.Context.of(player));
    }
}
