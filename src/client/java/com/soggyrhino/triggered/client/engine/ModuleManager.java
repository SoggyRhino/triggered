package com.soggyrhino.triggered.client.engine;

import com.soggyrhino.triggered.client.api.JSBindingManager;
import com.soggyrhino.triggered.client.api.JSHostAccessManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.soggyrhino.triggered.client.TriggeredClient.LOGGER;

public class ModuleManager {
    private volatile List<Module> modules;
    private final ExecutorService executorService;

    public static ModuleManager instance;

    private ModuleManager() {
        modules = new ArrayList<>();
        //todo add config in settings
        executorService = Executors.newFixedThreadPool(3);
    }

    public static void init(){
        instance = new ModuleManager();
        JSHostAccessManager.init();
        JSBindingManager.init();
    }

    //todo throws exception
    public void reload() {

        executorService.submit(() -> {
            LOGGER.info("Starting module reload...");

            modules.forEach(Module::shutdown);

            PersistentStorage.instance.clearListeners();

            modules = Manifest.loadManifests().stream()
                    .sorted(Comparator.comparing(Manifest::getPriority).reversed())
                    .map(Module::new)
                    .toList();

            modules.forEach(module -> executorService.submit(module::init));
            LOGGER.info("Module reload complete.");
        });
    }

    public void trigger(String event, Object eventData) {
        modules.forEach(module -> module.trigger(event, eventData));
    }
}