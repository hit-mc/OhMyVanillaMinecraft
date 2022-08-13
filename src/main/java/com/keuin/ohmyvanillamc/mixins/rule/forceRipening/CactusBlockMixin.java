package com.keuin.ohmyvanillamc.mixins.rule.forceRipening;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CactusBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(CactusBlock.class)
public abstract class CactusBlockMixin extends Block {

    public CactusBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    @Final
    public static IntProperty AGE;

    /**
     * Revert to base class (Block) implementation of randomTick: just simply call scheduledTick.
     * (both 1.15.2 and 1.16.4 are the same)
     *
     * @author trueKeuin
     * @reason revert to the base class `Block` implementation.
     */
    @Overwrite
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (OmvmSettings.enableCactusForceRipening) {
            scheduledTick(state, world, pos, random);
        } else {
            // here goes 1.16.4 version randomTick impl.
            realGrow(state, world, pos);
        }
    }

    /**
     * Reintroduce the MC-113809 glitch for cactus. The implementation is identical to Minecraft 1.15.2.
     *
     * @author trueKeuin
     * @reason reintroduce MC-113809 for cactus.
     */
    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (OmvmSettings.enableCactusForceRipening) {
            realGrow(state, world, pos);
        }
    }

    private void realGrow(BlockState state, ServerWorld world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos)) {
            int i;
            for (i = 1; world.getBlockState(pos.down(i)).isOf(this); ++i) {
            }

            if (i < 3) {
                int j = state.get(AGE);
                if (j == 15) {
                    world.setBlockState(blockPos, this.getDefaultState());
                    BlockState blockState = state.with(AGE, 0);
                    world.setBlockState(pos, blockState, 4);
                    blockState.neighborUpdate(world, blockPos, this, pos, false);
                } else {
                    world.setBlockState(pos, state.with(AGE, j + 1), 4);
                }

            }
        }
    }
}
