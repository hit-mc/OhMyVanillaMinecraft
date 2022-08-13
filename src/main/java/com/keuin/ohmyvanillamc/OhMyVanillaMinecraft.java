package com.keuin.ohmyvanillamc;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class OhMyVanillaMinecraft implements ModInitializer {

    private static final String MOD_ID = "ohmyvanillamc";

    private static String version;

    public static String getVersion() {
        return version;
    }

    @Override
    public void onInitialize() {
        version = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
        OmvmExtension.initialize();
    }

}
