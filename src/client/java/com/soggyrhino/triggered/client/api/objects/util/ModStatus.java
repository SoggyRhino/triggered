package com.soggyrhino.triggered.client.api.objects.util;

public class ModStatus {
    public com.soggyrhino.triggered.client.api.objects.util.ModStatus mcObject;

    public ModStatus(com.soggyrhino.triggered.client.api.objects.util.ModStatus mcObject) {
        this.mcObject = mcObject;
    }

    public boolean isModded() {
        return this.mcObject.isModded();
    }

    public String getMessage() {
        return this.mcObject.getMessage();
    }

    public ModStatus combine(ModStatus brand) {
        return mcObject.combine(brand.mcObject);
    }
}
