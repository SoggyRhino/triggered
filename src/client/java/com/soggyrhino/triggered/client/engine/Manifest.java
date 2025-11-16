package com.soggyrhino.triggered.client.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.soggyrhino.triggered.client.TriggeredClient.MODULES_DIR;

public class Manifest {

    private final String name;
    private final String[] authors;
    private final String[] permissions;
    private final String entry;
    private final int priority;

    private final Path resolvedEntry;
    private final Path base;

    private Manifest(String name, String[] authors, String[] permissions, String entry, int priority, Path base) {
        this.name = name;
        this.authors = authors.clone();
        this.permissions = permissions.clone();
        this.entry = entry;
        this.priority = priority;
        this.base = base;
        this.resolvedEntry = base.resolve(entry);
    }

    public static List<Manifest> loadManifests() {
        try (Stream<Path> walk = Files.walk(MODULES_DIR, 1)) {
            return walk.filter(Files::isDirectory)
                    .filter(p -> !p.equals(MODULES_DIR))
                    .map(folder -> folder.resolve("manifest.json"))
                    .filter(path -> path.toFile().exists())
                    .map(Manifest::loadManifest)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Unable to load manifests", e);
        }
    }

    private static Manifest loadManifest(Path manifestPath) {
        try {
            String content = Files.readString(manifestPath);
            Path moduleBase = manifestPath.getParent();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Manifest.class, new ManifestDeserializer(moduleBase))
                    .create();

            return gson.fromJson(content, Manifest.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read manifest: " + manifestPath, e);
        }
    }

    public String getName() {
        return name;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public String getEntry() {
        return entry;
    }

    public int getPriority() {
        return priority;
    }

    public Path getResolvedEntry() {
        return resolvedEntry;
    }

    @Override
    public String toString() {
        return "Manifest{" +
                "name='" + name + '\'' +
                ", authors=" + Arrays.toString(authors) +
                ", permissions=" + Arrays.toString(permissions) +
                ", entry='" + entry + '\'' +
                ", resolvedEntry=" + resolvedEntry +
                '}';
    }

    //todo better requirements non null etc
    private record ManifestDeserializer(Path moduleBase) implements JsonDeserializer<Manifest> {

        @Override
        public Manifest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            var obj = json.getAsJsonObject();

            String name = obj.get("name").getAsString();
            String[] authors = context.deserialize(obj.get("authors"), String[].class);
            String[] permissions = context.deserialize(obj.get("permissions"), String[].class);
            String entry = obj.get("entry").getAsString();
            int priority = context.deserialize(obj.get("priority"), Integer.class);

            return new Manifest(name, authors, permissions, entry, priority, moduleBase);
        }
    }
}