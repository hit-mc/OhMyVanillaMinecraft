package com.keuin.ohmyvanillamc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.keuin.ohmyvanillamc.config.ImmutableOmvmConfiguration;
import com.keuin.ohmyvanillamc.config.OmvmConfiguration;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static com.keuin.ohmyvanillamc.ReflectionUtils.getFieldName;
import static com.keuin.ohmyvanillamc.ReflectionUtils.setPrivateField;

public class OhMyVanillaMinecraft implements ModInitializer {

    private static final Logger LOGGER = Logger.getLogger("OhMyVanillaMinecraft");

    private static final OmvmConfiguration defaultConfiguration =
            new ImmutableOmvmConfiguration(true, true, true, true, true, true);
    private static OmvmConfiguration configuration = null;

    public static OmvmConfiguration getConfiguration() {
        return configuration;
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
            configuration = new OmvmConfiguration(defaultConfiguration);
        }

        LOGGER.info("Configuration: \n==========\n" + configuration + "\n==========");

        CommandRegistrationCallback.EVENT.register(new CommandRegistrationCallback() {
            @Override
            public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, boolean b) {
                commandDispatcher.register(CommandManager.literal("omvm").executes(new Command<ServerCommandSource>() {
                    @Override
                    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
                        String text = "OhMyVanillaMinecraft\n==========\n" + getConfiguration() + "\n==========";
                        context.getSource().sendFeedback(new LiteralText(text), false);
                        return 1; // 1: success, -1: fail
                    }
                }));
            }
        });


        String[] keys = getFieldName(configuration);
        final Set<String> keySet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(keys)));

        CommandRegistrationCallback.EVENT.register(new CommandRegistrationCallback() {
            @Override
            public void register(CommandDispatcher<ServerCommandSource> commandDispatcher, boolean b) {
                commandDispatcher.register(CommandManager.literal("omvm")
                        .then(CommandManager.literal("set")
                                .then(CommandManager.argument("key", StringArgumentType.word())
                                        .suggests(new SuggestionProvider<ServerCommandSource>() {
                                            @Override
                                            public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
                                                String remaining = builder.getRemaining().toLowerCase(Locale.ROOT);
                                                keySet.stream().filter(key -> key.toLowerCase().startsWith(remaining)).forEach(builder::suggest);
                                                return builder.buildFuture();
                                            }
                                        })
                                        .then(CommandManager.argument("value", StringArgumentType.word()).suggests(new SuggestionProvider<ServerCommandSource>() {
                                                    @Override
                                                    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
                                                        String remaining = builder.getRemaining().toLowerCase(Locale.ROOT);
                                                        Stream.of("true", "false").filter(key -> key.toLowerCase().startsWith(remaining)).forEach(builder::suggest);
                                                        return builder.buildFuture();
                                                    }
                                                })
                                                        .executes(new Command<ServerCommandSource>() {
                                                            @Override
                                                            public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
                                                                try {
                                                                    String key = context.getArgument("key", String.class);
                                                                    String value = context.getArgument("value", String.class);

                                                                    if (!keySet.contains(key)) {
                                                                        StringBuilder sb = new StringBuilder();
                                                                        sb.append("Invalid key. Available keys:");
                                                                        keySet.forEach(k -> sb.append("\n- ").append(k));
                                                                        context.getSource().sendFeedback(new LiteralText(sb.toString())
                                                                                .setStyle(Style.EMPTY.withColor(Formatting.RED)), false);
                                                                        return -1;
                                                                    }

                                                                    if (!Arrays.asList("true", "false").contains(value)) {
                                                                        context.getSource().sendFeedback(new LiteralText("Value must be `true` or `false`.")
                                                                                .setStyle(Style.EMPTY.withColor(Formatting.RED)), false);
                                                                        return -1;
                                                                    }

                                                                    setPrivateField(configuration, key, Boolean.valueOf(value));
                                                                    context.getSource().sendFeedback(
                                                                            new LiteralText("`" + key + "` has been temporarily set to `" + value + "`."), true
                                                                    );

                                                                    return 1; // 1: success, -1: fail
                                                                } catch (NoSuchFieldException | IllegalAccessException e) {
                                                                    context.getSource().sendFeedback(new LiteralText("There is a bug. Tell trueKeuin.")
                                                                            .setStyle(Style.EMPTY.withColor(Formatting.RED)), false);
                                                                    return -1;
                                                                }
                                                            }
                                                        })
                                        )
                                )));
            }
        });

    }


}
