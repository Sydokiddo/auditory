package net.sydokiddo.auditory.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.auditory.Auditory;

public class ModSoundEvents {

// Sound Registry:

// - Item Sounds:

    public static final SoundEvent ITEM_BOW_PULLING = registerSoundEvent("item.bow.pulling");

// - Block Sounds:

    public static final SoundEvent BLOCK_SHULKER_BOX_BREAK = registerSoundEvent("block.shulker_box.break");
    public static final SoundEvent BLOCK_SHULKER_BOX_STEP = registerSoundEvent("block.shulker_box.step");
    public static final SoundEvent BLOCK_SHULKER_BOX_PLACE = registerSoundEvent("block.shulker_box.place");
    public static final SoundEvent BLOCK_SHULKER_BOX_HIT = registerSoundEvent("block.shulker_box.hit");
    public static final SoundEvent BLOCK_SHULKER_BOX_FALL = registerSoundEvent("block.shulker_box.fall");
    public static final SoundEvent BLOCK_SPAWNER_BREAK = registerSoundEvent("block.spawner.break");

// - Other Sounds:

    public static final SoundEvent ENTITY_PLAYER_DROP_ITEM = registerSoundEvent("player.item.drop");

// Sound Groups:

    public static final BlockSoundGroup SHULKER_BOX = new BlockSoundGroup(1f, 1f,
    ModSoundEvents.BLOCK_SHULKER_BOX_BREAK, ModSoundEvents.BLOCK_SHULKER_BOX_STEP, ModSoundEvents.BLOCK_SHULKER_BOX_PLACE,
    ModSoundEvents.BLOCK_SHULKER_BOX_HIT, ModSoundEvents.BLOCK_SHULKER_BOX_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Auditory.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Auditory.MOD_ID);
    }
}
