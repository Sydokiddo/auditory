package net.sydokiddo.auditory.core;

import gg.moonflower.pollen.api.platform.Platform;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Auditory {

    public static final String MOD_ID = "auditory";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Auditory::clientInit)
            .commonInit(Auditory::commonInit)
            .build();

    public static void clientInit() {
    }

    public static void commonInit() {
        LOGGER.info("Thank you for downloading Auditory! :)");
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
