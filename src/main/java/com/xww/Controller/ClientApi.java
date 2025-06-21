package com.xww.Controller;

import com.alibaba.fastjson2.JSON;
import com.xww.client.Httpclient;
import com.xww.model.ChatMessage;
import okhttp3.Response;

import java.util.HashMap;

public class ClientApi {
    public ChatMessage chatmessage;
    private Httpclient client;

    public ClientApi(Httpclient c) {
        client = c;
    }

    /**
     * @param groupid 群号 创建一个用于发送群聊消息的ChatMessage
     */
    public void setGroupMessage(long groupid) {
        chatmessage = new ChatMessage(groupid, 0);
    }

    /**
     * @param userid qq号 建一个用于发送私聊消息的ChatMessage
     */
    public void setPrivateMessag(long userid) {
        chatmessage = new ChatMessage(0, userid);
    }


    public Response sendGroupMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.Post("/send_group_msg", jsonString);
    }

    public Response sendPrivateMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.Post("/send_private_msg", jsonString);
    }

    public Response SendForwardMsg(String data) {
        return client.Post("/send_forward_msg", data);
    }

    public Response SendGrouppoke(Long groupid, Long userid) {
        var h = new HashMap<String, String>();
        h.put("group_id", String.valueOf(groupid));
        h.put("user_id", String.valueOf(userid));
        return client.Post("/group_poke", JSON.toJSONString(h));
    }

    public Response SendPrivatepoke(Long userid) {
        var h = new HashMap<String, String>();
        h.put("user_id", String.valueOf(userid));
        return client.Post("/friend_poke", JSON.toJSONString(h));
    }

    public Response DeleMsg(Long msgid) {
        var h = new HashMap<String, String>();
        h.put("message_id", String.valueOf(msgid));
        return client.Post("/delete_msg", JSON.toJSONString(h));
    }
    //剩余2个先不管了
}
