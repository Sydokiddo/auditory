package net.sydokiddo.auditory.core.fabric;

import net.fabricmc.api.ModInitializer;
import net.sydokiddo.auditory.core.Auditory;

public class AuditoryFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        Auditory.PLATFORM.setup();
    }
}
