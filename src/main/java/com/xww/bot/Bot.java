package com.xww.bot;

import com.xww.PluginManager.PluginScanner;
import com.xww.client.Httpclient;
import com.xww.client.WsClient;
import com.xww.event.Event;
import com.xww.model.Message;
import com.xww.model.Plugins;

import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class Bot {
    private final BootConfig config;
    private final WsClient wsClient;


    private LinkedBlockingQueue<Message> messagesQueue;  //接收的数据队列
    private Set<Plugins> Plugins;

    public Bot(BootConfig config) {

        if (config.Wsurl() == null || config.Httpurl() == null) {
            throw new RuntimeException("没有配置Wsurl或者Httpurl");
        }

        this.config = config;
        wsClient = new WsClient(this.config);
        Plugins = PluginScanner.scanAllPlugins();
        messagesQueue = new LinkedBlockingQueue<>();
    }

    public void Run() {
        this.InitializePlugins();
        wsClient.Run(messagesQueue);
        Event.PostEvent(Plugins, messagesQueue);
    }

    private void InitializePlugins() {
        for (var p : this.Plugins) {
            p.setHttpclient(new Httpclient(this.config));
        }
    }


}
