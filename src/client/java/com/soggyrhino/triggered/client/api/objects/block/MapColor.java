package com.soggyrhino.triggered.client.api.objects.block;

import org.graalvm.polyglot.HostAccess;

/**
 * Represents the surface color of a block when rendered from the {@link net.minecraft.client.render.MapRenderer}.
 * Color names refer to a material or an object which refers to their vanilla Minecraft textures, not their real-world counterparts, eg. "emerald green".
 * Names are in the form of either <i>blockReference_baseColor</i> or <i>color</i>.
 *
 * <p>When the map is rendered, the {@link net.minecraft.block.MapColor.Brightness#brightness} value is added to the
 * base color. The "rendered color" is internally represented as a byte; the first six bits
 * indicate the base color, and the last two bits indicate the brightness. This value is returned
 * from {@link net.minecraft.block.MapColor#getRenderColorByte} and is passed to {@link net.minecraft.block.MapColor#getRenderColor}.
 */
public class MapColor {
    public net.minecraft.block.MapColor mcObject;

    public MapColor(net.minecraft.block.MapColor mcObject) {
        this.mcObject = mcObject;
    }

    @HostAccess.Export
    public int getRenderColor(String brightness) {
        return mcObject.getRenderColor(Brightness.fromString(brightness));
    }

    @HostAccess.Export
    public byte getRenderColorByte(String brightness) {
        return mcObject.getRenderColorByte(Brightness.fromString(brightness));
    }

    public static class Brightness {
        public static net.minecraft.block.MapColor.Brightness fromString(String name) {
            return switch (name) {
                case "LOW" -> net.minecraft.block.MapColor.Brightness.LOW;
                case "HIGH" -> net.minecraft.block.MapColor.Brightness.HIGH;
                case "LOWEST" -> net.minecraft.block.MapColor.Brightness.LOWEST;
                default -> net.minecraft.block.MapColor.Brightness.NORMAL;
            };
        }

        public static String toString(net.minecraft.block.MapColor.Brightness brightness) {
            return switch (brightness) {
                case net.minecraft.block.MapColor.Brightness.LOW -> "LOW";
                case net.minecraft.block.MapColor.Brightness.NORMAL -> "NORMAL";
                case net.minecraft.block.MapColor.Brightness.HIGH -> "HIGH";
                case net.minecraft.block.MapColor.Brightness.LOWEST -> "LOWEST";
            };
        }
    }
}

