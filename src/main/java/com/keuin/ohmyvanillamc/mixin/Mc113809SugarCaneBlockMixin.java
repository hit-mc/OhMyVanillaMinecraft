package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(SugarCaneBlock.class)
public abstract class Mc113809SugarCaneBlockMixin extends Block {

    public Mc113809SugarCaneBlockMixin(Settings settings) {
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
        if (OhMyVanillaMinecraft.getConfiguration().isReintroduceZeroTickFarm()) {
            scheduledTick(state, world, pos, random);
        } else if (world.isAir(pos.up())) { // here goes 1.16.4 version randomTick impl.
            int i;
            for (i = 1; world.getBlockState(pos.down(i)).isOf((SugarCaneBlock) (Object) this); ++i) {
            }

            if (i < 3) {
                int j = state.get(AGE);
                if (j == 15) {
                    world.setBlockState(pos.up(), this.getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0), 4);
                } else {
                    world.setBlockState(pos, state.with(AGE, j + 1), 4);
                }
            }
        }

    }

    /**
     * Reintroduce the MC-113809 glitch for sugar cane. The implementation is identical to Minecraft 1.15.2.
     *
     * @author trueKeuin
     * @reason reintroduce MC-113809 for sugar cane.
     */
    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (world.isAir(pos.up()) && OhMyVanillaMinecraft.getConfiguration().isReintroduceZeroTickFarm()) {
            int i;
            for (i = 1; world.getBlockState(pos.down(i)).isOf((SugarCaneBlock) (Object) this); ++i) {
            }

            if (i < 3) {
                int j = state.get(AGE);
                if (j == 15) {
                    world.setBlockState(pos.up(), this.getDefaultState());
                    world.setBlockState(pos, state.with(AGE, 0), 4);
                } else {
                    world.setBlockState(pos, state.with(AGE, j + 1), 4);
                }
            }
        }
    }
}
