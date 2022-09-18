package net.sydokiddo.auditory.core.registry.util;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

/**
 * A registry-safe {@link SoundType} that uses deferred sound event suppliers
 *
 * @see SoundType
 * @author ebo2022
 */
public class DeferredSoundType extends SoundType {

    private final Supplier<SoundEvent> breakSound;
    private final Supplier<SoundEvent> stepSound;
    private final Supplier<SoundEvent> placeSound;
    private final Supplier<SoundEvent> hitSound;
    private final Supplier<SoundEvent> fallSound;

    public DeferredSoundType(float volume, float pitch, Supplier<SoundEvent> breakSound, Supplier<SoundEvent> stepSound, Supplier<SoundEvent> placeSound, Supplier<SoundEvent> hitSound, Supplier<SoundEvent> fallSound) {
        super(volume, pitch, null, null, null, null, null);
        this.breakSound = breakSound;
        this.stepSound = stepSound;
        this.placeSound = placeSound;
        this.hitSound = hitSound;
        this.fallSound = fallSound;
    }

    @Override
    public SoundEvent getBreakSound() {
        return this.breakSound.get();
    }

    @Override
    public SoundEvent getStepSound() {
        return this.stepSound.get();
    }

    @Override
    public SoundEvent getPlaceSound() {
        return this.placeSound.get();
    }

    @Override
    public SoundEvent getHitSound() {
        return this.hitSound.get();
    }

    @Override
    public SoundEvent getFallSound() {
        return this.fallSound.get();
    }
}
