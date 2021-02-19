package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(BambooBlock.class)
public abstract class Mc113809BambooBlockMixin extends Block {

    public Mc113809BambooBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    @Final
    public static IntProperty STAGE;

    @Shadow
    protected abstract int countBambooBelow(BlockView world, BlockPos pos);

    @Shadow
    protected abstract void updateLeaves(BlockState state, World world, BlockPos pos, Random random, int height);

    /**
     * Reintroduce the base class's implementation.
     *
     * @reason reintroduce base class's implementation.
     * @author trueKeuin
     */
    @Overwrite
    public boolean hasRandomTicks(BlockState state) {
        boolean zf = OhMyVanillaMinecraft.getConfiguration().isReintroduceZeroTickFarm() && OhMyVanillaMinecraft.getConfiguration().isEnableBambooForceRipening();
        return ((state.get(STAGE) == 0) && !zf) || (randomTicks && zf);
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
        if (OhMyVanillaMinecraft.getConfiguration().isReintroduceZeroTickFarm() && OhMyVanillaMinecraft.getConfiguration().isEnableBambooForceRipening()) {
            scheduledTick(state, world, pos, random);
        } else {
            realGrow(state, world, pos, random);
        }
    }

    /**
     * Reintroduce the MC-113809 glitch for bamboo. The implementation is identical to Minecraft 1.15.2.
     *
     * @author trueKeuin
     * @reason reintroduce MC-113809 for bamboo.
     */
    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (OhMyVanillaMinecraft.getConfiguration().isReintroduceZeroTickFarm() && OhMyVanillaMinecraft.getConfiguration().isEnableBambooForceRipening()) {
            realGrow(state, world, pos, random);
        }
    }

    private void realGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STAGE) == 0) {
            if (random.nextInt(3) == 0 && world.isAir(pos.up()) && world.getBaseLightLevel(pos.up(), 0) >= 9) {
                int i = this.countBambooBelow(world, pos) + 1;
                if (i < 16) {
                    this.updateLeaves(state, world, pos, random, i);
                }
            }
        }
    }

}
