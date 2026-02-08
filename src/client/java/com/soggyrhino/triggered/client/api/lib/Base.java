package com.soggyrhino.triggered.client.api.lib;

import com.soggyrhino.triggered.client.api.annotations.Library;
import com.soggyrhino.triggered.client.engine.ModuleManager;
import com.soggyrhino.triggered.client.engine.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.util.concurrent.TimeUnit;

@Library(requiresModule = true, anonymous = true)
public class Base {
    private final Module module;

    public Base(Module module) {
        this.module = module;
    }

    @HostAccess.Export
    public void register(String eventType, Value listener) {
        this.module.register(eventType, listener);
    }

    @HostAccess.Export
    public void trigger(String name, Value data) {
        this.module.trigger(name, data);
    }

    @HostAccess.Export
    public void triggerAll(String name, Value data) {
        ModuleManager.instance.trigger(name, data);
    }

    @HostAccess.Export
    public void chat(String content) {
        //todo fix
        MinecraftClient.getInstance().player.sendMessage(Text.of(content), false);
    }

    @HostAccess.Export
    public void setTimeout(Value callback, long delay) {
        module.schedule(() -> {
            try {
                module.getContext().enter();
                callback.executeVoid();
            } finally {
                module.getContext().leave();
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    @HostAccess.Export
    public void setInterval(Value callback, long period) {
        module.scheduleAtFixedRate(() -> {
            try {
                module.getContext().enter();
                callback.executeVoid();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                module.getContext().leave();
            }
        }, period, period, TimeUnit.MILLISECONDS);
    }
}