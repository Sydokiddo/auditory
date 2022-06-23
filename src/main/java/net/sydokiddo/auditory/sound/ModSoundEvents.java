package net.sydokiddo.auditory.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.auditory.Auditory;

public class ModSoundEvents {

// Sound Registry:

// - Item Sounds:

    public static final SoundEvent ITEM_BOW_PULLING = registerSoundEvent("item.bow.pulling");
    public static final SoundEvent ITEM_ENDER_PEARL_TELEPORT = registerSoundEvent("item.ender_pearl.teleport");
    public static final SoundEvent ITEM_SPAWN_EGG_USE = registerSoundEvent("item.spawn_egg.use");

// - Block Sounds:

    public static final SoundEvent BLOCK_SHULKER_BOX_BREAK = registerSoundEvent("block.shulker_box.break");
    public static final SoundEvent BLOCK_SHULKER_BOX_STEP = registerSoundEvent("block.shulker_box.step");
    public static final SoundEvent BLOCK_SHULKER_BOX_PLACE = registerSoundEvent("block.shulker_box.place");
    public static final SoundEvent BLOCK_SHULKER_BOX_HIT = registerSoundEvent("block.shulker_box.hit");
    public static final SoundEvent BLOCK_SHULKER_BOX_FALL = registerSoundEvent("block.shulker_box.fall");
    public static final SoundEvent BLOCK_SPAWNER_BREAK = registerSoundEvent("block.spawner.break");

// - Other Sounds:

    public static final SoundEvent ENTITY_PLAYER_DROP_ITEM = registerSoundEvent("player.item.drop");
    public static final SoundEvent ENTITY_BOAT_PLACE = registerSoundEvent("entity.boat.place");
    public static final SoundEvent ENTITY_MINECART_PLACE = registerSoundEvent("entity.minecart.place");
    public static final SoundEvent BLOCK_JUKEBOX_USE = registerSoundEvent("block.jukebox.use");
    public static final SoundEvent BLOCK_JUKEBOX_EJECT = registerSoundEvent("block.jukebox.eject");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Auditory.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Auditory.MOD_ID);
    }
}
