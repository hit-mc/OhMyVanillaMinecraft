package com.keuin.ohmyvanillamc;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OmvmExtension implements CarpetExtension {
    private static final OmvmExtension INSTANCE = new OmvmExtension();

    public static final Logger LOGGER = LogManager.getLogger("OMVM");
    public static OmvmExtension getInstance() {
        return INSTANCE;
    }

    public static void initialize() {
        CarpetServer.manageExtension(INSTANCE);
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(OmvmSettings.class);
    }

    @Override
    public String version() {
        return OhMyVanillaMinecraft.getVersion();
    }
}
