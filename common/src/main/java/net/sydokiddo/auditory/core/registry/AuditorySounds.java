package net.sydokiddo.auditory.core.registry;

import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.sydokiddo.auditory.core.Auditory;
import net.sydokiddo.auditory.core.registry.util.DeferredSoundType;

import java.util.function.Supplier;

public class AuditorySounds {

    public static final PollinatedRegistry<SoundEvent> SOUNDS = PollinatedRegistry.create(Registry.SOUND_EVENT, Auditory.MOD_ID);

    public static final Supplier<SoundEvent> ITEM_BOW_PULLING = registerSound("item.bow.pulling");
    public static final Supplier<SoundEvent> ITEM_TRIDENT_SWING = registerSound("item.trident.swing");
    public static final Supplier<SoundEvent> ITEM_SWORD_SWING = registerSound("item.sword.swing");
    public static final Supplier<SoundEvent> ITEM_AXE_SWING = registerSound("item.axe.swing");
    public static final Supplier<SoundEvent> ITEM_TOOL_SWING = registerSound("item.tool.swing");
    public static final Supplier<SoundEvent> ITEM_SPAWN_EGG_USE = registerSound("item.spawn_egg.use");
    public static final Supplier<SoundEvent> ITEM_SHIELD_RAISE = registerSound("item.shield.raise");

    public static final Supplier<SoundEvent> BLOCK_SHULKER_BOX_BREAK = registerSound("block.shulker_box.break");
    public static final Supplier<SoundEvent> BLOCK_SHULKER_BOX_STEP = registerSound("block.shulker_box.step");
    public static final Supplier<SoundEvent> BLOCK_SHULKER_BOX_PLACE = registerSound("block.shulker_box.place");
    public static final Supplier<SoundEvent> BLOCK_SHULKER_BOX_HIT = registerSound("block.shulker_box.hit");
    public static final Supplier<SoundEvent> BLOCK_SHULKER_BOX_FALL = registerSound("block.shulker_box.fall");

    public static final Supplier<SoundEvent> BLOCK_PURPUR_BREAK = registerSound("block.purpur.break");
    public static final Supplier<SoundEvent> BLOCK_PURPUR_STEP = registerSound("block.purpur.step");
    public static final Supplier<SoundEvent> BLOCK_PURPUR_PLACE = registerSound("block.purpur.place");
    public static final Supplier<SoundEvent> BLOCK_PURPUR_HIT = registerSound("block.purpur.hit");
    public static final Supplier<SoundEvent> BLOCK_PURPUR_FALL = registerSound("block.purpur.fall");

    public static final Supplier<SoundEvent> BLOCK_CHORUS_PLANT_BREAK = registerSound("block.chorus_plant.break");
    public static final Supplier<SoundEvent> BLOCK_CHORUS_PLANT_STEP = registerSound("block.chorus_plant.step");
    public static final Supplier<SoundEvent> BLOCK_CHORUS_PLANT_PLACE = registerSound("block.chorus_plant.place");
    public static final Supplier<SoundEvent> BLOCK_CHORUS_PLANT_HIT = registerSound("block.chorus_plant.hit");
    public static final Supplier<SoundEvent> BLOCK_CHORUS_PLANT_FALL = registerSound("block.chorus_plant.fall");

    public static final Supplier<SoundEvent> BLOCK_SPAWNER_BREAK = registerSound("block.spawner.break");

    public static final Supplier<SoundEvent> BLOCK_ICE_BREAK = registerSound("block.ice.break");
    public static final Supplier<SoundEvent> BLOCK_ICE_STEP = registerSound("block.ice.step");
    public static final Supplier<SoundEvent> BLOCK_ICE_PLACE = registerSound("block.ice.place");
    public static final Supplier<SoundEvent> BLOCK_ICE_HIT = registerSound("block.ice.hit");
    public static final Supplier<SoundEvent> BLOCK_ICE_FALL = registerSound("block.ice.fall");

    public static final Supplier<SoundEvent> BLOCK_GOURD_BREAK = registerSound("block.gourd.break");
    public static final Supplier<SoundEvent> BLOCK_GOURD_STEP = registerSound("block.gourd.step");
    public static final Supplier<SoundEvent> BLOCK_GOURD_PLACE = registerSound("block.gourd.place");
    public static final Supplier<SoundEvent> BLOCK_GOURD_HIT = registerSound("block.gourd.hit");
    public static final Supplier<SoundEvent> BLOCK_GOURD_FALL = registerSound("block.gourd.fall");

    public static final Supplier<SoundEvent> ENTITY_PLAYER_DROP_ITEM = registerSound("player.item.drop");
    public static final Supplier<SoundEvent> BLOCK_JUKEBOX_USE = registerSound("block.jukebox.use");
    public static final Supplier<SoundEvent> BLOCK_JUKEBOX_EJECT = registerSound("block.jukebox.eject");

