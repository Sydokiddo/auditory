package net.sydokiddo.auditory.sound;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.sydokiddo.auditory.Auditory;

public class ModSoundEvents {

// Sound Registry:

// - Item Sounds:

    public static final SoundEvent ITEM_BOW_PULLING = registerSoundEvent("item.bow.pulling");
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
    public static final SoundEvent BLOCK_JUKEBOX_USE = registerSoundEvent("block.jukebox.use");
    public static final SoundEvent BLOCK_JUKEBOX_EJECT = registerSoundEvent("block.jukebox.eject");

// Sound Groups:

    public static final SoundType SHULKER_BOX = new SoundType(1f, 1f,
    ModSoundEvents.BLOCK_SHULKER_BOX_BREAK, ModSoundEvents.BLOCK_SHULKER_BOX_STEP,
    ModSoundEvents.BLOCK_SHULKER_BOX_PLACE, ModSoundEvents.BLOCK_SHULKER_BOX_HIT,
    ModSoundEvents.BLOCK_SHULKER_BOX_FALL);

    public static final SoundType SPAWNER = new SoundType(1f, 1f,
    ModSoundEvents.BLOCK_SPAWNER_BREAK, SoundEvents.NETHERITE_BLOCK_STEP,
    SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT,
    SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType STONE_ORE = new SoundType(1f, 0.9f,
    SoundEvents.NETHER_ORE_BREAK, SoundEvents.NETHER_ORE_STEP,
    SoundEvents.NETHER_ORE_PLACE, SoundEvents.NETHER_ORE_HIT,
    SoundEvents.NETHER_ORE_FALL);

    public static final SoundType OBSIDIAN = new SoundType(1f, 0.7f,
    SoundEvents.DEEPSLATE_BREAK, SoundEvents.DEEPSLATE_STEP,
    SoundEvents.DEEPSLATE_PLACE, SoundEvents.DEEPSLATE_HIT,
    SoundEvents.DEEPSLATE_FALL);

    public static final SoundType TERRACOTTA = new SoundType(1f, 0.6f,
    SoundEvents.CALCITE_BREAK, SoundEvents.CALCITE_STEP,
    SoundEvents.CALCITE_PLACE, SoundEvents.CALCITE_HIT,
    SoundEvents.CALCITE_FALL);

    public static final SoundType STONE_BRICKS = new SoundType(1f, 0.6f,
    SoundEvents.DEEPSLATE_TILES_BREAK, SoundEvents.DEEPSLATE_TILES_STEP,
    SoundEvents.DEEPSLATE_TILES_PLACE, SoundEvents.DEEPSLATE_TILES_HIT,
    SoundEvents.DEEPSLATE_TILES_FALL);

    public static final SoundType CLAY_BRICKS = new SoundType(1f, 1.3f,
    SoundEvents.NETHER_BRICKS_BREAK, SoundEvents.NETHER_BRICKS_STEP,
    SoundEvents.NETHER_BRICKS_PLACE, SoundEvents.NETHER_BRICKS_HIT,
    SoundEvents.NETHER_BRICKS_FALL);

    public static final SoundType METAL = new SoundType(1f, 1.2f,
    SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP,
    SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT,
    SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType GOLD = new SoundType(1f, 1.6f,
    SoundEvents.NETHERITE_BLOCK_BREAK, SoundEvents.NETHERITE_BLOCK_STEP,
    SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT,
    SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType LILY_PAD = new SoundType(1f, 1f,
    SoundEvents.BIG_DRIPLEAF_BREAK, SoundEvents.BIG_DRIPLEAF_STEP,
    SoundEvents.LILY_PAD_PLACE, SoundEvents.BIG_DRIPLEAF_HIT,
    SoundEvents.BIG_DRIPLEAF_FALL);

    public static final SoundType SMALL_OBJECT = new SoundType(1f, 0.8f,
    SoundEvents.CANDLE_BREAK, SoundEvents.CANDLE_STEP,
    SoundEvents.CANDLE_PLACE, SoundEvents.CANDLE_HIT,
    SoundEvents.CANDLE_FALL);

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(Auditory.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Auditory.MOD_ID);
    }
}
