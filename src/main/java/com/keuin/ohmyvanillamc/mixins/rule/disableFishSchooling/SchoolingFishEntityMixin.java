package com.keuin.ohmyvanillamc.mixins.rule.disableFishSchooling;

import com.keuin.ohmyvanillamc.OmvmSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowGroupLeaderGoal;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.SchoolingFishEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SchoolingFishEntity.class)
public abstract class SchoolingFishEntityMixin extends FishEntity {

    public SchoolingFishEntityMixin(EntityType<? extends FishEntity> type, World world) {
        super(type, world);
    }

    @Shadow
    public abstract boolean hasLeader();

    @Shadow
    private SchoolingFishEntity leader;

    /**
     * @reason To disable SchoolingFish schooling.
     * @author trueKeuin
     */
    @Overwrite
    public void moveTowardLeader() {
        if (!OmvmSettings.disableFishSchooling) {
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
        if (!OmvmSettings.disableFishSchooling) {
            this.goalSelector.add(5, new FollowGroupLeaderGoal((SchoolingFishEntity) (Object) this));
        }
    }
}
