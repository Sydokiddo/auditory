package net.sydokiddo.auditory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.sydokiddo.auditory.misc.config.ModConfig;
import net.sydokiddo.auditory.sound.ModSoundEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Auditory implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("auditory");
	public static final String MOD_ID = "auditory";
	private static final ModConfig CONFIG = AutoConfig.register(ModConfig.class, GsonConfigSerializer::new).getConfig();

	@Override
	public void onInitialize() {

		// Registry:

		ModSoundEvents.registerSounds();

		LOGGER.info("Thank you for downloading Auditory! :)");
	}

	public static ModConfig getConfig () {
		return CONFIG;
	}
}