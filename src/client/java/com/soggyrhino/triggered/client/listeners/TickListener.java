package com.soggyrhino.triggered.client.listeners;

import com.soggyrhino.triggered.client.api.objects.client.MinecraftClient;
import com.soggyrhino.triggered.client.engine.ModuleManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;


public class TickListener {

    @Listener
    public static void registerListener() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            ModuleManager.instance.trigger("END_CLIENT_TICK", new MinecraftClient(client));
        });

    }
}
