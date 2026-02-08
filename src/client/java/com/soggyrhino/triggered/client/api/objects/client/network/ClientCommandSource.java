package com.soggyrhino.triggered.client.api.objects.client.network;

import net.minecraft.command.CommandSource;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.graalvm.polyglot.HostAccess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ClientCommandSource {
    public net.minecraft.client.network.ClientCommandSource mcObject;

    public ClientCommandSource(net.minecraft.client.network.ClientCommandSource mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public List<String> getPlayerNames() {
        return new ArrayList<>(mcObject.getPlayerNames());
    }

    @HostAccess.Export
    public List<String> getChatSuggestions() {
        return new ArrayList<>(mcObject.getChatSuggestions());
    }

    @HostAccess.Export
    public List<String> getEntitySuggestions() {
        return new ArrayList<>(mcObject.getEntitySuggestions());
    }

    @HostAccess.Export
    public List<String> getTeamNames() {
        return new ArrayList<>(mcObject.getTeamNames());
    }

    @HostAccess.Export
    public List<Identifier> getSoundIds() {
        return mcObject.getSoundIds().collect(Collectors.toList());
    }

    @HostAccess.Export
    public boolean hasPermissionLevel(int level) {
        return mcObject.hasPermissionLevel(level);
    }

    @HostAccess.Export
    public boolean hasElevatedPermissions() {
        return mcObject.hasElevatedPermissions();
    }
    
    @HostAccess.Export
    public List<CommandSource.RelativePosition> getBlockPositionSuggestions() {
        return new ArrayList<>(mcObject.getBlockPositionSuggestions());
    }

    @HostAccess.Export
    public Collection<CommandSource.RelativePosition> getPositionSuggestions() {
        return new ArrayList<>(mcObject.getPositionSuggestions());
    }

    @HostAccess.Export
    public Set<RegistryKey<World>> getWorldKeys() {
        return mcObject.getWorldKeys();
    }
    


    @HostAccess.Export
    public boolean isTrusted() {
        return mcObject.isTrusted();
    }
}
