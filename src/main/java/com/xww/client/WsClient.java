package com.xww.client;

import com.xww.controller.WsController;
import com.xww.core.BootConfig;
import com.xww.model.Message;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WsClient {
    private final OkHttpClient client;
    private WebSocket webSocket;
    private final Request request;

    public WsClient(BootConfig config) {
        client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS) // 长连接无超时
                .build();

        Request.Builder builder = new Request.Builder()
                .url(config.getWsUrl());

        if (config.getWsToken() != null) {
            builder.header("Authorization", "Bearer " + config.getWsToken());
        }

        request = builder.build();
    }

    public void run(LinkedBlockingQueue<Message> queue) {
        webSocket = client.newWebSocket(request, new WsController(queue));
    }

    public void shutdown() {
        if (webSocket != null) {
            webSocket.close(1000, "Shutdown");
        }
        client.dispatcher().executorService().shutdown();
    }
}
