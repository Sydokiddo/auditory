package net.sydokiddo.auditory.core.forge;

import net.minecraftforge.fml.common.Mod;
import net.sydokiddo.auditory.core.Auditory;

@Mod(Auditory.MOD_ID)
public class AuditoryForge {

    public AuditoryForge() {
        Auditory.PLATFORM.setup();
    }
}
