package com.xww.client;

import com.xww.bot.BootConfig;
import okhttp3.*;

import java.io.IOException;

public class Httpclient {
    private final OkHttpClient client;
    private BootConfig config;

    public Httpclient(BootConfig config) {
        client = new OkHttpClient.Builder()
                .build();
        this.config = config;
    }

    public Response Post(String path, String data) {

        var b = new Request.Builder();
        if (this.config.Httptoken() != null) {
            b.header("Authorization", "Bearer " + this.config.Httptoken());
        }
        var request = b.url(this.config.Httpurl() + path)
                .post(RequestBody.create(data, MediaType.get("application/json; charset=utf-8"))).build();
        try {
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            return null;
        }
    }
}
