package com.keuin.ohmyvanillamc.mixins.rule.dropMinecartAsOneItem;

import com.keuin.ohmyvanillamc.OmvmSettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.entity.vehicle.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Restriction(
        require = {
                @Condition(value = "carpet-tis-addition", versionPredicates = "<1.50"),
        }
)
@Mixin(AbstractMinecartEntity.class)
public class AbstractMinecartEntityMixin {
    @SuppressWarnings("UnreachableCode")
    @ModifyArg(
            method = "dropItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;<init>(Lnet/minecraft/item/ItemConvertible;)V"
            ),
            index = 0
    )
    private ItemConvertible getMinecartItem(ItemConvertible i){
        if (!OmvmSettings.dropMinecartAsOneItem) return i;
        AbstractMinecartEntity $this = (AbstractMinecartEntity) (Object) this;
        if ($this instanceof TntMinecartEntity){
            return Items.TNT_MINECART;
        } else if ($this instanceof HopperMinecartEntity){
            return Items.HOPPER_MINECART;
        } else if($this instanceof ChestMinecartEntity){
            return Items.CHEST_MINECART;
        } else if($this instanceof FurnaceMinecartEntity){
            return Items.FURNACE_MINECART;
        } else {
            return i;
        }
    }
}
