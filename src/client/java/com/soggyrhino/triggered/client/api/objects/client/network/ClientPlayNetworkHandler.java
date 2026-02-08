package com.soggyrhino.triggered.client.api.objects.client.network;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.authlib.GameProfile;
import com.soggyrhino.triggered.client.api.objects.client.world.ClientWorld;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@MCObject
public class ClientPlayNetworkHandler {
    public net.minecraft.client.network.ClientPlayNetworkHandler mcObject;

    public ClientPlayNetworkHandler(net.minecraft.client.network.ClientPlayNetworkHandler mcObject) {
        this.mcObject = mcObject;
    }

    public ClientCommandSource getCommandSource() {
        return new ClientCommandSource(mcObject.getCommandSource());
    }


    public boolean isConnectionOpen() {
        return mcObject.isConnectionOpen();
    }

    public List<PlayerListEntry> getListedPlayerListEntries() {
        return mcObject.getListedPlayerListEntries().stream().map(PlayerListEntry::new).collect(Collectors.toList());
    }

    public List<PlayerListEntry> getPlayerList() {
        return mcObject.getPlayerList().stream().map(PlayerListEntry::new).collect(Collectors.toList());
    }

    public List<UUID> getPlayerUuids() {
        return new ArrayList<>(mcObject.getPlayerUuids());
    }

    @Nullable
    public PlayerListEntry getPlayerListEntry(UUID uuid) {
        return new PlayerListEntry(mcObject.getPlayerListEntry(uuid));
    }

    @Nullable
    public PlayerListEntry getPlayerListEntry(String profileName) {
        return new PlayerListEntry(mcObject.getPlayerListEntry(profileName));
    }

    public GameProfile getProfile() {
        return new GameProfile(mcObject.getProfile());
    }

    public ClientWorld getWorld() {
        return new ClientWorld(mcObject.getWorld());
    }

    public Set<RegistryKey<World>> getWorldKeys() {
        return mcObject.getWorldKeys();
    }

    @Nullable
    public ServerInfo getServerInfo() {
        return new ServerInfo(mcObject.getServerInfo());
    }

    public FeatureSet getEnabledFeatures() {
        return mcObject.getEnabledFeatures();
    }

    public boolean hasFeature(FeatureSet feature) {
        return mcObject.hasFeature(feature);
    }

    public Scoreboard getScoreboard() {
        return mcObject.getScoreboard();
    }
}
