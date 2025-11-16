package com.soggyrhino.triggered.client.listeners;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ListenersManager {

    public static void registerListeners() {
        final Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackage(ListenersManager.class.getPackage().getName())
                        .setScanners(Scanners.MethodsAnnotated)
                        .setExpandSuperTypes(false)
        );

        reflections.getMethodsAnnotatedWith(Listener.class).forEach(listener -> {
            try {
                validateListener(listener);
                listener.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException("Failed to register listener", e);
            }
        });
    }

    private static void validateListener(Method method) {
        if (!Modifier.isStatic(method.getModifiers())) {
            throw new IllegalStateException("@Listener method must be static: " + method);
        }
        if (method.getParameterCount() != 0) {
            throw new IllegalStateException("@Listener method must have no parameters: " + method);
        }
    }

}
