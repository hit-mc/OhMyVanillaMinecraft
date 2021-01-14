package com.keuin.ohmyvanillamc.mixin;

import com.keuin.ohmyvanillamc.OhMyVanillaMinecraft;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mixin(PlayerManager.class)
public abstract class ReintroduceLlamaItemDuping {

    @Shadow
    protected abstract void savePlayerData(ServerPlayerEntity player);

    @Shadow
    @Final
    private static Logger LOGGER;

    @Shadow
    @Final
    private List<ServerPlayerEntity> players;

    @Shadow
    @Final
    private MinecraftServer server;

    @Shadow
    @Final
    private Map<UUID, ServerPlayerEntity> playerMap;

    @Shadow
    @Final
    private Map<UUID, ServerStatHandler> statisticsMap;

    @Shadow
    @Final
    private Map<UUID, PlayerAdvancementTracker> advancementTrackers;

    @Shadow
    public abstract void sendToAll(Packet<?> packet);

    /**
     * @reason re-introduce llama item duplicating glitch.
     * @author trueKeuin
     */
    @Overwrite
    public void remove(ServerPlayerEntity player) {
        ServerWorld serverWorld = player.getServerWorld();
        player.incrementStat(Stats.LEAVE_GAME);
        this.savePlayerData(player);
        if (player.hasVehicle()) {
            Entity entity = player.getRootVehicle();
            if (entity.hasPlayerRider()) {
                LOGGER.debug("Removing player mount");
                player.stopRiding();
                serverWorld.removeEntity(entity);
                // entity.removed = true
                entity.removed = !OhMyVanillaMinecraft.getConfiguration().isReintroduceLlamaItemDuplicating() || entity.removed;

                Entity entity2;
                for (Iterator var4 = entity.getPassengersDeep().iterator(); var4.hasNext(); // entity2.removed = true
                     entity2.removed = !OhMyVanillaMinecraft.getConfiguration().isReintroduceLlamaItemDuplicating() || entity2.removed) {
                    entity2 = (Entity) var4.next();
                    serverWorld.removeEntity(entity2);
                }

                serverWorld.getChunk(player.chunkX, player.chunkZ).markDirty();
            }
        }

        player.detach();
        serverWorld.removePlayer(player);
        player.getAdvancementTracker().clearCriteria();
        this.players.remove(player);
        this.server.getBossBarManager().onPlayerDisconnect(player);
        UUID uUID = player.getUuid();
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) this.playerMap.get(uUID);
        if (serverPlayerEntity == player) {
            this.playerMap.remove(uUID);
            this.statisticsMap.remove(uUID);
            this.advancementTrackers.remove(uUID);
        }

        this.sendToAll(new PlayerListS2CPacket(PlayerListS2CPacket.Action.REMOVE_PLAYER, new ServerPlayerEntity[]{player}));
    }
}
