package com.xww.client;

import com.xww.Controller.WsControlle;
import com.xww.core.BootConfig;
import com.xww.model.Message;
import okhttp3.*;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WsClient {
    private OkHttpClient client;
    private WebSocket webSocket;
    private Request request;

    public WsClient(BootConfig config) {
        client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS) // 长连接无超时
                .build();
        Request.Builder builder = new Request.Builder()
                .url(config.getWsurl());
        if (config.getWstoken() != null) {
            builder.header("Authorization", "Bearer " + config.getWstoken());
        }
        request = builder.build();
    }

    public void Run(LinkedBlockingQueue<Message> Queue) {
        client.newWebSocket(request, new WsControlle(Queue));

    }
}
