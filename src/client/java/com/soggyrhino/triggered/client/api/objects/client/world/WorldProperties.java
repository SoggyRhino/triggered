package com.soggyrhino.triggered.client.api.objects.client.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.HeightLimitView;
import org.graalvm.polyglot.HostAccess;

public class WorldProperties {
    public net.minecraft.client.world.ClientWorld.Properties mcObject;

    public WorldProperties(net.minecraft.client.world.ClientWorld.Properties mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public BlockPos getSpawnPos() {
        return mcObject.getSpawnPos();
    }

    @HostAccess.Export
    public float getSpawnAngle() {
        return mcObject.getSpawnAngle();
    }

    @HostAccess.Export
    public long getTime() {
        return mcObject.getTime();
    }

    @HostAccess.Export
    public long getTimeOfDay() {
        return mcObject.getTimeOfDay();
    }

    @HostAccess.Export
    public boolean isThundering() {
        return mcObject.isThundering();
    }

    @HostAccess.Export
    public boolean isRaining() {
        return mcObject.isRaining();
    }
    
    @HostAccess.Export
    public boolean isHardcore() {
        return mcObject.isHardcore();
    }

    @HostAccess.Export
    public Difficulty getDifficulty() {
        return mcObject.getDifficulty();
    }

    @HostAccess.Export
    public boolean isDifficultyLocked() {
        return mcObject.isDifficultyLocked();
    }
    
    @HostAccess.Export
    public double getSkyDarknessHeight(HeightLimitView world) {
        return mcObject.getSkyDarknessHeight(world);
    }

    @HostAccess.Export
    public float getVoidDarknessRange() {
        return mcObject.getVoidDarknessRange();
    }
}