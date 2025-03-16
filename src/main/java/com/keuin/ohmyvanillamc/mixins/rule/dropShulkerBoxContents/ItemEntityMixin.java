package com.keuin.ohmyvanillamc.mixins.rule.dropShulkerBoxContents;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {

    @SuppressWarnings("UnreachableCode")
    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ItemEntity;remove()V"
            )
    )
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!OmvmSettings.dropShulkerBoxContents) return;
        ItemEntity $this = (ItemEntity) (Object) this;

        World world = $this.world;
        if (world.isClient) return;

        ItemStack itemStack = $this.getStack();
        Item item = itemStack.getItem();
        if (item instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) item;
            if (blockItem.getBlock() instanceof ShulkerBoxBlock) {
                NbtCompound nbtCompound = itemStack.getSubTag("BlockEntityTag");
                if (nbtCompound != null && nbtCompound.contains("Items", 9)) {
                    NbtList nbtList = nbtCompound.getList("Items", 10);
                    nbtList.stream()
                            .map(NbtCompound.class::cast)
                            .map(ItemStack::fromNbt)
                            .forEach(stack -> world.spawnEntity(new ItemEntity(world, $this.getX(), $this.getY(), $this.getZ(), stack)));
                }
            }
        }
    }

}
