package com.soggyrhino.triggered.client.api.objects.client.utils;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import net.minecraft.util.Identifier;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

@MCObject
public class SkinTextures {
    public net.minecraft.client.util.SkinTextures mcObject;

    public SkinTextures(net.minecraft.client.util.SkinTextures mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public Identifier getTexture() {
        return mcObject.texture();
    }

    @Nullable
    @HostAccess.Export
    public String getTextureUrl() {
        return mcObject.textureUrl();
    }

    @Nullable
    @HostAccess.Export
    public Identifier capeTexture() {
        return mcObject.capeTexture();
    }

    @Nullable
    @HostAccess.Export
    public Identifier getElytraTexture() {
        return mcObject.elytraTexture();
    }

    @HostAccess.Export
    public String getModelName() {
        return mcObject.model().getName();
    }
    
    @HostAccess.Export
    public boolean isSecure() {
        return mcObject.secure();
    }

}
