package com.soggyrhino.triggered.client.api.objects.client.network;

import net.minecraft.nbt.NbtCompound;
import org.graalvm.polyglot.HostAccess;

public class ServerInfo {
    public net.minecraft.client.network.ServerInfo mcObject;

    public ServerInfo(net.minecraft.client.network.ServerInfo mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public NbtCompound toNbt() {
        return mcObject.toNbt();
    }

    @HostAccess.Export
    public String getResourcePackPolicy() {
        return mcObject.getResourcePackPolicy().getName().getString();
    }

    @HostAccess.Export
    public byte[] getFavicon() {
        return mcObject.getFavicon();
    }

    @HostAccess.Export
    public boolean isLocal() {
        return mcObject.isLocal();
    }

    @HostAccess.Export
    public boolean isRealm() {
        return mcObject.isRealm();
    }

    @HostAccess.Export
    public String getServerType() {
        return mcObject.getServerType().name();
    }

    @HostAccess.Export
    public String getStatus() {
        return mcObject.getStatus().name();
    }
}