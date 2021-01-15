package com.keuin.ohmyvanillamc;

import java.util.Objects;

public class OmvmConfiguration {

    private final boolean fixEntityTrackerEntrySpamming;
    private final boolean disableFishSchooling;
    private final boolean disablePhantomSpawning;
    private final boolean disableWanderingTraderSpawning;
    private final boolean reintroduceLlamaItemDuplicating;
    private final boolean reintroduceZeroTickFarm;

    public OmvmConfiguration() {
        this(true, false, false, false, false, false);
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
