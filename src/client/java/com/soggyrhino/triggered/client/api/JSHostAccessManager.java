package com.soggyrhino.triggered.client.api;

import com.soggyrhino.triggered.client.api.annotations.Event;
import com.soggyrhino.triggered.client.api.annotations.MCObject;
import org.graalvm.polyglot.HostAccess;

public class JSHostAccessManager {
    public static HostAccess access;

    public static void init(){
        access = HostAccess.newBuilder(HostAccess.EXPLICIT)
                .allowAccessAnnotatedBy(HostAccess.Export.class)
                .allowAccessAnnotatedBy(Event.class)
                .allowAccessAnnotatedBy(MCObject.class)
                .build();
    }

    public static HostAccess getHostAccess() {
        return access;
    }

}
