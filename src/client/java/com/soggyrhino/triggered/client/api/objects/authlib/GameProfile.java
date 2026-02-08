package com.soggyrhino.triggered.client.api.objects.authlib;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

import java.util.*;

@MCObject
public class GameProfile {
    public final com.mojang.authlib.GameProfile mcObject;

    public GameProfile(com.mojang.authlib.GameProfile mcObject) {
        if (mcObject == null) {
            throw new IllegalArgumentException("GameProfile cannot be null");
        }

        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public UUID getId() {
        return mcObject.getId();
    }

    /**
     * Gets the display name of this game profile.
     * <p />
     *
     * @return Name of the profile
     */
    @HostAccess.Export
    public String getName() {
        return mcObject.getName();
    }

    /**
     * Returns any known properties about this game profile.
     *
     * @return Modifiable map of profile properties.
     */
    @HostAccess.Export
    public Map<String, List<Property>> getProperties() {
        HashMap<String, List<Property>> map = new HashMap<>();
        for (Map.Entry<String, com.mojang.authlib.properties.Property> entry : mcObject.getProperties().entries()) {
            map.computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                    .add(Property.fromMc(entry.getValue()));
        }
        return map;
    }
}
