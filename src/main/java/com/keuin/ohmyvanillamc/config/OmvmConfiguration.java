package com.keuin.ohmyvanillamc.config;

import java.util.Objects;

public class OmvmConfiguration {

    private boolean fixEntityTrackerEntrySpamming;
    private boolean disableFishSchooling;
    private boolean disablePhantomSpawning;
    private boolean disableWanderingTraderSpawning;
    private boolean reintroduceLlamaItemDuplicating;
    private boolean reintroduceZeroTickFarm;
    private boolean enableStemForceRipening;
    private boolean enableBambooForceRipening;
    private boolean enableCactusForceRipening;
    private boolean enableChorusFlowerForceRipening;
    private boolean enableSugarCaneForceRipening;

    public OmvmConfiguration() {
        this(true, false, false, false, false, false, true, true, true, true, true);
    }

    public OmvmConfiguration(OmvmConfiguration omvmConfiguration) {
        this.fixEntityTrackerEntrySpamming = omvmConfiguration.fixEntityTrackerEntrySpamming;
        this.disableFishSchooling = omvmConfiguration.disableFishSchooling;
        this.disablePhantomSpawning = omvmConfiguration.disablePhantomSpawning;
        this.disableWanderingTraderSpawning = omvmConfiguration.disableWanderingTraderSpawning;
        this.reintroduceLlamaItemDuplicating = omvmConfiguration.reintroduceLlamaItemDuplicating;
        this.reintroduceZeroTickFarm = omvmConfiguration.reintroduceZeroTickFarm;
        this.enableStemForceRipening = omvmConfiguration.enableStemForceRipening;
        this.enableBambooForceRipening = omvmConfiguration.enableBambooForceRipening;
        this.enableCactusForceRipening = omvmConfiguration.enableCactusForceRipening;
        this.enableChorusFlowerForceRipening = omvmConfiguration.enableChorusFlowerForceRipening;
        this.enableSugarCaneForceRipening = omvmConfiguration.enableSugarCaneForceRipening;
    }

    public OmvmConfiguration(boolean fixEntityTrackerEntrySpamming, boolean disableFishSchooling
            , boolean disablePhantomSpawning, boolean disableWanderingTraderSpawning
            , boolean reintroduceLlamaItemDuplicating, boolean reintroduceZeroTickFarm
            , boolean enableStemForceRipening, boolean enableBambooForceRipening
            , boolean enableCactusForceRipening, boolean enableChorusFlowerForceRipening
            , boolean enableSugarCaneForceRipening) {
        this.fixEntityTrackerEntrySpamming = fixEntityTrackerEntrySpamming;
        this.disableFishSchooling = disableFishSchooling;
        this.disablePhantomSpawning = disablePhantomSpawning;
        this.disableWanderingTraderSpawning = disableWanderingTraderSpawning;
        this.reintroduceLlamaItemDuplicating = reintroduceLlamaItemDuplicating;
        this.reintroduceZeroTickFarm = reintroduceZeroTickFarm;
        this.enableStemForceRipening = enableStemForceRipening;
        this.enableBambooForceRipening = enableBambooForceRipening;
        this.enableCactusForceRipening = enableCactusForceRipening;
        this.enableChorusFlowerForceRipening = enableChorusFlowerForceRipening;
        this.enableSugarCaneForceRipening = enableSugarCaneForceRipening;
    }

    public boolean isFixEntityTrackerEntrySpamming() {
        return fixEntityTrackerEntrySpamming;
    }

    public boolean isDisableFishSchooling() {
        return disableFishSchooling;
    }

    public boolean isDisablePhantomSpawning() {
        return disablePhantomSpawning;
    }

    public boolean isDisableWanderingTraderSpawning() {
        return disableWanderingTraderSpawning;
    }

    public boolean isReintroduceLlamaItemDuplicating() {
        return reintroduceLlamaItemDuplicating;
    }

    public boolean isReintroduceZeroTickFarm() {
        return reintroduceZeroTickFarm;
    }

    public boolean isEnableStemForceRipening() {
        return enableStemForceRipening;
    }

    public void setEnableStemForceRipening(boolean enableStemForceRipening) {
        this.enableStemForceRipening = enableStemForceRipening;
    }

    public boolean isEnableBambooForceRipening() {
        return enableBambooForceRipening;
    }

    public void setEnableBambooForceRipening(boolean enableBambooForceRipening) {
        this.enableBambooForceRipening = enableBambooForceRipening;
    }

    public boolean isEnableCactusForceRipening() {
        return enableCactusForceRipening;
    }

