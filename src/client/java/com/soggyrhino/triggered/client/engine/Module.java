package com.soggyrhino.triggered.client.engine;

import com.soggyrhino.triggered.client.TriggeredClient;
import com.soggyrhino.triggered.client.api.JSBindingManager;
import com.soggyrhino.triggered.client.api.JSHostAccessManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.ResourceLimits;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Module {
    private final Manifest manifest;
    private final Context context;
    private final ScheduledExecutorService scheduledExecutor;
    private final ConcurrentHashMap<String, List<Value>> eventListeners;

    private static final ResourceLimits limits = ResourceLimits.newBuilder()
            .statementLimit(1_000_000, null)
            .build();

    public Module(Manifest manifest) {
        this.manifest = manifest;
        Context.Builder builder = Context.newBuilder("js")
                .engine(ModuleManager.instance.getEngine())
                .allowHostAccess(JSHostAccessManager.getHostAccess())
                .allowAllAccess(false)
                .allowCreateThread(true)
                .resourceLimits(limits);
        this.context = builder.build();
        this.eventListeners = new ConcurrentHashMap<>();
        this.scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    }

    public void init() {
        this.scheduledExecutor.submit(() -> {
            try {
                JSBindingManager.setupBindings(this);
                context.eval(Source.newBuilder("js", manifest.getResolvedEntry().toFile()).build());
            } catch (Exception e) {
                logError("initialization", e);
            }
        });
    }

    public void register(String eventType, Value listener) {
        eventListeners.putIfAbsent(eventType, new ArrayList<>());
        eventListeners.get(eventType).add(listener);
    }

    public void trigger(String event, Object eventData) {
        List<Value> listeners = eventListeners.getOrDefault(event, Collections.emptyList());
        for (Value value : listeners) {
            execute(() -> value.executeVoid(eventData));
        }
    }

    public void execute(Runnable runnable) {
        scheduledExecutor.submit(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                logError("executing", e);
            }
        });
    }

    public Value toValue(Object object) {
        Value result = null;
        try {
            result = context.asValue(object);
        } catch (Exception e) {
            logError("Java Object toValue", e);
        }
        return result;
    }

    public Context getContext() {
        return context;
    }

    public Manifest getManifest() {
        return manifest;
    }

    public void shutdown() {
        context.close();
        scheduledExecutor.shutdown();
        try {
            if (!scheduledExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
                TriggeredClient.LOGGER.error("Failed to shutdown module in time, forcing shutdown");
                scheduledExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            TriggeredClient.LOGGER.error("Interrupted while waiting for scheduled executor to shutdown", e);
        }
    }

    //todo add enum for errors and better logging
    private void logError(String context, Exception e) {
        TriggeredClient.LOGGER.error("Error during module '{}' {}:", this.manifest.getName(), context, e);
        if (MinecraftClient.getInstance().player != null) {
            Text message = Text.literal("[Triggered] ").formatted(Formatting.RED)
                    .append(Text.literal("Error in '").formatted(Formatting.GRAY))
                    .append(Text.literal(this.manifest.getName()).formatted(Formatting.YELLOW))
                    .append(Text.literal("': " + e.getMessage()).formatted(Formatting.GRAY));

            MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().player.sendMessage(message, false));
        }
    }

    public void schedule(Runnable r, long delay, TimeUnit timeUnit) {
        try {
            this.scheduledExecutor.schedule(() -> {
                try {
                    r.run();
                } catch (Exception e) {
                    trigger("error", e);
                }

            }, delay, timeUnit);
        } catch (Exception e) {
            logError("scheduling", e);
        }
    }

    public void scheduleAtFixedRate(Runnable r, long period, long period1, TimeUnit timeUnit) {
        try {
            this.scheduledExecutor.scheduleAtFixedRate(() -> {
                try {
                    r.run();
                } catch (Exception e) {
                    trigger("error", e);
                }
            }, period, period1, timeUnit);
        } catch (Exception e) {
            logError("scheduling", e);
        }
    }
}