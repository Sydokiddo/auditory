package net.sydokiddo.auditory.mixin;

import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Fork of Trainguy9512's discontinued mod Trainguy's Block Sound Upgrade, credit goes to him for the original code that this is based on!

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow protected abstract Block asBlock();

    private static final BlockSoundGroup STONE_ORE = new BlockSoundGroup(1.0F, 0.9F, SoundEvents.BLOCK_NETHER_ORE_BREAK, SoundEvents.BLOCK_NETHER_ORE_STEP, SoundEvents.BLOCK_NETHER_ORE_PLACE, SoundEvents.BLOCK_NETHER_ORE_HIT, SoundEvents.BLOCK_NETHER_ORE_FALL);
    private static final BlockSoundGroup OBSIDIAN = new BlockSoundGroup(1.0F, 0.7F, SoundEvents.BLOCK_DEEPSLATE_BREAK, SoundEvents.BLOCK_DEEPSLATE_STEP, SoundEvents.BLOCK_DEEPSLATE_PLACE, SoundEvents.BLOCK_DEEPSLATE_HIT, SoundEvents.BLOCK_DEEPSLATE_FALL);
    private static final BlockSoundGroup GRASS_BLOCK = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_GRASS_STEP, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_ROOTED_DIRT_BREAK, SoundEvents.BLOCK_GRASS_FALL);
    private static final BlockSoundGroup TERRACOTTA = new BlockSoundGroup(1.0F, 0.6F, SoundEvents.BLOCK_CALCITE_BREAK, SoundEvents.BLOCK_CALCITE_STEP, SoundEvents.BLOCK_CALCITE_PLACE, SoundEvents.BLOCK_CALCITE_HIT, SoundEvents.BLOCK_CALCITE_FALL);
    private static final BlockSoundGroup STONE_BRICK = new BlockSoundGroup(1.0F, 0.6F, SoundEvents.BLOCK_DEEPSLATE_TILES_BREAK, SoundEvents.BLOCK_DEEPSLATE_TILES_STEP, SoundEvents.BLOCK_DEEPSLATE_TILES_PLACE, SoundEvents.BLOCK_DEEPSLATE_TILES_HIT, SoundEvents.BLOCK_DEEPSLATE_TILES_FALL);
    private static final BlockSoundGroup CLAY_BRICK = new BlockSoundGroup(1.0F, 1.3F, SoundEvents.BLOCK_NETHER_BRICKS_BREAK, SoundEvents.BLOCK_NETHER_BRICKS_STEP, SoundEvents.BLOCK_NETHER_BRICKS_PLACE, SoundEvents.BLOCK_NETHER_BRICKS_HIT, SoundEvents.BLOCK_NETHER_BRICKS_FALL);
    private static final BlockSoundGroup METAL = new BlockSoundGroup(1.0F, 1.2F, SoundEvents.BLOCK_NETHERITE_BLOCK_BREAK, SoundEvents.BLOCK_NETHERITE_BLOCK_STEP, SoundEvents.BLOCK_NETHERITE_BLOCK_PLACE, SoundEvents.BLOCK_NETHERITE_BLOCK_HIT, SoundEvents.BLOCK_NETHERITE_BLOCK_FALL);
    private static final BlockSoundGroup GOLD = new BlockSoundGroup(1.0F, 1.6F, SoundEvents.BLOCK_NETHERITE_BLOCK_BREAK, SoundEvents.BLOCK_NETHERITE_BLOCK_STEP, SoundEvents.BLOCK_NETHERITE_BLOCK_PLACE, SoundEvents.BLOCK_NETHERITE_BLOCK_HIT, SoundEvents.BLOCK_NETHERITE_BLOCK_FALL);
    private static final BlockSoundGroup LILY_PAD = new BlockSoundGroup(1.0F, 1.0F, SoundEvents.BLOCK_BIG_DRIPLEAF_BREAK, SoundEvents.BLOCK_BIG_DRIPLEAF_STEP, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundEvents.BLOCK_BIG_DRIPLEAF_HIT, SoundEvents.BLOCK_BIG_DRIPLEAF_FALL);
    private static final BlockSoundGroup SMALL_OBJECT = new BlockSoundGroup(1.0F, 0.8F, SoundEvents.BLOCK_CANDLE_BREAK, SoundEvents.BLOCK_CANDLE_STEP, SoundEvents.BLOCK_CANDLE_PLACE, SoundEvents.BLOCK_CANDLE_HIT, SoundEvents.BLOCK_CANDLE_FALL);
    private static final BlockSoundGroup SHULKER_BOX = new BlockSoundGroup(1.0F, 1.2F, ModSoundEvents.BLOCK_SHULKER_BOX_BREAK, ModSoundEvents.BLOCK_SHULKER_BOX_STEP, ModSoundEvents.BLOCK_SHULKER_BOX_PLACE, ModSoundEvents.BLOCK_SHULKER_BOX_HIT, ModSoundEvents.BLOCK_SHULKER_BOX_FALL);
    private static final BlockSoundGroup SPAWNER = new BlockSoundGroup(1.0F, 1.0F, ModSoundEvents.BLOCK_SPAWNER_BREAK, SoundEvents.BLOCK_NETHERITE_BLOCK_STEP, SoundEvents.BLOCK_NETHERITE_BLOCK_PLACE, SoundEvents.BLOCK_NETHERITE_BLOCK_HIT, SoundEvents.BLOCK_NETHERITE_BLOCK_FALL);

    @Inject(method = "getSoundGroup", at = @At("TAIL"), cancellable = true)
    private void getSoundGroupMixin(CallbackInfoReturnable<BlockSoundGroup> cir){
        String blockId = Registry.BLOCK.getId(this.asBlock()).toString();
        cir.setReturnValue(
                switch (blockId){
                    case
                            "minecraft:coal_ore",
                            "minecraft:copper_ore",
                            "minecraft:iron_ore",
                            "minecraft:gold_ore",
                            "minecraft:emerald_ore",
                            "minecraft:diamond_ore",
                            "minecraft:redstone_ore",
                            "minecraft:lapis_ore",
                            "odyssey:ruby_ore"
                            -> STONE_ORE;
                    case
                            "minecraft:raw_copper_block",
                            "minecraft:raw_iron_block",
                            "minecraft:raw_gold_block"
                            -> BlockSoundGroup.NETHER_GOLD_ORE;
                    case
                            "minecraft:oak_leaves",
                            "minecraft:birch_leaves",
                            "minecraft:spruce_leaves",
                            "minecraft:jungle_leaves",
                            "minecraft:acacia_leaves",
                            "minecraft:dark_oak_leaves",
                            "minecraft:mangrove_leaves",
                            "minecraft:oak_sapling",
                            "minecraft:birch_sapling",
                            "minecraft:spruce_sapling",
                            "minecraft:jungle_sapling",
                            "minecraft:acacia_sapling",
                            "minecraft:dark_oak_sapling",
                            "minecraft:mangrove_propagule"
                            -> BlockSoundGroup.AZALEA;
                    case
                            "minecraft:lily_pad"
                            -> LILY_PAD;
                    case
                            "minecraft:end_stone",
                            "minecraft:andesite",
                            "minecraft:andesite_wall",
                            "minecraft:andesite_slab",
                            "minecraft:andesite_stairs",
                            "minecraft:diorite",
                            "minecraft:diorite_wall",
                            "minecraft:diorite_stairs",
                            "minecraft:diorite_slab",
                            "minecraft:granite",
                            "minecraft:granite_wall",
                            "minecraft:granite_slab",
                            "minecraft:granite_stairs"
                            -> BlockSoundGroup.BASALT;
                    case
                            "minecraft:obsidian",
                            "minecraft:crying_obsidian",
                            "minecraft:respawn_anchor",
                            "minecraft:enchanting_table",
                            "lottablocks:obsidian_bricks",
                            "lottablocks:obsidian_brick_stairs",
                            "lottablocks:obsidian_brick_slab",
                            "lottablocks:obsidian_brick_wall",
                            "lottablocks:obsidian_pillar"
                            -> OBSIDIAN;
                    case
                            "minecraft:grass_block",
                            "minecraft:podzol",
                            "minecraft:mycelium",
                            "minecraft:dirt_path"
                            -> GRASS_BLOCK;
                    case
                            "minecraft:shulker_box",
                            "minecraft:white_shulker_box",
                            "minecraft:light_gray_shulker_box",
                            "minecraft:gray_shulker_box",
                            "minecraft:black_shulker_box",
                            "minecraft:brown_shulker_box",
                            "minecraft:red_shulker_box",
                            "minecraft:orange_shulker_box",
                            "minecraft:yellow_shulker_box",
                            "minecraft:lime_shulker_box",
                            "minecraft:green_shulker_box",
                            "minecraft:cyan_shulker_box",
                            "minecraft:light_blue_shulker_box",
                            "minecraft:blue_shulker_box",
                            "minecraft:purple_shulker_box",
                            "minecraft:magenta_shulker_box",
                            "minecraft:pink_shulker_box"
                            -> SHULKER_BOX;
                    case
                            "minecraft:grass",
                            "minecraft:dead_bush",
                            "minecraft:tall_grass",
                            "minecraft:fern",
                            "minecraft:tall_fern",
                            "minecraft:dandelion",
                            "minecraft:poppy",
                            "minecraft:blue_orchid",
                            "minecraft:allium",
                            "minecraft:azure_bluet",
                            "minecraft:red_tulip",
                            "minecraft:orange_tulip",
                            "minecraft:white_tulip",
                            "minecraft:pink_tulip",
                            "minecraft:oxeye_daisy",
                            "minecraft:cornflower",
                            "minecraft:lily_of_the_valley",
                            "minecraft:wither_rose",
                            "minecraft:brown_mushroom",
                            "minecraft:red_mushroom"
                            -> BlockSoundGroup.HANGING_ROOTS;
                    case
                            "minecraft:cobweb",
                            "minecraft:glow_lichen",
                            "minecraft:tripwire"
                            -> BlockSoundGroup.VINE;
                    case
                            "minecraft:spawner"
                            -> SPAWNER;
                    case
                            "minecraft:white_terracotta",
                            "minecraft:orange_terracotta",
                            "minecraft:magenta_terracotta",
                            "minecraft:light_blue_terracotta",
                            "minecraft:yellow_terracotta",
                            "minecraft:lime_terracotta",
                            "minecraft:pink_terracotta",
                            "minecraft:gray_terracotta",
                            "minecraft:light_gray_terracotta",
                            "minecraft:cyan_terracotta",
                            "minecraft:purple_terracotta",
                            "minecraft:blue_terracotta",
                            "minecraft:brown_terracotta",
                            "minecraft:green_terracotta",
                            "minecraft:red_terracotta",
                            "minecraft:black_terracotta",
                            "minecraft:terracotta"
                            -> TERRACOTTA;
                    case
                            "minecraft:stone_brick_slab",
                            "minecraft:infested_stone_bricks",
                            "minecraft:infested_mossy_stone_bricks",
                            "minecraft:infested_cracked_stone_bricks",
                            "minecraft:infested_chiseled_stone_bricks",
                            "minecraft:stone_bricks",
                            "minecraft:mossy_stone_bricks",
                            "minecraft:cracked_stone_bricks",
                            "minecraft:chiseled_stone_bricks",
                            "minecraft:stone_brick_stairs",
                            "minecraft:mossy_stone_brick_wall",
                            "minecraft:stone_brick_wall",
                            "minecraft:mossy_stone_brick_stairs",
                            "minecraft:mossy_stone_brick_slab"
                            -> STONE_BRICK;
                    case
                            "minecraft:brick_slab",
                            "minecraft:bricks",
                            "minecraft:brick_stairs",
                            "minecraft:brick_wall"
                            -> CLAY_BRICK;
                    case
                            "minecraft:iron_bars",
                            "minecraft:iron_door",
                            "minecraft:iron_block",
                            "minecraft:observer",
                            "minecraft:dropper",
                            "minecraft:dispenser",
                            "minecraft:hopper",
                            "minecraft:piston",
                            "minecraft:sticky_piston",
                            "minecraft:heavy_weighted_pressure_plate"
                            -> METAL;
                    case
                            "minecraft:gold_block",
                            "minecraft:light_weighted_pressure_plate"
                            -> GOLD;
                    case
                            "minecraft:gravel",
                            "minecraft:dirt",
                            "minecraft:coarse_dirt"
                            -> BlockSoundGroup.ROOTED_DIRT;
                    case
                            "minecraft:redstone_wire"
                            -> BlockSoundGroup.SAND;
                    case
                            "minecraft:flower_pot",
                            "minecraft:torch",
                            "minecraft:soul_torch",
                            "minecraft:redstone_torch",
                            "minecraft:wall_torch",
                            "minecraft:soul_wall_torch",
                            "minecraft:redstone_wall_torch",
                            "minecraft:end_rod",
                            "minecraft:tripwire_hook",
                            "minecraft:potted_wither_rose",
                            "minecraft:potted_white_tulip",
                            "minecraft:potted_warped_roots",
                            "minecraft:potted_spruce_sapling",
                            "minecraft:potted_red_tulip",
                            "minecraft:potted_red_mushroom",
                            "minecraft:potted_poppy",
                            "minecraft:potted_pink_tulip",
                            "minecraft:potted_oxeye_daisy",
                            "minecraft:potted_orange_tulip",
                            "minecraft:potted_oak_sapling",
                            "minecraft:potted_mangrove_propagule",
                            "minecraft:potted_lily_of_the_valley",
                            "minecraft:potted_jungle_sapling",
                            "minecraft:potted_flowering_azalea_bush",
                            "minecraft:potted_fern",
                            "minecraft:potted_dead_bush",
                            "minecraft:potted_dark_oak_sapling",
                            "minecraft:potted_dandelion",
                            "minecraft:potted_crimson_roots",
                            "minecraft:potted_crimson_fungus",
                            "minecraft:potted_cornflower",
                            "minecraft:potted_cactus",
                            "minecraft:potted_brown_mushroom",
                            "minecraft:potted_blue_orchid",
                            "minecraft:potted_birch_sapling",
                            "minecraft:potted_bamboo",
                            "minecraft:potted_azure_bluet",
                            "minecraft:potted_azalea_bush",
                            "minecraft:potted_allium",
                            "minecraft:potted_acacia_sapling"
                            -> SMALL_OBJECT;
                    default -> cir.getReturnValue();
                }
        );
    }
}