    public void setEnableCactusForceRipening(boolean enableCactusForceRipening) {
        this.enableCactusForceRipening = enableCactusForceRipening;
    }

    public boolean isEnableChorusFlowerForceRipening() {
        return enableChorusFlowerForceRipening;
    }

    public void setEnableChorusFlowerForceRipening(boolean enableChorusFlowerForceRipening) {
        this.enableChorusFlowerForceRipening = enableChorusFlowerForceRipening;
    }

    public boolean isEnableSugarCaneForceRipening() {
        return enableSugarCaneForceRipening;
    }

    public void setEnableSugarCaneForceRipening(boolean enableSugarCaneForceRipening) {
        this.enableSugarCaneForceRipening = enableSugarCaneForceRipening;
    }

    public void setFixEntityTrackerEntrySpamming(boolean fixEntityTrackerEntrySpamming) {
        this.fixEntityTrackerEntrySpamming = fixEntityTrackerEntrySpamming;
    }

    public void setDisableFishSchooling(boolean disableFishSchooling) {
        this.disableFishSchooling = disableFishSchooling;
    }

    public void setDisablePhantomSpawning(boolean disablePhantomSpawning) {
        this.disablePhantomSpawning = disablePhantomSpawning;
    }

    public void setDisableWanderingTraderSpawning(boolean disableWanderingTraderSpawning) {
        this.disableWanderingTraderSpawning = disableWanderingTraderSpawning;
    }

    public void setReintroduceLlamaItemDuplicating(boolean reintroduceLlamaItemDuplicating) {
        this.reintroduceLlamaItemDuplicating = reintroduceLlamaItemDuplicating;
    }

    public void setReintroduceZeroTickFarm(boolean reintroduceZeroTickFarm) {
        this.reintroduceZeroTickFarm = reintroduceZeroTickFarm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OmvmConfiguration that = (OmvmConfiguration) o;
        return fixEntityTrackerEntrySpamming == that.fixEntityTrackerEntrySpamming &&
                disableFishSchooling == that.disableFishSchooling &&
                disablePhantomSpawning == that.disablePhantomSpawning &&
                disableWanderingTraderSpawning == that.disableWanderingTraderSpawning &&
                reintroduceLlamaItemDuplicating == that.reintroduceLlamaItemDuplicating &&
                reintroduceZeroTickFarm == that.reintroduceZeroTickFarm &&
                enableStemForceRipening == that.enableStemForceRipening &&
                enableBambooForceRipening == that.enableBambooForceRipening &&
                enableCactusForceRipening == that.enableCactusForceRipening &&
                enableChorusFlowerForceRipening == that.enableChorusFlowerForceRipening &&
                enableSugarCaneForceRipening == that.enableSugarCaneForceRipening;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixEntityTrackerEntrySpamming, disableFishSchooling, disablePhantomSpawning, disableWanderingTraderSpawning, reintroduceLlamaItemDuplicating, reintroduceZeroTickFarm, enableStemForceRipening, enableBambooForceRipening, enableCactusForceRipening, enableChorusFlowerForceRipening, enableSugarCaneForceRipening);
    }

    @Override
    public String toString() {
        String s = "";
        s += "(BugFix) Fix Entity Tracker Entry Spamming: " + fixEntityTrackerEntrySpamming + "\n";
        s += "(Optimization) Disable Fish Schooling: " + disableFishSchooling + "\n";
        s += "(Exotic Feature) Disable Phantom Spawning: " + disablePhantomSpawning + "\n";
        s += "(Exotic Feature) Disable Wandering Trader Spawning: " + disableWanderingTraderSpawning + "\n";
        s += "(Obsolete Vanilla Feature) Reintroduce Llama Item Duplicating: " + reintroduceLlamaItemDuplicating + "\n";
        s += "(Obsolete Vanilla Feature) Reintroduce 0-tick Plants Farm: " + reintroduceZeroTickFarm + "\n";
        s += "(Zero Tick Farm) Enable Stem Force Ripening: " + enableStemForceRipening + "\n";
        s += "(Zero Tick Farm) Enable Bamboo Force Ripening: " + enableBambooForceRipening + "\n";
        s += "(Zero Tick Farm) Enable Cactus Force Ripening: " + enableCactusForceRipening + "\n";
        s += "(Zero Tick Farm) Enable Chorus Flower Force Ripening: " + enableCactusForceRipening + "\n";
        s += "(Zero Tick Farm) Enable Sugar Cane Force Ripening: " + enableSugarCaneForceRipening + "\n";
        return s;
    }
}
