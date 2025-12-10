package com.soggyrhino.triggered.client.api;

import com.soggyrhino.triggered.client.api.annotations.Event;
import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

/**
 * Manages host access configuration for the GraalVM JavaScript engine.
 * Controls which Java classes and members are accessible from JavaScript code.
 * Uses explicit whitelisting for security - only annotated members are exposed.
 */
public class JSHostAccessManager {
    private static HostAccess access;

    /**
     * Initializes the host access policy with explicit whitelisting.
     * Only members annotated with @Export, @Event, or @MCObject are accessible from JS.
     * Must be called once during mod initialization.
     */
    public static void init(){
        access = HostAccess.newBuilder(HostAccess.EXPLICIT)
                .allowAccessAnnotatedBy(HostAccess.Export.class)
                .allowAccessAnnotatedBy(Event.class)
                .allowAccessAnnotatedBy(MCObject.class)
                .build();
    }

    /**
     * Gets the configured host access policy.
     *
     * @return The HostAccess configuration for use with GraalVM contexts
     */
    public static HostAccess getHostAccess() {
        assert access != null : "HostAccess not initialized";
        return access;
    }
}