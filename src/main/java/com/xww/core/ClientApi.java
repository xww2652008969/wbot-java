package com.xww.core;

import com.alibaba.fastjson2.JSON;
import com.xww.client.Httpclient;
import com.xww.model.ChatMessage;
import okhttp3.Response;

public class ClientApi {
    private ChatMessage chatmessage;
    private Httpclient client;

    public ClientApi(Httpclient c) {
        client = c;
    }

    public Httpclient GetClient() {
        return client;
    }

    public ChatMessage GetChatmessage() {
        return chatmessage;
    }

    public void SetChatmessage(MessagesApi mes) {
        this.chatmessage = mes.getMessage();
    }

    public void SetClient(Httpclient client) {
        this.client = client;
    }

    public Response SendGroupMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.Post("/send_group_msg", jsonString);
    }

    public Response SendPrivateMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.Post("/send_private_msg", jsonString);
    }
}
