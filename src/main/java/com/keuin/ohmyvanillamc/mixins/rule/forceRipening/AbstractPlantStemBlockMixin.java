package com.keuin.ohmyvanillamc.mixins.rule.forceRipening;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.AbstractPlantPartBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(AbstractPlantStemBlock.class)
public abstract class AbstractPlantStemBlockMixin extends AbstractPlantPartBlock {

    protected AbstractPlantStemBlockMixin(Settings settings, Direction growthDirection, VoxelShape outlineShape, boolean tickWater) {
        super(settings, growthDirection, outlineShape, tickWater);
    }

    @Shadow
    @Final
    public static IntProperty AGE;

    @Shadow
    @Final
    private double growthChance;

    @Shadow
    protected abstract boolean chooseStemState(BlockState state);

    /**
     * @reason Revert to the super class's impl (Block's).
     * @author trueKeuin
     */
    @Overwrite
    public boolean hasRandomTicks(BlockState state) {
        return super.hasRandomTicks(state);
    }

    /**
     * Reintroduce the MC-113809 glitch for kelp, twisted vine and weeping vine. The implementation is identical to Minecraft 1.15.2.
     *
     * @author trueKeuin
     * @reason reintroduce MC-113809 for kelp, twisted vine and weeping vine.
     */
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (OmvmSettings.enableStemForceRipening) {
            realGrow(state, world, pos, random);
        }
    }

    /**
     * Revert to base class (Block) implementation of randomTick: just simply call scheduledTick.
     * (both 1.15.2 and 1.16.4 are the same)
     *
     * @author trueKeuin
     * @reason revert to the base class `Block` implementation.
     */
    @Overwrite
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (OmvmSettings.enableStemForceRipening) {
            scheduledTick(state, world, pos, random);
        } else {
            realGrow(state, world, pos, random);
        }
    }

    private void realGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(AGE) < 25 && random.nextDouble() < this.growthChance) {
            BlockPos blockPos = pos.offset(this.growthDirection);
            if (this.chooseStemState(world.getBlockState(blockPos))) {
                world.setBlockState(blockPos, state.cycle(AGE));
            }
        }
    }

}
