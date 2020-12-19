package com.keuin.ohmyvanillamc.mixin;

import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(WanderingTraderManager.class)
public class DisableWanderingTraderSpawning {

    /**
     * @reason disable ticking
     * @author trueKeuin
     */
    @Overwrite
    public void tick() {
    }

}
