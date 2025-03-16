package com.keuin.ohmyvanillamc.mixins.rule.minecartDropItem;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.TntMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntMinecartEntity.class)
public class TntMinecartEntityMixin {
    @Inject(
            method = "dropItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
            ),
            cancellable = true
    )
    private void dropItems(DamageSource damageSource, CallbackInfo ci){
        if (OmvmSettings.dropMinecartAsOneItem)
            ci.cancel();
    }
}
