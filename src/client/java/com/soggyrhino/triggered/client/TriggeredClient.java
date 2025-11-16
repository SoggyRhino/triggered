package com.soggyrhino.triggered.client;

import com.soggyrhino.triggered.client.command.TestCommand;
import com.soggyrhino.triggered.client.engine.ModuleManager;
import com.soggyrhino.triggered.client.engine.PersistentStorage;
import com.soggyrhino.triggered.client.listeners.ListenersManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TriggeredClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(TriggeredClient.class);
    public static final Path MODULES_DIR = FabricLoader.getInstance().getConfigDir().resolve("triggered");


    @Override
    public void onInitializeClient() {

        if (!Files.exists(MODULES_DIR)) {
            try {
                Files.createDirectories(MODULES_DIR);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create Modules Directory", e);
            }
        }

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            TestCommand.register(dispatcher);
        });

        ModuleManager.init();
        PersistentStorage.init();
        ListenersManager.registerListeners();
    }
}