package net.sydokiddo.auditory.common.block;

import gg.moonflower.pollen.api.platform.Platform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.core.registry.AuditoryBlockTags;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public final class BlockSoundOverrides {

    /**
     * If a {@link BlockState} contains any tag on the map, the new sound type will replace the original.
     */
    public static final Map<TagKey<Block>, SoundType> REGISTRY = new HashMap<>() {{
        put(AuditoryBlockTags.BASALT_SOUNDS, SoundType.BASALT);
        put(AuditoryBlockTags.CLAY_BRICK_SOUNDS, AuditorySounds.CLAY_BRICKS);
        put(AuditoryBlockTags.DIRT_SOUNDS, SoundType.ROOTED_DIRT);
        put(AuditoryBlockTags.GOLD_SOUNDS, AuditorySounds.GOLD);
        put(AuditoryBlockTags.LEAF_SOUNDS, SoundType.AZALEA_LEAVES);
        put(AuditoryBlockTags.LILY_PAD_SOUNDS, AuditorySounds.LILY_PAD);
        put(AuditoryBlockTags.METAL_SOUNDS, AuditorySounds.METAL);
        put(AuditoryBlockTags.NETHERRACK_SOUNDS, SoundType.NETHERRACK);
        put(AuditoryBlockTags.OBSIDIAN_SOUNDS, AuditorySounds.OBSIDIAN);
        put(AuditoryBlockTags.PLANT_SOUNDS, SoundType.HANGING_ROOTS);
        put(AuditoryBlockTags.RAW_ORE_BLOCK_SOUNDS, SoundType.NETHER_GOLD_ORE);
        put(AuditoryBlockTags.SAND_SOUNDS, SoundType.SAND);
        put(AuditoryBlockTags.SMALL_OBJECT_SOUNDS, AuditorySounds.SMALL_OBJECT);
        put(AuditoryBlockTags.SPAWNER_SOUNDS, AuditorySounds.SPAWNER);
        put(AuditoryBlockTags.STONE_BRICK_SOUNDS, AuditorySounds.STONE_BRICKS);
        put(AuditoryBlockTags.STONE_ORE_SOUNDS, AuditorySounds.STONE_ORE);
        put(AuditoryBlockTags.STRING_SOUNDS, SoundType.VINE);
        put(AuditoryBlockTags.TERRACOTTA_SOUNDS, AuditorySounds.TERRACOTTA);
        put(AuditoryBlockTags.WOOD_SOUNDS, SoundType.WOOD);
        put(AuditoryBlockTags.MUSHROOM_SOUNDS, SoundType.WART_BLOCK);
        put(AuditoryBlockTags.MUSHROOM_STEM_SOUNDS, SoundType.STEM);
        put(AuditoryBlockTags.ICE_SOUNDS, AuditorySounds.ICE);
        put(AuditoryBlockTags.GOURD_SOUNDS, AuditorySounds.GOURD);
        if (!Platform.isModLoaded("endlessencore")) {
            put(AuditoryBlockTags.SHULKER_BOX_SOUNDS, AuditorySounds.SHULKER_BOX);
            put(AuditoryBlockTags.PURPUR_SOUNDS, AuditorySounds.PURPUR);
            put(AuditoryBlockTags.CHORUS_PLANT_SOUNDS, AuditorySounds.CHORUS_PLANT);
        }
    }};

    private BlockSoundOverrides() {
    }
}
