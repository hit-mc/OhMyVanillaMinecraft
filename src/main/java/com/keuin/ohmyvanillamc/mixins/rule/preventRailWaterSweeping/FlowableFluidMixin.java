package com.keuin.ohmyvanillamc.mixins.rule.preventRailWaterSweeping;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FlowableFluid.class)
public class FlowableFluidMixin {

    @Inject(
            method = "flow",
            at = @At("HEAD"),
            cancellable = true
    )
    private void flow(WorldAccess world, BlockPos pos, BlockState state, Direction direction, FluidState fluidState, CallbackInfo ci){
        if(OmvmSettings.preventRailWaterSweeping && state.getBlock() instanceof AbstractRailBlock)
            ci.cancel();
    }

}
