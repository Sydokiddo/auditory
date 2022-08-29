package net.sydokiddo.auditory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

// Mod Config

@Config(name = "auditory")
public class ModConfig implements ConfigData {

    // Block Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public BlockSounds block_sounds = new BlockSounds();

    public static class BlockSounds {

        @ConfigEntry.Gui.Tooltip
        public boolean block_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean jukebox_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean flower_pot_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean cake_eating_sounds = true;
    }

    // Item Sounds

    @ConfigEntry.Gui.CollapsibleObject()
    public ItemSounds item_sounds = new ItemSounds();

    public static class ItemSounds {

        @ConfigEntry.Gui.Tooltip
        public boolean weapon_swing_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean bow_and_trident_pullback_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean ender_pearl_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean spawn_egg_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean boat_sounds = true;

        @ConfigEntry.Gui.Tooltip
        public boolean minecart_sounds = true;
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