package com.soggyrhino.triggered.client.api.objects.authlib.minecraft;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

import java.time.Instant;

@MCObject
public class BanDetails {
    public final com.mojang.authlib.minecraft.BanDetails mcObject;


    public BanDetails( com.mojang.authlib.minecraft.BanDetails mcObject) {
        this.mcObject = mcObject;
     }

    /**
     * {@return the unique identifier for this ban}
     */
    @HostAccess.Export
    public String getId() {
        return mcObject.id().toString();
    }

    //todo add instant to js context
    /**
     * {@return the expiration Instant}
     */
    @HostAccess.Export
    public Instant getExpires() {
        return mcObject.expires();
    }

    /**
     * {@return whether this ban has expired}
     */
    @HostAccess.Export
    public boolean isExpired() {
        return mcObject.expires() != null &&
                mcObject.expires().isBefore(java.time.Instant.now());
    }

    /**
     * {@return the reason code for the ban, or null if not set}
     */
    @HostAccess.Export
    public String getReason() {
        return mcObject.reason();
    }

    /**
     * {@return the human-readable reason message, or null if not set}
     */
    @HostAccess.Export
    public String getReasonMessage() {
        return mcObject.reasonMessage();
    }
}