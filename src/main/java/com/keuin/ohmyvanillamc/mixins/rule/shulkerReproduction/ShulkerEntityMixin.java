package com.keuin.ohmyvanillamc.mixins.rule.shulkerReproduction;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShulkerEntity.class)
public class ShulkerEntityMixin {

    @ModifyArg(
            method = "tryTeleport",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z"
            ),
            index = 1
    )
    private Box fixBox(Box b){
        return OmvmSettings.fixShulkerAvoidance ? b.contract(1.0E-6) : b;
    }

    @ModifyArg(
            method = "canStay",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;isSpaceEmpty(Lnet/minecraft/entity/Entity;Lnet/minecraft/util/math/Box;)Z"
            ),
            index = 1
    )
    private Box fixBox2(Box b){
        return OmvmSettings.fixShulkerAvoidance ? b.contract(1.0E-6) : b;
    }

    @Inject(
            method = "tryTeleport",
            at = @At(
                    value = "RETURN",
                    ordinal = 0
            ),
            cancellable = true
    )
    private void fix_tryTeleport(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
        cir.cancel();
    }

    @Inject(
            method = "damage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/ShulkerEntity;tryTeleport()Z",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    private void damage1(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(true);
        cir.cancel();
    }

    @SuppressWarnings("UnreachableCode")
    @Inject(
            method = "damage",
            at = @At(
                    value = "RETURN",
                    ordinal = 1
            )
    )
    private void damage2(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (OmvmSettings.enableShulkerReproduction && source.isProjectile()) {
            Entity entity = source.getSource();
            if (entity != null && entity.getType() == EntityType.SHULKER_BULLET) {
                ShulkerEntity $this = (ShulkerEntity) (Object) this;
                Vec3d vec3d = $this.getPos();
                Box box = $this.getBoundingBox();
                if (!$this.isClosed() && $this.tryTeleport()) {
                    int i = $this.world.getEntitiesByType(EntityType.SHULKER, box.expand(8.0), Entity::isAlive).size();
                    float f = (i - 1) / 5.0F;
                    if (!($this.world.random.nextFloat() < f)) {
                        ShulkerEntity shulkerEntity = EntityType.SHULKER.create($this.world);
                        DyeColor dyeColor = $this.getColor();
                        if (dyeColor != null) {
                            shulkerEntity.getDataTracker().set(ShulkerEntity.COLOR, (byte)dyeColor.getId());
                        }

                        shulkerEntity.refreshPositionAfterTeleport(vec3d);
                        $this.world.spawnEntity(shulkerEntity);
                    }
                }
            }
        }
    }
}
