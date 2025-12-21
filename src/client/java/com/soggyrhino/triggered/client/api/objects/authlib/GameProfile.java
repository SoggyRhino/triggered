package com.soggyrhino.triggered.client.api.objects.authlib;

import com.mojang.authlib.properties.PropertyMap;
import com.soggyrhino.triggered.client.api.annotations.MCObject;

import java.util.UUID;

@MCObject
public class GameProfile {
    public final com.mojang.authlib.GameProfile mcObject;

    public GameProfile(com.mojang.authlib.GameProfile mcProfile) {
        if (mcProfile == null) {
            throw new IllegalArgumentException("GameProfile cannot be null");
        }

        this.mcObject = mcProfile;
    }

    public UUID getId() {
        return mcObject.getId();
    }

    /**
     * Gets the display name of this game profile.
     * <p />
     *
     * @return Name of the profile
     */
    public String getName() {
        return mcObject.getName();
    }

    /**
     * Returns any known properties about this game profile.
     *
     * @return Modifiable map of profile properties.
     */
    public PropertyMap getProperties() {
        return mcObject.getProperties();
    }
}
