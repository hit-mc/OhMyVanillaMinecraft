package com.keuin.ohmyvanillamc;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class OmvmSettings {
    public static final String OMVM = "OMVM";

    @Rule(
            desc = "Remove AI of laggy schooling fish (tropical fish, cod)",
            category = {OMVM, FEATURE, OPTIMIZATION}
    )
    public static boolean disableFishSchooling = false;

    @Rule(
            desc = "Reintroduce item duping with llama on a boat",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean reintroduceLlamaItemDuplicating = false;

    @Rule(
            desc = "Reintroduce Force Ripening for kelp, twisted vine and weeping vine, See MC-113809",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean enableStemForceRipening = false;

    @Rule(
            desc = "Reintroduce Force Ripening for bamboo, See MC-113809",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean enableBambooForceRipening = false;

    @Rule(
            desc = "Reintroduce Force Ripening for cactus, See MC-113809",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean enableCactusForceRipening = false;

    @Rule(
            desc = "Reintroduce Force Ripening for chorus flower, See MC-113809",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean enableChorusFlowerForceRipening = false;

    @Rule(
            desc = "Reintroduce Force Ripening for sugar cane, See MC-113809",
            category = {OMVM, FEATURE, EXPERIMENTAL}
    )
    public static boolean enableSugarCaneForceRipening = false;
}
