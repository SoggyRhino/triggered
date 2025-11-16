package com.soggyrhino.triggered.client.api.lib;

import com.soggyrhino.triggered.client.api.annotations.Library;
import org.graalvm.polyglot.HostAccess;

@Library
public class Http {

    @HostAccess.Export
    public void Get(String url, String[][] headers) {
        //todo
    }

    @HostAccess.Export
    public void Post(String url, String[][] headers) {
        //todo
    }
}
