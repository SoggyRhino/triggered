package com.soggyrhino.triggered.client.api.objects.client.utils;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

@MCObject
public class Window {
    public net.minecraft.client.util.Window mcObject;

    public Window(net.minecraft.client.util.Window window) {
        mcObject = window;
    }

    @HostAccess.Export
    public int getRefreshRate() {
        return mcObject.getRefreshRate();
    }

    @HostAccess.Export
    public boolean isFullscreen() {
        return mcObject.isFullscreen();
    }

    @HostAccess.Export
    public boolean isMinimized() {
        return mcObject.isMinimized();
    }

    @HostAccess.Export
    public int getWidth() {
        return mcObject.getWidth();
    }

    @HostAccess.Export
    public int getHeight() {
        return mcObject.getHeight();
    }

    @HostAccess.Export
    public int getScaledWidth() {
        return mcObject.getScaledWidth();
    }

    @HostAccess.Export
    public int getScaledHeight() {
        return mcObject.getScaledHeight();
    }

    @HostAccess.Export
    public int getX() {
        return mcObject.getX();
    }

    @HostAccess.Export
    public int getY() {
        return mcObject.getY();
    }

    public int getScaleFactor() {
        return mcObject.getScaleFactor();
    }

}