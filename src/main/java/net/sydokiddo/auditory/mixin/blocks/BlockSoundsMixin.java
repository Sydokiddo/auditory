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

        if (Auditory.getConfig().block_sounds.block_sounds) {

            if (state.is(AuditoryTags.BASALT_SOUNDS)) {
                info.setReturnValue(SoundType.BASALT);
            }

            else if (state.is(AuditoryTags.CLAY_BRICK_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.CLAY_BRICKS);
            }

            else if (state.is(AuditoryTags.DIRT_SOUNDS)) {
                info.setReturnValue(SoundType.ROOTED_DIRT);
            }

            else if (state.is(AuditoryTags.GOLD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.GOLD);
            }

            else if (state.is(AuditoryTags.LEAF_SOUNDS)) {
                info.setReturnValue(SoundType.AZALEA_LEAVES);
            }

            else if (state.is(AuditoryTags.LILY_PAD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.LILY_PAD);
            }

            else if (state.is(AuditoryTags.METAL_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.METAL);
            }

            else if (state.is(AuditoryTags.NETHERRACK_SOUNDS)) {
                info.setReturnValue(SoundType.NETHERRACK);
            }

            else if (state.is(AuditoryTags.OBSIDIAN_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.OBSIDIAN);
            }

            else if (state.is(AuditoryTags.PLANT_SOUNDS)) {
                info.setReturnValue(SoundType.HANGING_ROOTS);
            }

            else if (state.is(AuditoryTags.RAW_ORE_BLOCK_SOUNDS)) {
                info.setReturnValue(SoundType.NETHER_GOLD_ORE);
            }

            else if (state.is(AuditoryTags.SAND_SOUNDS)) {
                info.setReturnValue(SoundType.SAND);
            }

            else if (state.is(AuditoryTags.SHULKER_BOX_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.SHULKER_BOX);
            }

            else if (state.is(AuditoryTags.SMALL_OBJECT_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.SMALL_OBJECT);
            }

            else if (state.is(AuditoryTags.SPAWNER_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.SPAWNER);
            }

            else if (state.is(AuditoryTags.STONE_BRICK_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.STONE_BRICKS);
            }

            else if (state.is(AuditoryTags.STONE_ORE_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.STONE_ORE);
            }

            else if (state.is(AuditoryTags.STRING_SOUNDS)) {
                info.setReturnValue(SoundType.VINE);
            }

            else if (state.is(AuditoryTags.TERRACOTTA_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.TERRACOTTA);
            }

            else if (state.is(AuditoryTags.WOOD_SOUNDS)) {
                info.setReturnValue(SoundType.WOOD);
            }

            else if (state.is(AuditoryTags.MUSHROOM_SOUNDS)) {
                info.setReturnValue(SoundType.WART_BLOCK);
            }

            else if (state.is(AuditoryTags.MUSHROOM_STEM_SOUNDS)) {
                info.setReturnValue(SoundType.STEM);
            }

            else if (state.is(AuditoryTags.PURPUR_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.PURPUR);
            }

            else if (state.is(AuditoryTags.CHORUS_PLANT_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.CHORUS_PLANT);
            }

            else if (state.is(AuditoryTags.ICE_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.ICE);
            }

            else if (state.is(AuditoryTags.GOURD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.GOURD);
            }
        }
    }
}