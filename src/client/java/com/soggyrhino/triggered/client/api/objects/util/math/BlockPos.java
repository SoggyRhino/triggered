package com.soggyrhino.triggered.client.api.objects.util.math;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.graalvm.polyglot.HostAccess;

public class BlockPos {

    public net.minecraft.util.math.BlockPos mcObject;

    public BlockPos(net.minecraft.util.math.BlockPos mcObject) {
        this.mcObject = mcObject;
    }

    public BlockPos(int x, int y, int z) {
        this.mcObject = new net.minecraft.util.math.BlockPos(x, y, z);
    }

    public BlockPos(Vec3i pos) {
        this.mcObject = new net.minecraft.util.math.BlockPos(pos);
    }

    @HostAccess.Export
    public static BlockPos fromLong(long packedPos) {
        return new BlockPos(net.minecraft.util.math.BlockPos.fromLong(packedPos));
    }

    @HostAccess.Export
    public static BlockPos ofFloored(double x, double y, double z) {
        return new BlockPos(net.minecraft.util.math.BlockPos.ofFloored(x, y, z));
    }

    @HostAccess.Export
    public static BlockPos ofFloored(Vec3d pos) {
        return new BlockPos(net.minecraft.util.math.BlockPos.ofFloored(pos));
    }

    @HostAccess.Export
    public static BlockPos min(BlockPos a, BlockPos b) {
        return new BlockPos(net.minecraft.util.math.BlockPos.min(a.mcObject, b.mcObject));
    }

    @HostAccess.Export
    public static BlockPos max(BlockPos a, BlockPos b) {
        return new BlockPos(net.minecraft.util.math.BlockPos.max(a.mcObject, b.mcObject));
    }

    @HostAccess.Export
    public int getX() {
        return mcObject.getX();
    }

    @HostAccess.Export
    public int getY() {
        return mcObject.getY();
    }

    @HostAccess.Export
    public int getZ() {
        return mcObject.getZ();
    }

    @HostAccess.Export
    public long asLong() {
        return mcObject.asLong();
    }

    @HostAccess.Export
    public Vec3d toCenterPos() {
        return mcObject.toCenterPos();
    }

    @HostAccess.Export
    public Vec3d toBottomCenterPos() {
        return mcObject.toBottomCenterPos();
    }

    @HostAccess.Export
    public BlockPos add(int x, int y, int z) {
        return new BlockPos(mcObject.add(x, y, z));
    }

    @HostAccess.Export
    public BlockPos add(Vec3i vec) {
        return new BlockPos(mcObject.add(vec));
    }

    @HostAccess.Export
    public BlockPos subtract(Vec3i vec) {
        return new BlockPos(mcObject.subtract(vec));
    }

    @HostAccess.Export
    public BlockPos multiply(int multiplier) {
        return new BlockPos(mcObject.multiply(multiplier));
    }

    @HostAccess.Export
    public BlockPos up() {
        return new BlockPos(mcObject.up());
    }

    @HostAccess.Export
    public BlockPos up(int distance) {
        return new BlockPos(mcObject.up(distance));
    }

    @HostAccess.Export
    public BlockPos down() {
        return new BlockPos(mcObject.down());
    }

    @HostAccess.Export
    public BlockPos down(int distance) {
        return new BlockPos(mcObject.down(distance));
    }

    @HostAccess.Export
    public BlockPos north() {
        return new BlockPos(mcObject.north());
    }

    @HostAccess.Export
    public BlockPos north(int distance) {
        return new BlockPos(mcObject.north(distance));
    }

    @HostAccess.Export
    public BlockPos south() {
        return new BlockPos(mcObject.south());
    }

    @HostAccess.Export
    public BlockPos south(int distance) {
        return new BlockPos(mcObject.south(distance));
    }

    @HostAccess.Export
    public BlockPos west() {
        return new BlockPos(mcObject.west());
    }

    @HostAccess.Export
    public BlockPos west(int distance) {
        return new BlockPos(mcObject.west(distance));
    }

    @HostAccess.Export
    public BlockPos east() {
        return new BlockPos(mcObject.east());
    }

    @HostAccess.Export
    public BlockPos east(int distance) {
        return new BlockPos(mcObject.east(distance));
    }

    @HostAccess.Export
    public BlockPos offsetDirection(String direction) {
        return new BlockPos(mcObject.offset(Direction.fromString(direction)));
    }

    @HostAccess.Export
    public BlockPos offsetDirection(String direction, int distance) {
        return new BlockPos(mcObject.offset(Direction.fromString(direction), distance));
    }

    @HostAccess.Export
    public BlockPos offsetAxis(String axis, int distance) {
        return new BlockPos(mcObject.offset(Direction.Axis.fromString(axis), distance));
    }

