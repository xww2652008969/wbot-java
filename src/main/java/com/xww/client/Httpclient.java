package com.xww.client;

import com.xww.core.BootConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Httpclient {
    private final OkHttpClient client;
    private final BootConfig config;

    public Httpclient(BootConfig config) {
        client = new OkHttpClient.Builder().build();
        this.config = config;
    }

    public Response post(String path, String data) {
        Request.Builder b = new Request.Builder();
        if (this.config.getHttpToken() != null) {
            b.header("Authorization", "Bearer " + this.config.getHttpToken());
        }

        Request request = b.url(this.config.getHttpUrl() + path)
                .post(RequestBody.create(data, MediaType.get("application/json; charset=utf-8")))
                .build();

        try {
            return client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("HTTP 请求失败: " + e.getMessage(), e);
        }
    }
}
