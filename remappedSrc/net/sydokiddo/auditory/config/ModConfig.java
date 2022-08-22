package net.sydokiddo.auditory.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "auditory")
public class ModConfig implements ConfigData {

    @Comment("If true, will play updated block " +
            "breaking and placing sounds")
    public boolean block_sounds = true;

    @Comment("If true, will play item using sounds, " +
            "such as Bow sounds, Ender Pearl sounds, etc.")
    public boolean item_use_sounds = true;

    @Comment("If true, will play a popping sound " +
            "whenever an item is dropped")
    public boolean item_drop_sounds = true;

    @Comment("If true, will play sounds when interacting with " +
            "specific blocks such as Jukeboxes, Flower Pots, etc.")
    public boolean interaction_sounds = true;

    @Comment("If true, will play entity sounds such as " +
            "Silverfish slithering, Minecart/Boat placing, etc.")
    public boolean entity_sounds = true;
}