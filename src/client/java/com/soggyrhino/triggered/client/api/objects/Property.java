package com.soggyrhino.triggered.client.api.objects;

import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;
import org.jetbrains.annotations.NotNull;

@MCObject
public record Property(@HostAccess.Export String name, @HostAccess.Export String value,
                       @HostAccess.Export String signature) {

    public static Property fromMc(com.mojang.authlib.properties.Property mcProperty) {
        if (mcProperty == null) {
            return null;
        }
        return new Property(mcProperty.name(), mcProperty.value(), mcProperty.signature());
    }

    @Override
    public @NotNull String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}