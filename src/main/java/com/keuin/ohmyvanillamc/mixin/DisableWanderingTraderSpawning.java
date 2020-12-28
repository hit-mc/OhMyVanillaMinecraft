package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WanderingTraderManager.class)
public class DisableWanderingTraderSpawning {
    /**
     * Disable ticking
     * @author trueKeuin
     */
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfo ci) {
        if (OhMyVanillaMinecraft.disableWanderingTraderSpawning)
            ci.cancel();
    }
}
