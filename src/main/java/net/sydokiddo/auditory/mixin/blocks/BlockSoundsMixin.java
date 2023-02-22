package net.sydokiddo.auditory.mixin.blocks;


import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.sydokiddo.auditory.Auditory;
import net.sydokiddo.auditory.misc.AuditoryTags;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Any blocks in the respective tags will use the respective sound group.
// This is entirely data-driven and can be controlled by adding the block's ID to the respective tag through a datapack or mod.

@Mixin(Block.class)
abstract class BlockSoundsMixin extends BlockBehaviour {

    public BlockSoundsMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "getSoundType", cancellable = true)
    private void auditory_alterSoundType(BlockState state, CallbackInfoReturnable<SoundType> info) {

        if (state.is(AuditoryTags.BASALT_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.overworldStoneVariantSounds)) {
            info.setReturnValue(SoundType.BASALT);
        }
        else if (state.is(AuditoryTags.CLAY_BRICK_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.clayBrickSounds)) {
            info.setReturnValue(ModSoundEvents.CLAY_BRICKS);
        }
        else if (state.is(AuditoryTags.DIRT_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.dirtSounds)) {
            info.setReturnValue(SoundType.ROOTED_DIRT);
        }
        else if (state.is(AuditoryTags.GOLD_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.goldSounds)) {
            info.setReturnValue(ModSoundEvents.GOLD);
        }
        else if (state.is(AuditoryTags.LEAF_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.leafSounds)) {
            info.setReturnValue(SoundType.AZALEA_LEAVES);
        }
        else if (state.is(AuditoryTags.LILY_PAD_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.lilyPadSounds)) {
            info.setReturnValue(ModSoundEvents.LILY_PAD);
        }
        else if (state.is(AuditoryTags.METAL_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.metalSounds)) {
            info.setReturnValue(ModSoundEvents.METAL);
        }
        else if (state.is(AuditoryTags.NETHERRACK_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.magmaSounds)) {
            info.setReturnValue(SoundType.NETHERRACK);
        }
        else if (state.is(AuditoryTags.OBSIDIAN_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.obsidianSounds)) {
            info.setReturnValue(ModSoundEvents.OBSIDIAN);
        }
        else if (state.is(AuditoryTags.PLANT_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.plantSounds)) {
            info.setReturnValue(SoundType.HANGING_ROOTS);
        }
        else if (state.is(AuditoryTags.RAW_ORE_BLOCK_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.rawOreBlockSounds)) {
            info.setReturnValue(SoundType.NETHER_GOLD_ORE);
        }
        else if (state.is(AuditoryTags.SAND_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.redstoneWireSounds)) {
            info.setReturnValue(SoundType.SAND);
        }
        else if (state.is(AuditoryTags.SHULKER_BOX_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore")) && (Auditory.getConfig().block_sounds.blockSoundsConfig.shulkerBoxSounds)) {
            info.setReturnValue(ModSoundEvents.SHULKER_BOX);
        }
        else if (state.is(AuditoryTags.SMALL_OBJECT_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.smallObjectSounds)) {
            info.setReturnValue(ModSoundEvents.SMALL_OBJECT);
        }
        else if (state.is(AuditoryTags.SPAWNER_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.spawnerSounds)) {
            info.setReturnValue(ModSoundEvents.SPAWNER);
        }
        else if (state.is(AuditoryTags.STONE_BRICK_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.stoneBrickSounds)) {
            info.setReturnValue(ModSoundEvents.STONE_BRICKS);
        }
        else if (state.is(AuditoryTags.STONE_ORE_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.stoneOreSounds)) {
            info.setReturnValue(ModSoundEvents.STONE_ORE);
        }
        else if (state.is(AuditoryTags.STRING_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.stringSounds)) {
            info.setReturnValue(SoundType.VINE);
        }
        else if (state.is(AuditoryTags.TERRACOTTA_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.terracottaSounds)) {
            info.setReturnValue(ModSoundEvents.TERRACOTTA);
        }
        else if (state.is(AuditoryTags.WOOD_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.jukeboxWoodSounds)) {
            info.setReturnValue(SoundType.WOOD);
        }
        else if (state.is(AuditoryTags.MUSHROOM_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.mushroomSounds)) {
            info.setReturnValue(SoundType.WART_BLOCK);
        }
        else if (state.is(AuditoryTags.MUSHROOM_STEM_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.mushroomSounds)) {
            info.setReturnValue(SoundType.STEM);
        }
        else if (state.is(AuditoryTags.PURPUR_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore")) && (Auditory.getConfig().block_sounds.blockSoundsConfig.purpurSounds)) {
            info.setReturnValue(ModSoundEvents.PURPUR);
        }
        else if (state.is(AuditoryTags.CHORUS_PLANT_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore")) && (Auditory.getConfig().block_sounds.blockSoundsConfig.chorusPlantSounds)) {
            info.setReturnValue(ModSoundEvents.CHORUS_PLANT);
        }
        else if (state.is(AuditoryTags.ICE_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.iceSounds)) {
            info.setReturnValue(ModSoundEvents.ICE);
        }
        else if (state.is(AuditoryTags.GOURD_SOUNDS) && (Auditory.getConfig().block_sounds.blockSoundsConfig.gourdSounds)) {
            info.setReturnValue(ModSoundEvents.GOURD);
        }
    }
}