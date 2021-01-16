package com.keuin.ohmyvanillamc.config;

import java.util.Objects;

public class OmvmConfiguration {

    private boolean fixEntityTrackerEntrySpamming;
    private boolean disableFishSchooling;
    private boolean disablePhantomSpawning;
    private boolean disableWanderingTraderSpawning;
    private boolean reintroduceLlamaItemDuplicating;
    private boolean reintroduceZeroTickFarm;

    public OmvmConfiguration() {
        this(true, false, false, false, false, false);
    }

    public OmvmConfiguration(OmvmConfiguration omvmConfiguration) {
        this.fixEntityTrackerEntrySpamming = omvmConfiguration.fixEntityTrackerEntrySpamming;
        this.disableFishSchooling = omvmConfiguration.disableFishSchooling;
        this.disablePhantomSpawning = omvmConfiguration.disablePhantomSpawning;
        this.disableWanderingTraderSpawning = omvmConfiguration.disableWanderingTraderSpawning;
        this.reintroduceLlamaItemDuplicating = omvmConfiguration.reintroduceLlamaItemDuplicating;
        this.reintroduceZeroTickFarm = omvmConfiguration.reintroduceZeroTickFarm;
    }

    public OmvmConfiguration(boolean fixEntityTrackerEntrySpamming, boolean disableFishSchooling, boolean disablePhantomSpawning, boolean disableWanderingTraderSpawning, boolean reintroduceLlamaItemDuplicating, boolean reintroduceZeroTickFarm) {
        this.fixEntityTrackerEntrySpamming = fixEntityTrackerEntrySpamming;
        this.disableFishSchooling = disableFishSchooling;
        this.disablePhantomSpawning = disablePhantomSpawning;
        this.disableWanderingTraderSpawning = disableWanderingTraderSpawning;
        this.reintroduceLlamaItemDuplicating = reintroduceLlamaItemDuplicating;
        this.reintroduceZeroTickFarm = reintroduceZeroTickFarm;
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
                reintroduceZeroTickFarm == that.reintroduceZeroTickFarm;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixEntityTrackerEntrySpamming, disableFishSchooling, disablePhantomSpawning, disableWanderingTraderSpawning, reintroduceLlamaItemDuplicating, reintroduceZeroTickFarm);
    }

    @Override
    public String toString() {
        String s = "";
        s += "(BugFix) Fix Entity Tracker Entry Spamming: " + fixEntityTrackerEntrySpamming + "\n";
        s += "(Optimization) Disable Fish Schooling: " + disableFishSchooling + "\n";
        s += "(Exotic Feature) Disable Phantom Spawning: " + disablePhantomSpawning + "\n";
        s += "(Exotic Feature) Disable Wandering Trader Spawning: " + disableWanderingTraderSpawning + "\n";
        s += "(Obsolete Vanilla Feature) Reintroduce Llama Item Duplicating: " + reintroduceLlamaItemDuplicating + "\n";
        s += "(Obsolete Vanilla Feature) Reintroduce 0-tick Plants Farm: " + reintroduceZeroTickFarm;
        return s;
    }
}
