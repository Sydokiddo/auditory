package net.sydokiddo.auditory.core;

import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.lifecycle.TickEvents;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.KeybindRegistry;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.sydokiddo.auditory.core.mixin.SoundEngineAccessor;
import net.sydokiddo.auditory.core.registry.AuditorySounds;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class Auditory {

    public static final String MOD_ID = "auditory";
    public static final AuditoryClientConfig CLIENT_CONFIG = ConfigManager.register(MOD_ID, PollinatedConfigType.CLIENT, AuditoryClientConfig::new);
    private static final KeyMapping reloadKey = new KeyMapping("auditory.sound_reload_key", GLFW.GLFW_KEY_UNKNOWN, "auditory.category");
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Auditory::clientInit)
            .commonInit(Auditory::commonInit)
            .build();

    public static void clientInit() {
        KeybindRegistry.register(reloadKey);
        TickEvents.CLIENT_POST.register(() -> {
            if (reloadKey.isDown()) {
                ((SoundEngineAccessor) Minecraft.getInstance().getSoundManager()).getSoundEngine().reload();
                Minecraft mc = Minecraft.getInstance();
                mc.gui.getChat().addMessage(new TranslatableComponent("auditory.sound_reload_message"));
            }
        });
    }

    public static void commonInit() {
        LOGGER.info("Thank you for downloading Auditory! :)");
        AuditorySounds.SOUNDS.register(PLATFORM);
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
