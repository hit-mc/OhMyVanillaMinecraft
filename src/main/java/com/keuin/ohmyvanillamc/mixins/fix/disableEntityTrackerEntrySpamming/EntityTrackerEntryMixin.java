package com.keuin.ohmyvanillamc.mixins.fix.disableEntityTrackerEntrySpamming;

import com.keuin.ohmyvanillamc.DummyLogger;
import net.minecraft.server.network.EntityTrackerEntry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {

    static {
        setLOGGER(new DummyLogger());
    }

    @Shadow
    @Final
    private static Logger LOGGER;

    @Accessor("LOGGER")
    private static Logger getLOGGER() {
        throw new AssertionError();
    }

    @Accessor("LOGGER")
    private static void setLOGGER(Logger logger) {
        throw new AssertionError();
    }

}
