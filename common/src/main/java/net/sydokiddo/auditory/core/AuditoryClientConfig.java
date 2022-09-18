package net.sydokiddo.auditory.core;

import gg.moonflower.pollen.api.config.PollinatedConfigBuilder;

public class AuditoryClientConfig {

    public final PollinatedConfigBuilder.ConfigValue<Boolean> blockSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> jukeboxSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> flowerPotSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> cakeEatingSounds;

    public final PollinatedConfigBuilder.ConfigValue<Boolean> enderPearlSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> spawnEggSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> leadSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> boatSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> minecartSounds;

    public final PollinatedConfigBuilder.ConfigValue<Boolean> fistSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> swordSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> axeSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> pickaxeSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> shovelSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> hoeSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> tridentSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> shearSwingingSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> bowPullbackSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> tridentPullbackSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> shieldBlockingSounds;

    public final PollinatedConfigBuilder.ConfigValue<Boolean> silverfishStepSounds;
    public final PollinatedConfigBuilder.ConfigValue<Boolean> itemDropSounds;

    public AuditoryClientConfig(PollinatedConfigBuilder builder) {
        builder.push("Block Sounds");
        this.blockSounds = builder.comment("Enables updated block sounds when set to true.").define("Updated Block Sounds", true);
        this.jukeboxSounds = builder.comment("Whether jukeboxes should play an inserting and ejecting sound when interacted with.").define("Jukebox Interact Sounds", true);
        this.flowerPotSounds = builder.comment("Whether flower pots should play a sound when a plant is inserted into them.").define("Flower Pot Interact Sounds", true);
        this.cakeEatingSounds = builder.comment("Whether cakes should play a sound when eaten by the player.").define("Cake Eating Sounds", true);
        builder.pop();

        builder.push("Item Sounds");
        this.enderPearlSounds = builder.comment("Whether ender pearls should play a sound when thrown.").define("Ender Pearl Sounds", true);
        this.spawnEggSounds = builder.comment("Whether spawns eggs should play a cracking sound when a mob is spawned using one.").define("Spawn Egg Sounds", true);
        this.leadSounds = builder.comment("Whether leads should make a sound when attached to a mob.").define("Lead Attaching Sounds", true);
        this.boatSounds = builder.comment("Whether boats should play a sound when placed.").define("Boat Sounds", true);
        this.minecartSounds = builder.comment("Whether minecarts should play a sound when placed.").define("Minecart Sounds", true);
        builder.pop();

        builder.push("Tool and Weapon Sounds");
        this.fistSwingingSounds = builder.comment("Whether a sound should play when a player punches with an empty hand.").define("Empty Hand Swinging Sounds", true);
        this.swordSwingingSounds = builder.comment("Whether a sound should play when a player punches with a sword.").define("Sword Swinging Sounds", true);
        this.axeSwingingSounds = builder.comment("Whether a sound should play when a player punches with an axe.").define("Axe Swinging Sounds", true);
        this.pickaxeSwingingSounds = builder.comment("Whether a sound should play when a player punches with a pickaxe.").define("Pickaxe Swinging Sounds", true);
        this.shovelSwingingSounds = builder.comment("Whether a sound should play when a player punches with a shovel.").define("Shovel Swinging Sounds", true);
        this.hoeSwingingSounds = builder.comment("Whether a sound should play when a player punches with a hoe.").define("Hoe Swinging Sounds", true);
        this.tridentSwingingSounds = builder.comment("Whether a sound should play when a player punches with a trident.").define("Trident Swinging Sounds", true);
        this.shearSwingingSounds = builder.comment("Whether a sound should play when a player uses shears.").define("Shear Swinging Sounds", true);
        this.bowPullbackSounds = builder.comment("Whether a sound should play when a player pulls back a bow.").define("Bow Pullback Sounds", true);
        this.tridentPullbackSounds = builder.comment("Whether a sound should play when a player pulls back a trident.").define("Trident Pullback Sounds", true);
        this.shieldBlockingSounds = builder.comment("Whether a sound should play when a player puts their shield up").define("Shield Blocking Sounds", true);
        builder.pop();

        builder.push("Misc Sounds");
        this.silverfishStepSounds = builder.comment("Whether Silverfish and Endermites should use their unused stepping sounds.").define("Silverfish/Endermite Step Sounds", true);
        this.itemDropSounds = builder.comment("Whether a plopping sound should play when an item is dropped.").define("Item Drop Sounds", true);
        builder.pop();
    }
}
