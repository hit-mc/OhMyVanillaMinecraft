package com.keuin.ohmyvanillamc;

import carpet.settings.Condition;
import carpet.settings.Rule;
import me.fallenbreath.conditionalmixin.api.util.VersionChecker;

import static carpet.settings.RuleCategory.*;

public class OmvmSettings {
    public static final String OMVM = "OMVM";

    public static final String PORTING = "porting";

    @Rule(
            desc = "Remove AI of laggy schooling fish (tropical fish, cod)",
            category = {OMVM, FEATURE, OPTIMIZATION}
    )
    public static boolean disableFishSchooling = false;

    @Rule(
            desc = "Reintroduce item duping with llama on a boat",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean reintroduceLlamaItemDuplicating = false;

    @Rule(
            desc = "Reintroduce force growth for kelp, twisted vine and weeping vine, see MC-113809",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean enableStemForceRipening = false;

    @Rule(
            desc = "Reintroduce force growth for bamboo, see MC-113809",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean enableBambooForceRipening = false;

    @Rule(
            desc = "Reintroduce force growth for cactus, See MC-113809",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean enableCactusForceRipening = false;

    @Rule(
            desc = "Reintroduce force growth for chorus flower, See MC-113809",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean enableChorusFlowerForceRipening = false;

    @Rule(
            desc = "Reintroduce force growth for sugar cane, See MC-113809",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean enableSugarCaneForceRipening = false;

    @Rule(
            desc = "Backported from 1.19, drops specialized minecarts as single items rather than separate components.",
            category = {OMVM, FEATURE, PORTING},
            condition = dropMinecartAsOneItemCondition.class
    )
    public static boolean dropMinecartAsOneItem = false;
    private static class dropMinecartAsOneItemCondition implements Condition {
        @Override
        public boolean isTrue() {
            return VersionChecker.doesModVersionSatisfyPredicate("carpet-tis-addition", "<1.50");
        }
    }

    @Rule(
            desc = "Backported from 1.17, All shulker boxes now drop their contents when destroyed as an item entity.",
            category = {OMVM, FEATURE, PORTING},
            condition = dropShulkerBoxContentsCondition.class
    )
    public static boolean dropShulkerBoxContents = false;
    private static class dropShulkerBoxContentsCondition implements Condition {
        @Override
        public boolean isTrue() {
            return VersionChecker.doesModVersionSatisfyPredicate("carpet-tis-addition", "<1.59");
        }
    }

    @Rule(
            desc = "Backported from 1.17, makes rails resistant to water flow.",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean preventRailWaterSweeping = false;

    @Rule(
            desc = "Backported from 1.17, allows shulkers to spawn new shulkers when hitting each other with bullets.",
            category = {OMVM, FEATURE, EXPERIMENTAL, PORTING}
    )
    public static boolean enableShulkerReproduction = false;

    @Rule(
            desc = "Fixes shulkers incorrectly trying to avoid or move away from nearby shulkers, see MC-183884.",
            category = {OMVM, FEATURE, PORTING}
    )
    public static boolean fixShulkerAvoidance = false;
}
