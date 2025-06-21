package com.xww.client;

import com.xww.core.BootConfig;
import okhttp3.*;

import java.io.IOException;

public class Httpclient {
    private final OkHttpClient client;
    private final BootConfig config;

    public Httpclient(BootConfig config) {
        client = new OkHttpClient.Builder()
                .build();
        this.config = config;
    }

    public Response post(String path, String data) {

        var b = new Request.Builder();
        if (this.config.getHttptoken() != null) {
            b.header("Authorization", "Bearer " + this.config.getHttptoken());
        }
        var request = b.url(this.config.getHttpurl() + path)
                .post(RequestBody.create(data, MediaType.get("application/json; charset=utf-8"))).build();
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            return null;
        }
    }
}
