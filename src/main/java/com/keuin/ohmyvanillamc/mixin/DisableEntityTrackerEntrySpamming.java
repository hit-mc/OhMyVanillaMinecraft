package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.DummyLogger;
import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.server.network.EntityTrackerEntry;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EntityTrackerEntry.class)
public class DisableEntityTrackerEntrySpamming {

    private static final Logger DUMMY_LOGGER = new DummyLogger();

    @Shadow
    @Final
    private static Logger LOGGER;

    @Accessor("LOGGER")
    private static Logger LOGGER() {
        return OhMyVanillaMinecraft.getConfiguration().isFixEntityTrackerEntrySpamming() ? DUMMY_LOGGER : LOGGER;
    }

}
