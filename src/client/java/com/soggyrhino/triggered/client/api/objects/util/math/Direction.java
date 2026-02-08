package com.soggyrhino.triggered.client.api.objects.util.math;

import org.graalvm.polyglot.HostAccess;

public class Direction {

    @HostAccess.Export
    public static net.minecraft.util.math.Direction fromString(String name) {
        return switch (name) {
            case "UP" -> net.minecraft.util.math.Direction.UP;
            case "DOWN" -> net.minecraft.util.math.Direction.DOWN;
            case "SOUTH" -> net.minecraft.util.math.Direction.SOUTH;
            case "WEST" -> net.minecraft.util.math.Direction.WEST;
            case "EAST" -> net.minecraft.util.math.Direction.EAST;
            default -> net.minecraft.util.math.Direction.NORTH;
        };
    }

    @HostAccess.Export
    public static String toString(net.minecraft.util.math.Direction direction) {
        return switch (direction) {
            case net.minecraft.util.math.Direction.UP -> "UP";
            case net.minecraft.util.math.Direction.DOWN -> "DOWN";
            case net.minecraft.util.math.Direction.NORTH -> "NORTH";
            case net.minecraft.util.math.Direction.SOUTH -> "SOUTH";
            case net.minecraft.util.math.Direction.WEST -> "WEST";
            case net.minecraft.util.math.Direction.EAST -> "EAST";
        };
    }

    @HostAccess.Export
    public static String getOpposite(String direction) {
        return toString(fromString(direction).getOpposite());
    }

    @HostAccess.Export
    public static String rotateClockwise(String direction, String axis) {
        return toString(fromString(direction).rotateClockwise(Axis.fromString(axis)));
    }

    @HostAccess.Export
    public static String rotateCounterclockwise(String direction, String axis) {
        return toString(fromString(direction).rotateCounterclockwise(Axis.fromString(axis)));
    }

    @HostAccess.Export
    public static String rotateYClockwise(String direction) {
        return toString(fromString(direction).rotateYClockwise());
    }

    @HostAccess.Export
    public static String rotateYCounterclockwise(String direction) {
        return toString(fromString(direction).rotateYCounterclockwise());
    }

    @HostAccess.Export
    public static int getOffsetX(String direction) {
        return fromString(direction).getOffsetX();
    }

    @HostAccess.Export
    public static int getOffsetY(String direction) {
        return fromString(direction).getOffsetY();
    }

    @HostAccess.Export
    public static int getOffsetZ(String direction) {
        return fromString(direction).getOffsetZ();
    }

    @HostAccess.Export
    public static String getId(String direction) {
        return fromString(direction).getId();
    }

    @HostAccess.Export
    public static String getAxis(String direction) {
        return Axis.toString(fromString(direction).getAxis());
    }

    @HostAccess.Export
    public static String fromHorizontalDegrees(double angle) {
        return toString(net.minecraft.util.math.Direction.fromHorizontalDegrees(angle));
    }

    @HostAccess.Export
    public static float getPositiveHorizontalDegrees(String direction) {
        return fromString(direction).getPositiveHorizontalDegrees();
    }

    @HostAccess.Export
    public static String getFacing(double x, double y, double z) {
        return toString(net.minecraft.util.math.Direction.getFacing(x, y, z));
    }

    public static class Axis {

        @HostAccess.Export
        public static net.minecraft.util.math.Direction.Axis fromString(String name) {
            return switch (name) {
                case "X" -> net.minecraft.util.math.Direction.Axis.X;
                case "Y" -> net.minecraft.util.math.Direction.Axis.Y;
                case "Z" -> net.minecraft.util.math.Direction.Axis.Z;
                default -> net.minecraft.util.math.Direction.Axis.Y;
            };
        }

        @HostAccess.Export
        public static String toString(net.minecraft.util.math.Direction.Axis axis) {
            return switch (axis) {
                case net.minecraft.util.math.Direction.Axis.X -> "X";
                case net.minecraft.util.math.Direction.Axis.Y -> "Y";
                case net.minecraft.util.math.Direction.Axis.Z -> "Z";
            };
        }

        @HostAccess.Export
        public static String getId(String axis) {
            return fromString(axis).getId();
        }

        @HostAccess.Export
        public static boolean isVertical(String axis) {
            return fromString(axis).isVertical();
        }

        @HostAccess.Export
        public static boolean isHorizontal(String axis) {
            return fromString(axis).isHorizontal();
        }

        @HostAccess.Export
        public static String getPositiveDirection(String axis) {
            return Direction.toString(fromString(axis).getPositiveDirection());
        }

        @HostAccess.Export
        public static String getNegativeDirection(String axis) {
            return Direction.toString(fromString(axis).getNegativeDirection());
        }

        @HostAccess.Export
        public static int choose(String axis, int x, int y, int z) {
            return fromString(axis).choose(x, y, z);
        }

        @HostAccess.Export
        public static double choose(String axis, double x, double y, double z) {
            return fromString(axis).choose(x, y, z);
        }

        @HostAccess.Export
        public static boolean choose(String axis, boolean x, boolean y, boolean z) {
            return fromString(axis).choose(x, y, z);
        }
    }

    public static class AxisDirection {

        @HostAccess.Export
        public static net.minecraft.util.math.Direction.AxisDirection fromString(String name) {
            return switch (name) {
                case "POSITIVE" -> net.minecraft.util.math.Direction.AxisDirection.POSITIVE;
                case "NEGATIVE" -> net.minecraft.util.math.Direction.AxisDirection.NEGATIVE;
                default -> net.minecraft.util.math.Direction.AxisDirection.POSITIVE;
            };
        }

        @HostAccess.Export
        public static String toString(net.minecraft.util.math.Direction.AxisDirection axisDirection) {
            return switch (axisDirection) {
                case net.minecraft.util.math.Direction.AxisDirection.POSITIVE -> "POSITIVE";
                case net.minecraft.util.math.Direction.AxisDirection.NEGATIVE -> "NEGATIVE";
            };
        }

        @HostAccess.Export
        public static int offset(String axisDirection) {
            return fromString(axisDirection).offset();
        }

        @HostAccess.Export
        public static String getDescription(String axisDirection) {
            return fromString(axisDirection).getDescription();
        }

        @HostAccess.Export
        public static String getOpposite(String axisDirection) {
            return toString(fromString(axisDirection).getOpposite());
        }
    }
}