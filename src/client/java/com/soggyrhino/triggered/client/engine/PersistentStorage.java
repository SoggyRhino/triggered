package com.soggyrhino.triggered.client.engine;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.soggyrhino.triggered.client.TriggeredClient.MODULES_DIR;

public class PersistentStorage {
    private final DB db;
    private final HTreeMap<String, String> persistentStore;
    private final ConcurrentHashMap<String, List<Runnable>> listeners;

    public static PersistentStorage instance;

    public PersistentStorage() {
        this.db = DBMaker.fileDB(MODULES_DIR.resolve("database.db").toString())
                .transactionEnable()
                .closeOnJvmShutdown()
                .make();

        this.persistentStore = db.hashMap("persistentData", Serializer.STRING, Serializer.STRING)
                .createOrOpen();
        this.listeners = new ConcurrentHashMap<>();
    }

    public static void init(){
        instance =  new PersistentStorage();
    }

    public void set(String key, String json) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        persistentStore.put(key, json);
        db.commit();
        notifyListeners(key, json);
    }

    public String get(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        return persistentStore.get(key);
    }

    public void watch(String key, Runnable listener) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (listener == null) {
            throw new IllegalArgumentException("Listener cannot be null");
        }

        listeners.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>())
                .add(listener);
    }

    private void notifyListeners(String key, String json) {
        List<Runnable> keyListeners = listeners.getOrDefault(key, Collections.emptyList());

        for (Runnable listener : keyListeners) {
            listener.run();
        }
    }

    public void clearListeners() {
        listeners.clear();
    }

    public boolean containsKey(String key) {
        return persistentStore.containsKey(key);
    }

    public void delete(String key) {
        if (persistentStore.containsKey(key)) {
            persistentStore.remove(key);
            db.commit();
            notifyListeners(key, null);
        }
    }

    public void close() {
        if (db != null && !db.isClosed()) {
            db.commit();
            db.close();
        }
    }
}