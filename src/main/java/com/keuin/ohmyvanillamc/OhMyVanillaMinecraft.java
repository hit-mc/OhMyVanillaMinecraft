package com.keuin.ohmyvanillamc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.network.EntityTrackerEntry;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class OhMyVanillaMinecraft implements ModInitializer {

	private static final Logger LOGGER = Logger.getLogger("OhMyVanillaMinecraft");

	private static OmvmConfiguration configuration = null;
	private static final OmvmConfiguration defaultConfiguration = new OmvmConfiguration(
			false,
			false,
			false,
			false
	);

	public static OmvmConfiguration getConfiguration() {
		return configuration != null ? configuration : defaultConfiguration;
	}

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

		LOGGER.info("Loading configuration...");

		// load configuration

		final String fileName = "omvm.json";
		final File file = new File(fileName);
		if (!file.exists()) {
			LOGGER.info("Configuration file does not exist! Use default configuration.");
			try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
				final String jsonString = (new GsonBuilder().setPrettyPrinting().create()).toJson(defaultConfiguration);
				try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
					outputStreamWriter.write(jsonString);
				}
			} catch (FileNotFoundException e) {
				LOGGER.severe("Cannot write default configuration to file `" + fileName + "`: " + e);
			} catch (IOException e) {
				LOGGER.severe("Failed to write default configuration to file `" + fileName + "`: " + e);
			}
		} else {
			try (FileInputStream fileInputStream = new FileInputStream(file)) {
				try (InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8)) {
					try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
						configuration = (new Gson()).fromJson(reader, OmvmConfiguration.class);
					}
				}
			} catch (IOException e) {
				LOGGER.severe("Failed to read config file `" + fileName + "`: " + e);
			} catch (JsonIOException e) {
				LOGGER.severe("Failed to decode config json file `" + fileName + "`: " + e);
			}
		}

		if (configuration == null) {
			configuration = defaultConfiguration;
		}

		LOGGER.info("Configuration: \n==========\n" + configuration + "\n==========");

	}
}
