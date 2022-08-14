package com.keuin.ohmyvanillamc.mixins.rule.forceRipening;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin extends Block {

    public BambooBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    public abstract void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random);

    @Inject(
            method = "scheduledTick",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.AFTER,
                    target = "Lnet/minecraft/server/world/ServerWorld;breakBlock(Lnet/minecraft/util/math/BlockPos;Z)Z"
            ),
            cancellable = true
    )
    private void scheduleTick_mixin1(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (OmvmSettings.enableBambooForceRipening)
            ci.cancel();
    }

    @Inject(
            method = "scheduledTick",
            at = @At("TAIL")
    )
    private void scheduleTick_mixin2(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (OmvmSettings.enableBambooForceRipening)
            this.randomTick(state, world, pos, random);
    }
}
