package com.keuin.ohmyvanillamc;

import net.fabricmc.api.ModInitializer;

public class OhMyVanillaMinecraft implements ModInitializer {

	public static boolean disableFishSchooling = true;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("OhMyVanillaMinecraft is loading...");
	}
}
