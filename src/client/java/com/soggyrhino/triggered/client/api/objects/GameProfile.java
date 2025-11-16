package com.soggyrhino.triggered.client.api.objects;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

@MCObject
public class GameProfile {
    @HostAccess.Export
    public String id;
    @HostAccess.Export
    public String name;

    @HostAccess.Export
    public GameProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GameProfile fromMc(com.mojang.authlib.GameProfile mcProfile) {
        return new GameProfile(mcProfile.getId().toString(), mcProfile.getName());
    }

    @Override
    @HostAccess.Export
    public String toString() {
        return "GameProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
