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
abstract class BlockMixin extends BlockBehaviour {
    public BlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "getSoundType", cancellable = true)
    public void getSoundType(BlockState state, CallbackInfoReturnable<SoundType> info) {

        if (Auditory.getConfig().block_sounds.block_sounds) {

            if (state.is(AuditoryTags.BASALT_SOUNDS)) {
                info.setReturnValue(SoundType.BASALT);
            }

            if (state.is(AuditoryTags.CLAY_BRICK_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.CLAY_BRICKS);
            }

            if (state.is(AuditoryTags.DIRT_SOUNDS)) {
                info.setReturnValue(SoundType.ROOTED_DIRT);
            }

            if (state.is(AuditoryTags.GOLD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.GOLD);
            }

            if (state.is(AuditoryTags.LEAF_SOUNDS)) {
                info.setReturnValue(SoundType.AZALEA_LEAVES);
            }

            if (state.is(AuditoryTags.LILY_PAD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.LILY_PAD);
            }

            if (state.is(AuditoryTags.METAL_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.METAL);
            }

            if (state.is(AuditoryTags.NETHERRACK_SOUNDS)) {
                info.setReturnValue(SoundType.NETHERRACK);
            }

            if (state.is(AuditoryTags.OBSIDIAN_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.OBSIDIAN);
            }

            if (state.is(AuditoryTags.PLANT_SOUNDS)) {
                info.setReturnValue(SoundType.HANGING_ROOTS);
            }

            if (state.is(AuditoryTags.RAW_ORE_BLOCK_SOUNDS)) {
                info.setReturnValue(SoundType.NETHER_GOLD_ORE);
            }

            if (state.is(AuditoryTags.SAND_SOUNDS)) {
                info.setReturnValue(SoundType.SAND);
            }

            if (state.is(AuditoryTags.SHULKER_BOX_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.SHULKER_BOX);
            }

            if (state.is(AuditoryTags.SMALL_OBJECT_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.SMALL_OBJECT);
            }

            if (state.is(AuditoryTags.SPAWNER_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.SPAWNER);
            }

            if (state.is(AuditoryTags.STONE_BRICK_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.STONE_BRICKS);
            }

            if (state.is(AuditoryTags.STONE_ORE_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.STONE_ORE);
            }

            if (state.is(AuditoryTags.STRING_SOUNDS)) {
                info.setReturnValue(SoundType.VINE);
            }

            if (state.is(AuditoryTags.TERRACOTTA_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.TERRACOTTA);
            }

            if (state.is(AuditoryTags.WOOD_SOUNDS)) {
                info.setReturnValue(SoundType.WOOD);
            }

            if (state.is(AuditoryTags.MUSHROOM_SOUNDS)) {
                info.setReturnValue(SoundType.WART_BLOCK);
            }

            if (state.is(AuditoryTags.MUSHROOM_STEM_SOUNDS)) {
                info.setReturnValue(SoundType.STEM);
            }

            if (state.is(AuditoryTags.PURPUR_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.PURPUR);
            }

            if (state.is(AuditoryTags.CHORUS_PLANT_SOUNDS) && (!FabricLoader.getInstance().isModLoaded("endlessencore"))) {
                info.setReturnValue(ModSoundEvents.CHORUS_PLANT);
            }

            if (state.is(AuditoryTags.ICE_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.ICE);
            }

            if (state.is(AuditoryTags.GOURD_SOUNDS)) {
                info.setReturnValue(ModSoundEvents.GOURD);
            }
        }
    }
}