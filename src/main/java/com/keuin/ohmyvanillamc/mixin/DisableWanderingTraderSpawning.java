package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderManager.class)
public class DisableWanderingTraderSpawning {
    /**
     * Disable ticking
     *
     * @author trueKeuin
     */
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    public void tick(CallbackInfoReturnable<Integer> ci) {
        if (OhMyVanillaMinecraft.getConfiguration().isDisableWanderingTraderSpawning()) {
            ci.setReturnValue(0);
            ci.cancel();
        }
    }
}
