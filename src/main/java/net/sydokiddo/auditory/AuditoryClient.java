package net.sydokiddo.auditory;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.sydokiddo.auditory.mixin.SoundEngineAccessor;
import org.lwjgl.glfw.GLFW;
public class AuditoryClient implements ClientModInitializer {

    // Keybindings:

    private static final KeyBinding reloadKey;

    static {
        reloadKey = new KeyBinding("auditory.sound_reload_key", GLFW.GLFW_KEY_UNKNOWN, "auditory.category");
    }

    // Reloads the sound engine when the registered keybinding is pressed

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(reloadKey);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (reloadKey.wasPressed()) {
                assert client.player != null;
                ((SoundEngineAccessor)MinecraftClient.getInstance().getSoundManager()).getSoundSystem().reloadSounds();
                MinecraftClient mc = MinecraftClient.getInstance();
                mc.inGameHud.getChatHud().addMessage(Text.of("Auditory: Reloaded sound system"));
            }
        });
    }
}