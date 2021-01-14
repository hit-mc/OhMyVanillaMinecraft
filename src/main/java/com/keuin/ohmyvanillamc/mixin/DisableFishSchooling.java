package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowGroupLeaderGoal;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SchoolingFishEntity.class)
public abstract class DisableFishSchooling extends FishEntity {

    public DisableFishSchooling(EntityType<? extends FishEntity> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean hasLeader();
    @Shadow private SchoolingFishEntity leader;

    /**
     * @reason To disable SchoolingFish schooling.
     * @author trueKeuin
     */
    @Overwrite
    public void moveTowardLeader() {
        if (!OhMyVanillaMinecraft.getConfiguration().isDisableFishSchooling()) {
            if (this.hasLeader()) {
                this.getNavigation().startMovingTo(this.leader, 1.0D);
            }
        }
    }

    /**
     * @reason To disable SchoolingFish schooling.
     * @author trueKeuin
     */
    @Overwrite
    public void initGoals() {
        super.initGoals();
        if (!OhMyVanillaMinecraft.getConfiguration().isDisableFishSchooling()) {
            this.goalSelector.add(5, new FollowGroupLeaderGoal((SchoolingFishEntity) (Object) this));
        }
    }
}
