package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.entity.passive.SchoolingFishEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SchoolingFishEntity.class)
public class DisableFishSchooling {
    /**
     * @reason To disable SchoolingFish schooling.
     * @author trueKeuin
     */
    @Inject(method = "moveTowardLeader()V", at = @At("HEAD"))
    protected void moveTowardLeader(CallbackInfo ci) {
        if (OhMyVanillaMinecraft.disableFishSchooling)
            ci.cancel();
    }
}
