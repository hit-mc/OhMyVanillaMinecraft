package com.keuin.ohmyvanillamc.mixins.rule.forceRipening;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin extends Block {

    public ChorusFlowerBlockMixin(Settings settings) {
        super(settings);
    }

    @Shadow
    @Final
    public static IntProperty AGE;

    @Shadow
    @Final
    private ChorusPlantBlock plantBlock;

    @Shadow
    private static boolean isSurroundedByAir(WorldView world, BlockPos pos, @Nullable Direction exceptDirection) {
        throw new RuntimeException("Mixin error. Program should not go here.");
    }

    @Shadow
    private void grow(World world, BlockPos pos, int age) {
        throw new RuntimeException("Mixin error. Program should not go here.");
    }

    @Shadow
    private void die(World world, BlockPos pos) {
        throw new RuntimeException("Mixin error. Program should not go here.");
    }

    /**
     * Reintroduce the MC-113809 glitch for chorus flower. The implementation is identical to Minecraft 1.15.2.
     *
     * @author trueKeuin
     * @reason reintroduce MC-113809 for chorus flower.
     */
    @Overwrite
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (OmvmSettings.enableChorusFlowerForceRipening) {
            realGrow(state, world, pos, random);
        }

    }

    /**
     * Reintroduce the base class's implementation.
     *
     * @reason reintroduce base class's implementation.
     * @author trueKeuin
     */
    @Overwrite
    public boolean hasRandomTicks(BlockState state) {
        boolean zf = OmvmSettings.enableChorusFlowerForceRipening;
        return ((state.get(AGE) < 5) && !zf) || (randomTicks && zf);
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
        if (OmvmSettings.enableChorusFlowerForceRipening) {
            scheduledTick(state, world, pos, random);
        } else {
            realGrow(state, world, pos, random);
        }

    }

    private void realGrow(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockPos blockPos = pos.up();
        if (world.isAir(blockPos) && blockPos.getY() < 256) {
            int i = state.get(AGE);
            if (i < 5) {
                boolean bl = false;
                boolean bl2 = false;
                BlockState blockState = world.getBlockState(pos.down());
                Block block = blockState.getBlock();
                int l;
                if (block == Blocks.END_STONE) {
                    bl = true;
                } else if (block == this.plantBlock) {
                    l = 1;

                    for (int k = 0; k < 4; ++k) {
                        Block block2 = world.getBlockState(pos.down(l + 1)).getBlock();
                        if (block2 != this.plantBlock) {
                            if (block2 == Blocks.END_STONE) {
                                bl2 = true;
                            }
                            break;
                        }

                        ++l;
                    }

                    if (l < 2 || l <= random.nextInt(bl2 ? 5 : 4)) {
                        bl = true;
                    }
                } else if (blockState.isAir()) {
                    bl = true;
                }

                if (bl && isSurroundedByAir(world, blockPos, (Direction) null) && world.isAir(pos.up(2))) {
                    world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                    this.grow(world, blockPos, i);
                } else if (i < 4) {
                    l = random.nextInt(4);
                    if (bl2) {
                        ++l;
                    }

                    boolean bl3 = false;

                    for (int m = 0; m < l; ++m) {
                        Direction direction = Direction.Type.HORIZONTAL.random(random);
                        BlockPos blockPos2 = pos.offset(direction);
                        if (world.isAir(blockPos2) && world.isAir(blockPos2.down()) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {
                            this.grow(world, blockPos2, i + 1);
                            bl3 = true;
                        }
                    }

                    if (bl3) {
                        world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                    } else {
                        this.die(world, pos);
                    }
                } else {
                    this.die(world, pos);
                }

            }
        }
    }


}
