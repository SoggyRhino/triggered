package com.soggyrhino.triggered.client.api.objects.client.world;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.api.objects.entity.Entity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ColorResolver;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@MCObject
public class ClientWorld {
    public net.minecraft.client.world.ClientWorld mcObject;

    public ClientWorld(net.minecraft.client.world.ClientWorld mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public Set<BlockEntity> getBlockEntities() {
        return mcObject.getBlockEntities();
    }

    @HostAccess.Export
    public DimensionEffects getDimensionEffects() {
        return mcObject.getDimensionEffects();
    }
    
    @HostAccess.Export
    public List<Entity> getEntities() {
        List<Entity> list = new ArrayList<>();
        mcObject.getEntities().forEach(i -> list.add(new Entity(i)));
        return list;
    }

    @HostAccess.Export
    public boolean hasEntity(Entity entity) {
        return mcObject.hasEntity(entity.mcEntity);
    }

    @HostAccess.Export
    public boolean isChunkLoaded(int chunkX, int chunkZ) {
        return mcObject.isChunkLoaded(chunkX, chunkZ);
    }

    @HostAccess.Export
    public int getRegularEntityCount() {
        return mcObject.getRegularEntityCount();
    }

    @HostAccess.Export
    public List<Entity> getCrammedEntities(Entity entity, Box box) {
        return mcObject.getCrammedEntities(entity.mcEntity, box).stream().map(Entity::new).collect(Collectors.toList());
    }

    @Nullable
    @HostAccess.Export
    public Entity getEntityById(int id) {
        return new Entity(mcObject.getEntityById(id));
    }

    //todo look at
    @HostAccess.Export
    public ClientChunkManager getChunkManager() {
        return mcObject.getChunkManager();
    }

    @Nullable
    @HostAccess.Export
    public MapState getMapState(MapIdComponent id) {
        return mcObject.getMapState(id);
    }

    @HostAccess.Export
    public Scoreboard getScoreboard() {
        return mcObject.getScoreboard();
    }

    @HostAccess.Export
    public List<AbstractClientPlayerEntity> getPlayers() {
        return mcObject.getPlayers();
    }

    @HostAccess.Export
    public List<EnderDragonPart> getEnderDragonParts() {
        return mcObject.getEnderDragonParts();
    }

    @HostAccess.Export
    public RegistryEntry<Biome> getGeneratorStoredBiome(int biomeX, int biomeY, int biomeZ) {
        return mcObject.getGeneratorStoredBiome(biomeX, biomeY, biomeZ);
    }

    @HostAccess.Export
    public float getSkyBrightness(float tickProgress) {
        return mcObject.getSkyBrightness(tickProgress);
    }

    @HostAccess.Export
    public int getSkyColor(Vec3d cameraPos, float tickProgress) {
        return mcObject.getSkyColor(cameraPos, tickProgress);
    }

    @HostAccess.Export
    public int getCloudsColor(float tickProgress) {
        return mcObject.getCloudsColor(tickProgress);
    }

    @HostAccess.Export
    public float getStarBrightness(float tickProgress) {
        return mcObject.getStarBrightness(tickProgress);
    }

    @HostAccess.Export
    public int getLightningTicksLeft() {
        return mcObject.getLightningTicksLeft();
    }

    @HostAccess.Export
    public float getBrightness(Direction direction, boolean shaded) {
        return mcObject.getBrightness(direction, shaded);
    }

    @HostAccess.Export
    public int getColor(BlockPos pos, ColorResolver colorResolver) {
        return mcObject.getColor(pos, colorResolver);
    }
    
    @HostAccess.Export
    public String toString() {
        return "ClientLevel";
    }

    @HostAccess.Export
    public WorldProperties getLevelProperties() {
        return new WorldProperties(mcObject.getLevelProperties());
    }

    @HostAccess.Export
    public String asString() {
        return mcObject.asString();
    }

    @HostAccess.Export
    public int getSimulationDistance() {
        return mcObject.getSimulationDistance();
    }

    @HostAccess.Export
    public FeatureSet getEnabledFeatures() {
        return mcObject.getEnabledFeatures();
    }

    @HostAccess.Export
    public int getSeaLevel() {
        return mcObject.getSeaLevel();
    }

    @HostAccess.Export
    public int getBlockColor(BlockPos pos) {
        return mcObject.getBlockColor(pos);
    }

}
