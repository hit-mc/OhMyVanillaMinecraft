package com.keuin.ohmyvanillamc.mixins.rule.reintroduceLlamaItemDuping;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Reintroduce MC-161754 glitch
 */
@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @Redirect(
            method = "remove",
            at = @At(
                    value = "FIELD",
                    opcode = Opcodes.PUTFIELD,
                    target = "Lnet/minecraft/entity/Entity;removed:Z"
            ),
            require = 2
    )
    private void Entity_setRemoved(Entity entity, boolean value) {
//        OmvmExtension.LOGGER.info("set entity remove!");
        entity.removed = !OmvmSettings.reintroduceLlamaItemDuplicating || entity.removed;
    }
}
