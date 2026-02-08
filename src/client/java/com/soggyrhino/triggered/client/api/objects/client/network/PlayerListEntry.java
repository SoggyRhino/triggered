package com.soggyrhino.triggered.client.api.objects.client.network;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.authlib.GameProfile;
import com.soggyrhino.triggered.client.api.objects.client.utils.SkinTextures;
import net.minecraft.scoreboard.Team;
import net.minecraft.text.Text;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

@MCObject
public class PlayerListEntry {
    public net.minecraft.client.network.PlayerListEntry mcObject;

    @HostAccess.Export
    public PlayerListEntry(net.minecraft.client.network.PlayerListEntry mcObject) {
        this.mcObject = mcObject;
    }
    
    @HostAccess.Export
    public GameProfile getProfile() {
        return new GameProfile(mcObject.getProfile());
    }

    @HostAccess.Export
    public boolean hasPublicKey() {
        return mcObject.hasPublicKey();
    }
 
    @HostAccess.Export
    public String getGameMode() {
        return mcObject.getGameMode().asString();
    }
    
    @HostAccess.Export
    public int getLatency() {
        return mcObject.getLatency();
    }

    @HostAccess.Export
    public SkinTextures getSkinTextures() {
        return new SkinTextures(mcObject.getSkinTextures());
    }

    @Nullable
    @HostAccess.Export
    public Team getScoreboardTeam() {
        return mcObject.getScoreboardTeam();
    }
 
    @Nullable
    @HostAccess.Export
    public Text getDisplayName() {
        return mcObject.getDisplayName();
    }
    
    @HostAccess.Export
    public boolean shouldShowHat() {
        return mcObject.shouldShowHat();
    }
    
    @HostAccess.Export
    public int getListOrder() {
        return mcObject.getListOrder();
    }
}
