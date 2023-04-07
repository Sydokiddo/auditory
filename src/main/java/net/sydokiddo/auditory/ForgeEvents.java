package net.sydokiddo.auditory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Auditory.MOD_ID, value = Dist.CLIENT)
public class ForgeEvents {

    @SubscribeEvent
    @SuppressWarnings("unused")
    public static void tick(final TickEvent.ClientTickEvent evt) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        if (player != null && evt.phase == TickEvent.Phase.END && client.isWindowActive() &&
            ModEvents.RELOADKEY.consumeClick()) {
            Minecraft.getInstance().getSoundManager().reload();
            client.gui.getChat().addMessage(Component.translatable("auditory.sound_reload_message"));
        }
    }
}