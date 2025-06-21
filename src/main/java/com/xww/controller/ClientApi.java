package com.xww.controller;

import com.alibaba.fastjson2.JSON;
import com.xww.client.Httpclient;
import com.xww.model.ChatMessage;
import okhttp3.Response;

import java.util.HashMap;

public class ClientApi {
    public ChatMessage chatMessage;
    private Httpclient client;

    public ClientApi(Httpclient c) {
        client = c;
    }

    /**
     * 设置一个用于发送群聊消息的 ChatMessage 对象。
     *
     * @param groupId 群号
     */
    public void setGroupMessage(long groupId) {
        chatMessage = new ChatMessage(groupId, 0);
    }

    /**
     * 设置一个用于发送私聊消息的 ChatMessage 对象。
     *
     * @param userId 用户 QQ 号
     */
    public void setPrivateMessage(long userId) {
        chatMessage = new ChatMessage(0, userId);
    }

    public Response sendGroupMsg() {
        String jsonString = JSON.toJSONString(chatMessage);
        return client.post("/send_group_msg", jsonString);
    }

    public Response sendPrivateMsg() {
        String jsonString = JSON.toJSONString(chatMessage);
        return client.post("/send_private_msg", jsonString);
    }

    public Response sendForwardMsg(String data) {
        return client.post("/send_forward_msg", data);
    }

    public Response sendGroupPoke(Long groupId, Long userId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("group_id", String.valueOf(groupId));
        h.put("user_id", String.valueOf(userId));
        return client.post("/group_poke", JSON.toJSONString(h));
    }

    public Response sendPrivatePoke(Long userId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("user_id", String.valueOf(userId));
        return client.post("/friend_poke", JSON.toJSONString(h));
    }

    public Response deleteMsg(Long msgId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("message_id", String.valueOf(msgId));
        return client.post("/delete_msg", JSON.toJSONString(h));
    }
}
