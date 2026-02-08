package com.soggyrhino.triggered.client.api.objects.util.math;

import org.graalvm.polyglot.HostAccess;

public class BlockRotation {

    @HostAccess.Export
    public static net.minecraft.util.BlockRotation fromString(String name) {
        return switch (name) {
            case "CLOCKWISE_90" -> net.minecraft.util.BlockRotation.CLOCKWISE_90;
            case "CLOCKWISE_180" -> net.minecraft.util.BlockRotation.CLOCKWISE_180;
            case "COUNTERCLOCKWISE_90" -> net.minecraft.util.BlockRotation.COUNTERCLOCKWISE_90;
            case "NONE" -> net.minecraft.util.BlockRotation.NONE;
            default -> throw new IllegalArgumentException("Unknown block rotation name: " + name);
        };
    }

    @HostAccess.Export
    public static String toString(net.minecraft.util.BlockRotation rotation) {
        return switch (rotation) {
            case net.minecraft.util.BlockRotation.CLOCKWISE_90 -> "CLOCKWISE_90";
            case net.minecraft.util.BlockRotation.CLOCKWISE_180 -> "CLOCKWISE_180";
            case net.minecraft.util.BlockRotation.COUNTERCLOCKWISE_90 -> "COUNTERCLOCKWISE_90";
            case net.minecraft.util.BlockRotation.NONE -> "NONE";
        };
    }

    @HostAccess.Export
    public static String rotateRotation(String base, String rotation) {
        return toString(fromString(base).rotate(fromString(rotation)));
    }

    @HostAccess.Export
    public static String rotateDirection(String rotation, String direction) {
        return Direction.toString(BlockRotation.fromString(rotation).rotate(Direction.fromString(direction)));
    }

    @HostAccess.Export
    public static int rotateInt(String rotation, int value, int fullTurn) {
        return fromString(rotation).rotate(value, fullTurn);
    }

}