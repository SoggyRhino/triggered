package com.soggyrhino.triggered.client.listeners;

import com.soggyrhino.triggered.client.api.events.ChatMessageEvent;
import com.soggyrhino.triggered.client.api.objects.GameProfile;
import com.soggyrhino.triggered.client.engine.ModuleManager;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

public class ChatListener {

    @Listener
    public static void registerListener(){
        ClientReceiveMessageEvents.CHAT.register((message, signed_message, sender, params, timestamp) -> {
            if (sender != null) {
                ChatMessageEvent event = new ChatMessageEvent(message.toString(), GameProfile.fromMc(sender), timestamp.toString());
                ModuleManager.instance.trigger("ChatMessageEvent", event);
            }
        });
    }
}
