package com.soggyrhino.triggered.client.api.lib;

import com.google.gson.*;
import com.soggyrhino.triggered.client.api.annotations.Library;
import com.soggyrhino.triggered.client.engine.Module;
import com.soggyrhino.triggered.client.engine.PersistentStorage;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;

//todo better types support,
@Library(requiresModule = true)
public class Storage {
    private final Module module;
    private final Gson gson = new Gson();

    public Storage(Module module) {
        this.module = module;
    }

    @HostAccess.Export
    public Value get(String key) {
        final String modKey = String.format("%s.%s", this.module.getManifest().getName(), key);
        String storedValue = PersistentStorage.instance.get(modKey);

        if (storedValue == null) {
            return module.toValue(null);
        }

        JsonElement element = gson.fromJson(storedValue, JsonElement.class);
        return module.toValue(jsonElementToObject(element));
    }

    @HostAccess.Export
    public void set(String key, Object value) {
        final String modKey = String.format("%s.%s", this.module.getManifest().getName(), key);
        String serialized = gson.toJson(value);
        PersistentStorage.instance.set(modKey, serialized);
    }

    @HostAccess.Export
    public void watch(String key, Value callback) {
        final String modKey = String.format("%s.%s", this.module.getManifest().getName(), key);
        PersistentStorage.instance.watch(modKey, () -> {
            String newValue = PersistentStorage.instance.get(modKey);
            Object deserialized = null;
            if (newValue != null) {
                JsonElement element = gson.fromJson(newValue, JsonElement.class);
                deserialized = jsonElementToObject(element);
            }
            Value jsValue = module.toValue(deserialized);
            this.module.execute(() -> callback.execute(jsValue));
        });
    }


    private Object jsonElementToObject(JsonElement element) {
        if (element.isJsonNull()) {
            return null;
        }
        if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isBoolean()) {
                return primitive.getAsBoolean();
            }
            if (primitive.isNumber()) {
                Number num = primitive.getAsNumber();

                if (num.doubleValue() == num.longValue()) {
                    return num.longValue();
                }
                return num.doubleValue();
            }
            return primitive.getAsString();
        }
        if (element.isJsonArray()) {
            JsonArray array = element.getAsJsonArray();
            java.util.List<Object> list = new java.util.ArrayList<>();
            for (JsonElement item : array) {
                list.add(jsonElementToObject(item));
            }
            return list;
        }
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            for (java.util.Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                map.put(entry.getKey(), jsonElementToObject(entry.getValue()));
            }
            return ProxyObject.fromMap(map);
        }
        return null;
    }
}