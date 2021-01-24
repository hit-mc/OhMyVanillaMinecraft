package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.DummyLogger;
import net.minecraft.server.network.EntityTrackerEntry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTrackerEntry.class)
public class DisableEntityTrackerEntrySpamming {

    private static final Logger LOGGER_DUMMY = new DummyLogger();

    static {
        setLOGGER(LOGGER_DUMMY);
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
