package com.soggyrhino.triggered.client.api.events;

import com.soggyrhino.triggered.client.api.annotations.Event;
import com.soggyrhino.triggered.client.api.objects.GameProfile;
import org.graalvm.polyglot.HostAccess;

@Event
public class ChatMessageEvent {
    @HostAccess.Export public String message;
    @HostAccess.Export public GameProfile sender;
    @HostAccess.Export public String timestamp;

    @HostAccess.Export
    public ChatMessageEvent(String message, GameProfile sender, String timestamp) {
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
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
