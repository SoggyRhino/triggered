package com.soggyrhino.triggered.client.api.events;

import com.soggyrhino.triggered.client.api.annotations.Event;
import com.soggyrhino.triggered.client.api.objects.authlib.GameProfile;
import org.graalvm.polyglot.HostAccess;

import java.time.Instant;

@Event
public class ChatMessageEvent {
    @HostAccess.Export
    public String message;
    @HostAccess.Export
    public GameProfile sender;
    @HostAccess.Export
    public Instant timestamp;

    @HostAccess.Export
    public ChatMessageEvent(String message, GameProfile sender, Instant timestamp) {
        this.message = message;
        this.sender = sender;
        this.timestamp = timestamp;
    }

    @Override
    @HostAccess.Export
    public String toString() {
        return "ChatMessageEvent{" +
                "message='" + message + '\'' +
                ", sender=" + sender +
                ", timestamp='" + timestamp.toString() + '\'' +
                '}';
    }
}
