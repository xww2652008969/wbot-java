package com.xww.core;

public class BootConfig {
    private String Wsurl;
    private String Wstoken;
    private String Httpurl;
    private String Httptoken;


    public void BootConfig() {
        Wsurl = "";
        Wstoken = "";
        Httpurl = "";
        Httptoken = "";
    }

    public String getWsurl() {
        return Wsurl;
    }

    public BootConfig setWsurl(String wsurl) {
        Wsurl = wsurl;
        return this;
    }

    public String getWstoken() {
        return Wstoken;
    }

    public BootConfig setWstoken(String wstoken) {
        Wstoken = wstoken;
        return this;
    }

    public String getHttpurl() {
        return Httpurl;
    }

    public BootConfig setHttpurl(String httpurl) {
        Httpurl = httpurl;
        return this;
    }

    public String getHttptoken() {
        return Httptoken;
    }

    public BootConfig setHttptoken(String httptoken) {
        Httptoken = httptoken;
        return this;
    }
}
