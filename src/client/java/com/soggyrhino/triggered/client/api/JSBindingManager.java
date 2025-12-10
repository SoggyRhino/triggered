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

/**
 * Manages JavaScript bindings for the Triggered mod API.
 * Scans and registers annotated classes to be accessible from JavaScript code.
 */
public class JSBindingManager {
    private static Set<Class<?>> events;
    private static Set<Class<?>> objects;
    private static Set<Class<?>> libraries;

    /**
     * Initializes the binding manager by scanning the API package for annotated classes.
     * Must be called once during mod initialization before setupBindings().
     */
    public static void init() {
        Reflections reflections = new Reflections(JSBindingManager.class.getPackage().getName());
        events = reflections.getTypesAnnotatedWith(Event.class);
        objects = reflections.getTypesAnnotatedWith(MCObject.class);
        libraries = reflections.getTypesAnnotatedWith(Library.class);
    }

    /**
     * Sets up JavaScript bindings for a module's GraalVM context.
     * Registers all discovered @Event, @MCObject, and @Library classes.
     *
     * @param module The module to set up bindings for
     * @throws RuntimeException if a @Library class cannot be instantiated
     */
    public static void setupBindings(Module module) {
        assert events != null && objects != null && libraries != null : "Bindings not initialized";
        final Context context = module.getContext();
        final Value bindings = context.getBindings("js");

        // Expose event and object classes as constructors
        events.forEach(clazz -> bindings.putMember(clazz.getSimpleName(), context.asValue(clazz)));
        objects.forEach(clazz -> bindings.putMember(clazz.getSimpleName(), context.asValue(clazz)));

        // Instantiate and expose library classes
        libraries.forEach(clazz -> {
            try {
                Library annotation = clazz.getAnnotation(Library.class);

                // Create an instance (with or without module dependency)
                Object instance = annotation.requiresModule()
                        ? clazz.getDeclaredConstructor(Module.class).newInstance(module)
                        : clazz.getDeclaredConstructor().newInstance();

                Value value = module.toValue(instance);

                // Anonymous libraries spread members into a global scope
                // Named libraries are exposed as a single object
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