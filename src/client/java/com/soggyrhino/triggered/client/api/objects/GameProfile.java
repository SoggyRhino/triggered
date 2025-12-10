package com.soggyrhino.triggered.client.api.objects;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@MCObject
public class GameProfile {
    @HostAccess.Export
    public final String id;
    @HostAccess.Export
    public final String name;
    @HostAccess.Export
    public final Map<String, List<Property>> properties = new HashMap<>();

    public GameProfile(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static GameProfile fromMc(com.mojang.authlib.GameProfile mcProfile) {
        if (mcProfile == null) {
            throw new IllegalArgumentException("GameProfile cannot be null");
        }

        GameProfile profile = new GameProfile(
                mcProfile.getId() != null ? mcProfile.getId().toString() : null,
                mcProfile.getName()
        );

        if (mcProfile.getProperties() != null) {
            for (Map.Entry<String, com.mojang.authlib.properties.Property> entry : mcProfile.getProperties().entries()) {
                profile.properties.computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(Property.fromMc(entry.getValue()));
            }
        }
        return profile;
    }

    @HostAccess.Export
    public boolean isComplete() {
        return this.id != null && this.name != null && !this.name.trim().isEmpty();
    }

    @HostAccess.Export
    public boolean isLegacy() {
        return this.id == null && this.name != null && !this.name.trim().isEmpty();
    }

    @Override
    @HostAccess.Export
    public @NotNull String toString() {
        return "GameProfile{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }

    @Override
    @HostAccess.Export
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameProfile that = (GameProfile) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    @HostAccess.Export
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
