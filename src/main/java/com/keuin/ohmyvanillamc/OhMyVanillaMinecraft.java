package com.keuin.ohmyvanillamc;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.network.EntityTrackerEntry;

import java.lang.reflect.Field;

public class OhMyVanillaMinecraft implements ModInitializer {

	public static boolean disableFishSchooling = true;
	public static boolean disablePhantomSpawning = false;
	public static boolean disableWanderingTraderSpawning = false;

	private static void disableEntityTrackerEntrySpamming() {
		try {
			//获取该类的字节码对象
			Class<EntityTrackerEntry> clazz = EntityTrackerEntry.class;

			//获取其私有成员
			Field name = clazz.getDeclaredField("LOGGER");

			//暴力反射
			name.setAccessible(true);

			//重新赋值  参数一：实力  参数二：值
//			name.set(EntityTrackerEntry, "小鸭鸭");
		} catch (NoSuchFieldException ignored) {
			// never mind
		}
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("OhMyVanillaMinecraft is loading...");

	}
}
