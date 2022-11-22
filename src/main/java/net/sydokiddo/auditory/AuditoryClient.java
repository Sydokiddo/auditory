package net.sydokiddo.auditory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class AuditoryClient implements ClientModInitializer {

    // Keybindings:

    private static final KeyMapping reloadKey;

    static {
        reloadKey = new KeyMapping("auditory.sound_reload_key", GLFW.GLFW_KEY_UNKNOWN, "auditory.category");
    }

    // Reloads the sound engine when the registered keybinding is pressed

    @Override
    public void onInitializeClient() {

        KeyBindingHelper.registerKeyBinding(reloadKey);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (reloadKey.isDown()) {
                assert client.player != null;
                Minecraft.getInstance().getSoundManager().reload();
                Minecraft mc = Minecraft.getInstance();
                mc.gui.getChat().addMessage(Component.translatable("auditory.sound_reload_message"));
            }
        });
    }
}