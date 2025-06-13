package com.xww.bot;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BootConfig config = new BootConfig();
        config.setWsurl("ws://192.168.2.3:3001");
        config.setHttpurl("http://192.168.2.3:4000");
        var bot = new Bot(config);
        bot.Run();

//        OkHttpClient client = new OkHttpClient.Builder().build();
//        Request request = new Request.Builder().url("http://192.168.2.3:4000/send_group_msg").get().build();
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response.code());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