    @HostAccess.Export
    public BlockPos rotateRotation(String rotation) {
        return new BlockPos(mcObject.rotate(BlockRotation.fromString(rotation)));
    }

    @HostAccess.Export
    public BlockPos crossProduct(Vec3i pos) {
        return new BlockPos(mcObject.crossProduct(pos));
    }

    @HostAccess.Export
    public BlockPos withY(int y) {
        return new BlockPos(mcObject.withY(y));
    }

    @HostAccess.Export
    public BlockPos toImmutable() {
        return new BlockPos(mcObject.toImmutable());
    }

    @HostAccess.Export
    public Mutable mutableCopy() {
        return new Mutable(mcObject.mutableCopy());
    }

    @HostAccess.Export
    public Vec3d clampToWithin(Vec3d pos) {
        return mcObject.clampToWithin(pos);
    }

    @HostAccess.Export
    public double getSquaredDistance(Vec3i pos) {
        return mcObject.getSquaredDistance(pos);
    }

    @HostAccess.Export
    public double getSquaredDistance(double x, double y, double z) {
        return mcObject.getSquaredDistance(x, y, z);
    }

    @HostAccess.Export
    public int getManhattanDistance(Vec3i pos) {
        return mcObject.getManhattanDistance(pos);
    }

    @HostAccess.Export
    public boolean isWithinDistance(Vec3i pos, double distance) {
        return mcObject.isWithinDistance(pos, distance);
    }

    @Override
    public String toString() {
        return mcObject.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockPos blockPos = (BlockPos) obj;
        return mcObject.equals(blockPos.mcObject);
    }

    @Override
    public int hashCode() {
        return mcObject.hashCode();
    }

    public static class Mutable extends BlockPos {

        public Mutable(net.minecraft.util.math.BlockPos.Mutable mcObject) {
            super(mcObject);
        }

        public Mutable() {
            super(new net.minecraft.util.math.BlockPos.Mutable());
        }

        public Mutable(int x, int y, int z) {
            super(new net.minecraft.util.math.BlockPos.Mutable(x, y, z));
        }

        public Mutable(double x, double y, double z) {
            super(new net.minecraft.util.math.BlockPos.Mutable(x, y, z));
        }

        private net.minecraft.util.math.BlockPos.Mutable getMutable() {
            return (net.minecraft.util.math.BlockPos.Mutable) mcObject;
        }

        @HostAccess.Export
        public Mutable set(int x, int y, int z) {
            getMutable().set(x, y, z);
            return this;
        }

        @HostAccess.Export
        public Mutable set(double x, double y, double z) {
            getMutable().set(x, y, z);
            return this;
        }

        @HostAccess.Export
        public Mutable set(Vec3i pos) {
            getMutable().set(pos);
            return this;
        }

        @HostAccess.Export
        public Mutable set(long pos) {
            getMutable().set(pos);
            return this;
        }

        @HostAccess.Export
        public Mutable set(Vec3i pos, String direction) {
            getMutable().set(pos, Direction.fromString(direction));
            return this;
        }

        @HostAccess.Export
        public Mutable set(Vec3i pos, int x, int y, int z) {
            getMutable().set(pos, x, y, z);
            return this;
        }

        @HostAccess.Export
        public Mutable set(Vec3i vec1, Vec3i vec2) {
            getMutable().set(vec1, vec2);
            return this;
        }

        @HostAccess.Export
        public Mutable moveDirection(String direction) {
            getMutable().move(Direction.fromString(direction));
            return this;
        }

        @HostAccess.Export
        public Mutable moveDirection(String direction, int distance) {
            getMutable().move(Direction.fromString(direction), distance);
            return this;
        }

        @HostAccess.Export
        public Mutable move(int dx, int dy, int dz) {
            getMutable().move(dx, dy, dz);
            return this;
        }

        @HostAccess.Export
        public Mutable move(Vec3i vec) {
            getMutable().move(vec);
            return this;
        }

        @HostAccess.Export
        public Mutable clamp(String axis, int min, int max) {
            getMutable().clamp(Direction.Axis.fromString(axis), min, max);
            return this;
        }

        @HostAccess.Export
        public Mutable setX(int x) {
            getMutable().setX(x);
            return this;
        }

        @HostAccess.Export
        public Mutable setY(int y) {
            getMutable().setY(y);
            return this;
        }

        @HostAccess.Export
        public Mutable setZ(int z) {
            getMutable().setZ(z);
            return this;
        }

        @Override
        @HostAccess.Export
        public BlockPos toImmutable() {
            return new BlockPos(getMutable().toImmutable());
        }
    }
}