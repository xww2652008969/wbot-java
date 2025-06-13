package com.xww.bot;

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

    public String Wsurl() {
        return Wsurl;
    }

    public BootConfig setWsurl(String wsurl) {
        Wsurl = wsurl;
        return this;
    }

    public String Wstoken() {
        return Wstoken;
    }

    public BootConfig setWstoken(String wstoken) {
        Wstoken = wstoken;
        return this;
    }

    public String Httpurl() {
        return Httpurl;
    }

    public BootConfig setHttpurl(String httpurl) {
        Httpurl = httpurl;
        return this;
    }

    public String Httptoken() {
        return Httptoken;
    }

    public BootConfig setHttptoken(String httptoken) {
        Httptoken = httptoken;
        return this;
    }
}
