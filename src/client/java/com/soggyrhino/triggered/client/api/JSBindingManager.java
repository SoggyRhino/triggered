package com.soggyrhino.triggered.client.api;

import com.soggyrhino.triggered.client.api.annotations.Event;
import com.soggyrhino.triggered.client.api.annotations.Library;
import com.soggyrhino.triggered.client.api.annotations.MCObject;
import com.soggyrhino.triggered.client.engine.Module;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;


public class JSBindingManager {
    private static Set<Class<?>> events;
    private static Set<Class<?>> objects;
    private static Set<Class<?>> libraries;


    //todo shared code
    public static void init() {
        Reflections reflections = new Reflections(JSBindingManager.class.getPackage().getName());
        events = reflections.getTypesAnnotatedWith(Event.class);
        objects = reflections.getTypesAnnotatedWith(MCObject.class);
        libraries = reflections.getTypesAnnotatedWith(Library.class);
    }

    public static void setupBindings(Module module) {
        final Context context = module.getContext();
        final Value bindings = context.getBindings("js");

        events.forEach(clazz -> bindings.putMember(clazz.getSimpleName(), context.asValue(clazz)));
        objects.forEach(clazz -> bindings.putMember(clazz.getSimpleName(), context.asValue(clazz)));

        libraries.forEach(clazz -> {
            try {
                Library annotation = clazz.getAnnotation(Library.class);
                Object instance = annotation.requiresModule()
                        ? clazz.getDeclaredConstructor(Module.class).newInstance(module)
                        : clazz.getDeclaredConstructor().newInstance();

                Value value = module.toValue(instance);
                if (annotation.anonymous()) {
                    for (String memberKey : value.getMemberKeys()) {
                        bindings.putMember(memberKey, value.getMember(memberKey));
                    }
                } else {
                    bindings.putMember(clazz.getSimpleName(), value);
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException("Failed to instantiate @Library: " + clazz.getName(), e);
            }
        });
    }
}