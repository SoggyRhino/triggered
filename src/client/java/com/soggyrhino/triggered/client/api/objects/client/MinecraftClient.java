package com.soggyrhino.triggered.client.api.objects.client;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.authlib.GameProfile;
import com.soggyrhino.triggered.client.api.objects.authlib.minecraft.BanDetails;
import com.soggyrhino.triggered.client.api.objects.client.color.block.BlockColors;
import com.soggyrhino.triggered.client.api.objects.client.network.ClientPlayNetworkHandler;
import com.soggyrhino.triggered.client.api.objects.client.network.ServerInfo;
import com.soggyrhino.triggered.client.api.objects.client.utils.Window;
import com.soggyrhino.triggered.client.api.objects.entity.Entity;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@MCObject
public class MinecraftClient {
    public net.minecraft.client.MinecraftClient mcObject;

    public MinecraftClient(net.minecraft.client.MinecraftClient mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public boolean isFinishedLoading() {
        return mcObject.isFinishedLoading();
    }

    @HostAccess.Export
    public String getGameVersion() {
        return mcObject.getGameVersion();
    }

    @HostAccess.Export
    public String getVersionType() {
        return mcObject.getVersionType();
    }

    @HostAccess.Export
    public boolean forcesUnicodeFont() {
        return mcObject.forcesUnicodeFont();
    }


    @HostAccess.Export
    public int getCurrentFps() {
        return mcObject.getCurrentFps();
    }

    @HostAccess.Export
    public long getRenderTime() {
        return mcObject.getRenderTime();
    }


    @HostAccess.Export
    public boolean isRunning() {
        return mcObject.isRunning();
    }


    @HostAccess.Export
    public double getGpuUtilizationPercentage() {
        return mcObject.getGpuUtilizationPercentage();
    }


    @HostAccess.Export
    public boolean isMultiplayerEnabled() {
        return mcObject.isMultiplayerEnabled();
    }

    @HostAccess.Export
    public boolean isRealmsEnabled() {
        return mcObject.isRealmsEnabled();
    }

    @Nullable
    @HostAccess.Export
    public BanDetails getMultiplayerBanDetails() {
        return new BanDetails(mcObject.getMultiplayerBanDetails());
    }

    @HostAccess.Export
    public boolean isUsernameBanned() {
        return mcObject.isUsernameBanned();
    }

    @HostAccess.Export
    public boolean shouldBlockMessages(UUID sender) {
        return mcObject.shouldBlockMessages(sender);
    }

    @HostAccess.Export
    public net.minecraft.client.MinecraftClient.ChatRestriction getChatRestriction() {
        return mcObject.getChatRestriction();
    }

    @HostAccess.Export
    public boolean isDemo() {
        return mcObject.isDemo();
    }

    @Nullable
    @HostAccess.Export
    public ClientPlayNetworkHandler getNetworkHandler() {
        return new ClientPlayNetworkHandler(mcObject.getNetworkHandler());
    }

    @Nullable
    @HostAccess.Export
    public ServerInfo getCurrentServerEntry() {
        return new ServerInfo(mcObject.getCurrentServerEntry());
    }

    @HostAccess.Export
    public boolean isInSingleplayer() {
        return mcObject.isInSingleplayer();
    }

    @HostAccess.Export
    public boolean isIntegratedServerRunning() {
        return mcObject.isIntegratedServerRunning();
    }

    @HostAccess.Export
    public boolean isConnectedToLocalServer() {
        return mcObject.isConnectedToLocalServer();
    }

    @HostAccess.Export
    public boolean uuidEquals(UUID uuid) {
        return mcObject.uuidEquals(uuid);
    }

    @HostAccess.Export
    public GameProfile getGameProfile() {
        return new GameProfile(mcObject.getGameProfile());
    }

    @HostAccess.Export
    public boolean isPaused() {
        return mcObject.isPaused();
    }

    @Nullable
    @HostAccess.Export
    public Entity getCameraEntity() {
        return new Entity(mcObject.getCameraEntity());
    }

    @HostAccess.Export
    public boolean hasOutline(Entity entity) {
        return mcObject.hasOutline(entity.mcEntity);
    }

    @HostAccess.Export
    public BlockColors getBlockColors() {
        return new BlockColors(mcObject.getBlockColors());
    }

    @HostAccess.Export
    public boolean hasReducedDebugInfo() {
        return mcObject.hasReducedDebugInfo();
    }


    @HostAccess.Export
    public boolean isWindowFocused() {
        return mcObject.isWindowFocused();
    }

    //todo look at moving to lib take panorama

    @HostAccess.Export
    public Window getWindow() {
        return new Window(mcObject.getWindow());
    }

    //@HostAccess.Export
    //public LoadedEntityModels getLoadedEntityModels() {
    //    return mcObject.getLoadedEntityModels();
    //}

    @HostAccess.Export
    public boolean shouldFilterText() {
        return mcObject.shouldFilterText();
    }


    @HostAccess.Export
    public boolean providesProfileKeys() {
        return mcObject.providesProfileKeys();
    }

    @HostAccess.Export
    public List<String> getCommandHistory() {
        return new ArrayList<>(mcObject.getCommandHistoryManager().getHistory());
    }

}