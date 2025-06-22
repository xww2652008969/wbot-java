package com.xww.client;

import com.alibaba.fastjson2.JSON;
import com.xww.core.BootConfig;
import com.xww.model.HttpResult;
import okhttp3.*;

import java.io.IOException;

public class Httpclient {
    private final OkHttpClient client;
    private final BootConfig config;

    public Httpclient(BootConfig config) {
        client = new OkHttpClient.Builder().build();
        this.config = config;
    }

    public HttpResult post(String path, String data) {
        Request.Builder b = new Request.Builder();
        if (this.config.getHttpToken() != null) {
            config.getHttpUrl();
            b.header("Authorization", "Bearer " + this.config.getHttpToken());
        }

        Request request = b.url(this.config.getHttpUrl() + path)
                .post(RequestBody.create(data, MediaType.get("application/json; charset=utf-8")))
                .build();

        try {
            var response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("请求失败，状态码：" + response.code());
            }
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new IOException("响应体为空");
            }
            String json = responseBody.string();
            HttpResult httpResult = JSON.parseObject(json, HttpResult.class);
            return httpResult;
            
        } catch (IOException e) {
            throw new RuntimeException("HTTP 请求失败: " + e.getMessage(), e);
        }
    }
}