    public static final SoundType ICE = new DeferredSoundType(1f, 1f,
            BLOCK_ICE_BREAK, BLOCK_ICE_STEP,
            BLOCK_ICE_PLACE, BLOCK_ICE_HIT,
            BLOCK_ICE_FALL);
    public static final SoundType GOURD = new DeferredSoundType(1f, 1f,
            BLOCK_GOURD_BREAK, BLOCK_GOURD_STEP,
            BLOCK_GOURD_PLACE, BLOCK_GOURD_HIT,
            BLOCK_GOURD_FALL);
    public static final SoundType SHULKER_BOX = new DeferredSoundType(1f, 1f,
            BLOCK_SHULKER_BOX_BREAK, BLOCK_SHULKER_BOX_STEP,
            BLOCK_SHULKER_BOX_PLACE, BLOCK_SHULKER_BOX_HIT,
            BLOCK_SHULKER_BOX_FALL);
    public static final SoundType SPAWNER = new DeferredSoundType(1f, 1f,
            BLOCK_SPAWNER_BREAK, () -> SoundEvents.NETHERITE_BLOCK_STEP,
            () -> SoundEvents.NETHERITE_BLOCK_PLACE, () -> SoundEvents.NETHERITE_BLOCK_HIT,
            () -> SoundEvents.NETHERITE_BLOCK_FALL);
    public static final SoundType PURPUR = new DeferredSoundType(1f, 1f,
            BLOCK_PURPUR_BREAK, BLOCK_PURPUR_STEP,
            BLOCK_PURPUR_PLACE, BLOCK_PURPUR_HIT,
            BLOCK_PURPUR_FALL);
    public static final SoundType CHORUS_PLANT = new DeferredSoundType(1f, 1f,
            BLOCK_CHORUS_PLANT_BREAK, BLOCK_CHORUS_PLANT_STEP,
            BLOCK_CHORUS_PLANT_PLACE, BLOCK_CHORUS_PLANT_HIT,
            BLOCK_CHORUS_PLANT_FALL);
    public static final SoundType STONE_ORE = new DeferredSoundType(1f, 0.9f,
            () -> SoundEvents.NETHER_ORE_BREAK, () -> SoundEvents.NETHER_ORE_STEP,
            () -> SoundEvents.NETHER_ORE_PLACE, () -> SoundEvents.NETHER_ORE_HIT,
            () -> SoundEvents.NETHER_ORE_FALL);
    public static final SoundType OBSIDIAN = new DeferredSoundType(1f, 0.7f,
            () -> SoundEvents.DEEPSLATE_BREAK, () -> SoundEvents.DEEPSLATE_STEP,
            () -> SoundEvents.DEEPSLATE_PLACE, () -> SoundEvents.DEEPSLATE_HIT,
            () -> SoundEvents.DEEPSLATE_FALL);
    public static final SoundType TERRACOTTA = new DeferredSoundType(1f, 0.6f,
            () -> SoundEvents.CALCITE_BREAK, () -> SoundEvents.CALCITE_STEP,
            () -> SoundEvents.CALCITE_PLACE, () -> SoundEvents.CALCITE_HIT,
            () -> SoundEvents.CALCITE_FALL);
    public static final SoundType STONE_BRICKS = new DeferredSoundType(1f, 0.6f,
            () -> SoundEvents.DEEPSLATE_TILES_BREAK, () -> SoundEvents.DEEPSLATE_TILES_STEP,
            () -> SoundEvents.DEEPSLATE_TILES_PLACE, () -> SoundEvents.DEEPSLATE_TILES_HIT,
            () -> SoundEvents.DEEPSLATE_TILES_FALL);
    public static final SoundType CLAY_BRICKS = new DeferredSoundType(1f, 1.3f,
            () -> SoundEvents.NETHER_BRICKS_BREAK, () -> SoundEvents.NETHER_BRICKS_STEP,
            () -> SoundEvents.NETHER_BRICKS_PLACE, () -> SoundEvents.NETHER_BRICKS_HIT,
            () -> SoundEvents.NETHER_BRICKS_FALL);
    public static final SoundType METAL = new DeferredSoundType(1f, 1.2f,
            () -> SoundEvents.NETHERITE_BLOCK_BREAK, () -> SoundEvents.NETHERITE_BLOCK_STEP,
            () -> SoundEvents.NETHERITE_BLOCK_PLACE, () -> SoundEvents.NETHERITE_BLOCK_HIT,
            () -> SoundEvents.NETHERITE_BLOCK_FALL);
    public static final SoundType GOLD = new DeferredSoundType(1f, 1.6f,
            () -> SoundEvents.NETHERITE_BLOCK_BREAK, () -> SoundEvents.NETHERITE_BLOCK_STEP,
            () -> SoundEvents.NETHERITE_BLOCK_PLACE, () -> SoundEvents.NETHERITE_BLOCK_HIT,
            () -> SoundEvents.NETHERITE_BLOCK_FALL);
    public static final SoundType LILY_PAD = new DeferredSoundType(1f, 1f,
            () -> SoundEvents.BIG_DRIPLEAF_BREAK, () -> SoundEvents.BIG_DRIPLEAF_STEP,
            () -> SoundEvents.LILY_PAD_PLACE, () -> SoundEvents.BIG_DRIPLEAF_HIT,
            () -> SoundEvents.BIG_DRIPLEAF_FALL);
    public static final SoundType SMALL_OBJECT = new DeferredSoundType(1f, 0.8f,
            () -> SoundEvents.CANDLE_BREAK, () -> SoundEvents.CANDLE_STEP,
            () -> SoundEvents.CANDLE_PLACE, () -> SoundEvents.CANDLE_HIT,
            () -> SoundEvents.CANDLE_FALL);

    private static Supplier<SoundEvent> registerSound(String id) {
        return SOUNDS.register(id, () -> new SoundEvent(Auditory.location(id)));
    }
}
