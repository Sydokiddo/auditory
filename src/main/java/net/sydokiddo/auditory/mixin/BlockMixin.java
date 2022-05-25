package net.sydokiddo.auditory.mixin;

import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.registry.Registry;
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
                            "minecraft:dispenser"
                            -> METAL;
                    case
                            "minecraft:gold_block"
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
                            "minecraft:skeleton_skull",
                            "minecraft:wither_skeleton_skull",
                            "minecraft:zombie_head",
                            "minecraft:creeper_head",
                            "minecraft:player_head",
                            "minecraft:dragon_head",
                            "minecraft:skeleton_wall_skull",
                            "minecraft:wither_skeleton_wall_skull",
                            "minecraft:zombie_wall_head",
                            "minecraft:creeper_wall_head",
                            "minecraft:player_wall_head",
                            "minecraft:dragon_wall_head"
                            -> SMALL_OBJECT;
                    default -> cir.getReturnValue();
                }
        );
    }
}