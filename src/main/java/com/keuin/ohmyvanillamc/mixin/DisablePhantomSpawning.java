package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.gen.PhantomSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PhantomSpawner.class)
public class DisablePhantomSpawning {
    /**
     * Disable phantom spawning
     * @author trueKeuin
     */
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    public void spawn(ServerWorld serverWorld, boolean spawnMonsters, boolean spawnAnimals, CallbackInfo ci) {
        if (OhMyVanillaMinecraft.disablePhantomSpawning)
            ci.cancel();
    }
}
