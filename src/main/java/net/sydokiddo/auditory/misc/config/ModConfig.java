package net.sydokiddo.auditory.misc.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.sydokiddo.auditory.misc.config.options.IndividualBlockSoundsConfig;

// Mod Config

@Config(name = "auditory")
public class ModConfig implements ConfigData {

    // Block Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public BlockSounds block_sounds = new BlockSounds();

    public static class BlockSounds {

        @ConfigEntry.Gui.CollapsibleObject()
        @ConfigEntry.Gui.Tooltip
        public IndividualBlockSoundsConfig blockSoundsConfig = new IndividualBlockSoundsConfig();

        @ConfigEntry.Gui.Tooltip
        public boolean falling_in_place_sound = true;

        @ConfigEntry.Gui.Tooltip
        public boolean jukebox_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean flower_pot_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean cake_eating_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean sculk_shrieker_ambient_sounds = true;
    }

    // Item Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public ItemSounds item_sounds = new ItemSounds();

    public static class ItemSounds {

        @ConfigEntry.Gui.Tooltip
        public boolean ender_pearl_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean spawn_egg_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean lead_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean boat_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean minecart_sounds = true;
    }

    // Weapon Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public WeaponSounds weapon_sounds = new WeaponSounds();

    public static class WeaponSounds {

        @ConfigEntry.Gui.Tooltip
        public boolean fist_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean sword_swinging_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean axe_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean pickaxe_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean shovel_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean hoe_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean trident_swinging_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean shear_swinging_sounds = false;

        @ConfigEntry.Gui.Tooltip
        public boolean bow_pullback_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean trident_pullback_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean shield_blocking_sounds = true;
    }

    // Misc Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public MiscSounds misc_sounds = new MiscSounds();

    public static class MiscSounds {

        @ConfigEntry.Gui.Tooltip
        public boolean silverfish_step_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean item_drop_sounds = true;
    }
}