package net.sydokiddo.auditory.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.sydokiddo.auditory.Auditory;

public class ModSoundEvents {

    // - Item Sounds:

    public static final SoundEvent ITEM_BOW_PULLING = registerSoundEvent("item.bow.pulling");
    public static final SoundEvent ITEM_TRIDENT_SWING = registerSoundEvent("item.trident.swing");
    public static final SoundEvent ITEM_SWORD_SWING = registerSoundEvent("item.sword.swing");
    public static final SoundEvent ITEM_AXE_SWING = registerSoundEvent("item.axe.swing");
    public static final SoundEvent ITEM_TOOL_SWING = registerSoundEvent("item.tool.swing");
    public static final SoundEvent ITEM_SPAWN_EGG_USE = registerSoundEvent("item.spawn_egg.use");
    public static final SoundEvent ITEM_SHIELD_RAISE = registerSoundEvent("item.shield.raise");

    // - Block Sounds:

    public static final SoundEvent BLOCK_SHULKER_BOX_BREAK = registerSoundEvent("block.shulker_box.break");
    public static final SoundEvent BLOCK_SHULKER_BOX_STEP = registerSoundEvent("block.shulker_box.step");
    public static final SoundEvent BLOCK_SHULKER_BOX_PLACE = registerSoundEvent("block.shulker_box.place");
    public static final SoundEvent BLOCK_SHULKER_BOX_HIT = registerSoundEvent("block.shulker_box.hit");
    public static final SoundEvent BLOCK_SHULKER_BOX_FALL = registerSoundEvent("block.shulker_box.fall");

    public static final SoundEvent BLOCK_PURPUR_BREAK = registerSoundEvent("block.purpur.break");
    public static final SoundEvent BLOCK_PURPUR_STEP = registerSoundEvent("block.purpur.step");
    public static final SoundEvent BLOCK_PURPUR_PLACE = registerSoundEvent("block.purpur.place");
    public static final SoundEvent BLOCK_PURPUR_HIT = registerSoundEvent("block.purpur.hit");
    public static final SoundEvent BLOCK_PURPUR_FALL = registerSoundEvent("block.purpur.fall");

    public static final SoundEvent BLOCK_CHORUS_PLANT_BREAK = registerSoundEvent("block.chorus_plant.break");
    public static final SoundEvent BLOCK_CHORUS_PLANT_STEP = registerSoundEvent("block.chorus_plant.step");
    public static final SoundEvent BLOCK_CHORUS_PLANT_PLACE = registerSoundEvent("block.chorus_plant.place");
    public static final SoundEvent BLOCK_CHORUS_PLANT_HIT = registerSoundEvent("block.chorus_plant.hit");
    public static final SoundEvent BLOCK_CHORUS_PLANT_FALL = registerSoundEvent("block.chorus_plant.fall");

    public static final SoundEvent BLOCK_SPAWNER_BREAK = registerSoundEvent("block.spawner.break");

    public static final SoundEvent BLOCK_ICE_BREAK = registerSoundEvent("block.ice.break");
    public static final SoundEvent BLOCK_ICE_STEP = registerSoundEvent("block.ice.step");
    public static final SoundEvent BLOCK_ICE_PLACE = registerSoundEvent("block.ice.place");
    public static final SoundEvent BLOCK_ICE_HIT = registerSoundEvent("block.ice.hit");
    public static final SoundEvent BLOCK_ICE_FALL = registerSoundEvent("block.ice.fall");

    public static final SoundEvent BLOCK_GOURD_BREAK = registerSoundEvent("block.gourd.break");
    public static final SoundEvent BLOCK_GOURD_STEP = registerSoundEvent("block.gourd.step");
    public static final SoundEvent BLOCK_GOURD_PLACE = registerSoundEvent("block.gourd.place");
    public static final SoundEvent BLOCK_GOURD_HIT = registerSoundEvent("block.gourd.hit");
    public static final SoundEvent BLOCK_GOURD_FALL = registerSoundEvent("block.gourd.fall");

    // - Other Sounds:

    public static final SoundEvent ENTITY_PLAYER_DROP_ITEM = registerSoundEvent("player.item.drop");
    public static final SoundEvent BLOCK_JUKEBOX_USE = registerSoundEvent("block.jukebox.use");
    public static final SoundEvent BLOCK_JUKEBOX_EJECT = registerSoundEvent("block.jukebox.eject");

    // Sound Groups:

    public static final SoundType ICE = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_ICE_BREAK, ModSoundEvents.BLOCK_ICE_STEP,
        ModSoundEvents.BLOCK_ICE_PLACE, ModSoundEvents.BLOCK_ICE_HIT,
        ModSoundEvents.BLOCK_ICE_FALL);

    public static final SoundType GOURD = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_GOURD_BREAK, ModSoundEvents.BLOCK_GOURD_STEP,
        ModSoundEvents.BLOCK_GOURD_PLACE, ModSoundEvents.BLOCK_GOURD_HIT,
        ModSoundEvents.BLOCK_GOURD_FALL);

    public static final SoundType SHULKER_BOX = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_SHULKER_BOX_BREAK, ModSoundEvents.BLOCK_SHULKER_BOX_STEP,
        ModSoundEvents.BLOCK_SHULKER_BOX_PLACE, ModSoundEvents.BLOCK_SHULKER_BOX_HIT,
        ModSoundEvents.BLOCK_SHULKER_BOX_FALL);

    public static final SoundType SPAWNER = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_SPAWNER_BREAK, SoundEvents.NETHERITE_BLOCK_STEP,
        SoundEvents.NETHERITE_BLOCK_PLACE, SoundEvents.NETHERITE_BLOCK_HIT,
        SoundEvents.NETHERITE_BLOCK_FALL);

    public static final SoundType PURPUR = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_PURPUR_BREAK, ModSoundEvents.BLOCK_PURPUR_STEP,
        ModSoundEvents.BLOCK_PURPUR_PLACE, ModSoundEvents.BLOCK_PURPUR_HIT,
        ModSoundEvents.BLOCK_PURPUR_FALL);

    public static final SoundType CHORUS_PLANT = new SoundType(1f, 1f,
        ModSoundEvents.BLOCK_CHORUS_PLANT_BREAK, ModSoundEvents.BLOCK_CHORUS_PLANT_STEP,
        ModSoundEvents.BLOCK_CHORUS_PLANT_PLACE, ModSoundEvents.BLOCK_CHORUS_PLANT_HIT,
        ModSoundEvents.BLOCK_CHORUS_PLANT_FALL);

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
        SoundEvent se = SoundEvent.createVariableRangeEvent(id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, se);
    }

    public static void registerSounds() {
        System.out.println("Registering Sounds for " + Auditory.MOD_ID);
    }
}
