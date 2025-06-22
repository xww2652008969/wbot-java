package com.xww.core;

import com.xww.PluginManager.PluginControl;
import com.xww.client.Httpclient;
import com.xww.client.WsClient;
import com.xww.model.Message;
import lombok.Getter;

import java.util.concurrent.LinkedBlockingQueue;

public class Bot {

    @Getter
    private final BootConfig config;
    private final WsClient wsClient;
    private final LinkedBlockingQueue<Message> messagesQueue;  //接收的数据队列
    private final PluginControl pluginControl;

    public Bot(BootConfig config) {
        LogUtils.setIsDev(config.isDebug());
        if (config.getWsUrl() == null || config.getHttpUrl() == null) {
            throw new RuntimeException("没有配置wsRrl或者httpUrl");
        }
        this.config = config;
        wsClient = new WsClient(this.config);
        pluginControl = new PluginControl(this);
        messagesQueue = new LinkedBlockingQueue<>();
    }

    public void run() {
        this.initializePlugins();
        wsClient.run(messagesQueue);
        Event.postEvent(pluginControl, messagesQueue);
        LogUtils.debug(this.getClass(), "机器人启动了");
    }

    private void initializePlugins() {
        for (var p : pluginControl.getPlugins().values()) {
            p.setApi(new Httpclient(this.config));
        }
    }

}
