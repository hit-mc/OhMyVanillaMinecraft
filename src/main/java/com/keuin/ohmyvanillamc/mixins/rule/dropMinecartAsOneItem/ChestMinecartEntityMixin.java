package com.keuin.ohmyvanillamc.mixins.rule.dropMinecartAsOneItem;

import com.keuin.ohmyvanillamc.OmvmSettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.ChestMinecartEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(
        require = {
                @Condition(value = "carpet-tis-addition", versionPredicates = "<1.50"),
        }
)
@Mixin(ChestMinecartEntity.class)
public class ChestMinecartEntityMixin {
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
