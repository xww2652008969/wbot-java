package com.xww.core;

import com.xww.PluginManager.PluginScanner;
import com.xww.client.Httpclient;
import com.xww.client.WsClient;
import com.xww.event.Event;
import com.xww.model.Message;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Bot {
    private final BootConfig config;
    private final WsClient wsClient;
    private final LinkedBlockingQueue<Message> messagesQueue;  //接收的数据队列
    private final Set<BasePlugins> Plugins;

    public Bot(BootConfig config) {

        if (config.getWsUrl() == null || config.getHttpUrl() == null) {
            throw new RuntimeException("没有配置wsRrl或者httpUrl");
        }
        this.config = config;
        wsClient = new WsClient(this.config);
        Plugins = PluginScanner.scanAllPlugins();
        messagesQueue = new LinkedBlockingQueue<>();
    }

    public void run() {
        this.initializePlugins();
        wsClient.run(messagesQueue);
        Event.postEvent(Plugins, messagesQueue);
    }

    private void initializePlugins() {
        for (var p : this.Plugins) {
            p.setApi(new Httpclient(this.config));
        }
    }
}
