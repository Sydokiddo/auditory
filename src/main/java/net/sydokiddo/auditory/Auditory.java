package net.sydokiddo.auditory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sydokiddo.auditory.misc.config.ModConfig;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Auditory.MOD_ID)
public class Auditory {

    public static final String MOD_ID = "auditory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).get();

    public Auditory() {
        ModSoundEvents.SOUND_EVENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        // Registry:
        ModSoundEvents.registerSounds();
        LOGGER.info("Thank you for downloading Auditory! :)");

        // registering config gui
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
            () -> new ConfigGuiHandler.ConfigGuiFactory((minecraft, screen) -> AutoConfig.getConfigScreen(ModConfig.class, screen).get()));
    }

    public static ModConfig getConfig() {
        return CONFIG;
    }
}