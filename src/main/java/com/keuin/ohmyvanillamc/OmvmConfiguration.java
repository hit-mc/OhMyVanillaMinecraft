package com.keuin.ohmyvanillamc;

import java.util.Objects;

public class OmvmConfiguration {

    private final boolean disableFishSchooling;
    private final boolean disablePhantomSpawning;
    private final boolean disableWanderingTraderSpawning;
    private final boolean reintroduceLlamaItemDuplicating;

    public OmvmConfiguration(boolean disableFishSchooling, boolean disablePhantomSpawning, boolean disableWanderingTraderSpawning, boolean reintroduceLlamaItemDuplicating) {
        this.disableFishSchooling = disableFishSchooling;
        this.disablePhantomSpawning = disablePhantomSpawning;
        this.disableWanderingTraderSpawning = disableWanderingTraderSpawning;
        this.reintroduceLlamaItemDuplicating = reintroduceLlamaItemDuplicating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OmvmConfiguration that = (OmvmConfiguration) o;
        return disableFishSchooling == that.disableFishSchooling &&
                disablePhantomSpawning == that.disablePhantomSpawning &&
                disableWanderingTraderSpawning == that.disableWanderingTraderSpawning &&
                reintroduceLlamaItemDuplicating == that.reintroduceLlamaItemDuplicating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(disableFishSchooling, disablePhantomSpawning, disableWanderingTraderSpawning, reintroduceLlamaItemDuplicating);
    }

    @Override
    public String toString() {
        return "Disable Fish Schooling: " + disableFishSchooling + "\n" +
                "Disable Phantom Spawning: " + disablePhantomSpawning + "\n" +
                "Disable Wandering Trader Spawning: " + disableWanderingTraderSpawning + "\n" +
                "Reintroduce Llama Item Duplicating: " + reintroduceLlamaItemDuplicating;
    }
}
