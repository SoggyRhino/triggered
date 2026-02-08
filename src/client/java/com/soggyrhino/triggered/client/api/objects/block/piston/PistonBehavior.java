package com.soggyrhino.triggered.client.api.objects.block.piston;

public class PistonBehavior {

    public static net.minecraft.block.piston.PistonBehavior fromString(String name) {
        return switch (name) {
            case "NORMAL" -> net.minecraft.block.piston.PistonBehavior.NORMAL;
            case "DESTROY" -> net.minecraft.block.piston.PistonBehavior.DESTROY;
            case "BLOCK" -> net.minecraft.block.piston.PistonBehavior.BLOCK;
            case "IGNORE" -> net.minecraft.block.piston.PistonBehavior.IGNORE;
            case "PUSH_ONLY" -> net.minecraft.block.piston.PistonBehavior.PUSH_ONLY;
            default -> throw new IllegalArgumentException("Unknown PistonBehavior: " + name);
        };
    }

    public static String toString(net.minecraft.block.piston.PistonBehavior behavior) {
        return switch (behavior) {
            case net.minecraft.block.piston.PistonBehavior.NORMAL -> "NORMAL";
            case net.minecraft.block.piston.PistonBehavior.DESTROY -> "DESTROY";
            case net.minecraft.block.piston.PistonBehavior.BLOCK -> "BLOCK";
            case net.minecraft.block.piston.PistonBehavior.IGNORE -> "IGNORE";
            case net.minecraft.block.piston.PistonBehavior.PUSH_ONLY -> "PUSH_ONLY";
        };
    }

}