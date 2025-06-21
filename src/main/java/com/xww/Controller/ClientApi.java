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
     * @param groupId 群号 创建一个用于发送群聊消息的ChatMessage
     */
    public void setGroupMessage(long groupId) {
        chatmessage = new ChatMessage(groupId, 0);
    }

    /**
     * @param userid qq号 建一个用于发送私聊消息的ChatMessage
     */
    public void setPrivateMessage(long userid) {
        chatmessage = new ChatMessage(0, userid);
    }


    public Response sendGroupMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.post("/send_group_msg", jsonString);
    }

    public Response sendPrivateMsg() {
        String jsonString = JSON.toJSONString(chatmessage);
        return client.post("/send_private_msg", jsonString);
    }

    public Response sendForwardMsg(String data) {
        return client.post("/send_forward_msg", data);
    }

    public Response sendGroupPoke(Long groupId, Long userid) {
        var h = new HashMap<String, String>();
        h.put("group_id", String.valueOf(groupId));
        h.put("user_id", String.valueOf(userid));
        return client.post("/group_poke", JSON.toJSONString(h));
    }

    public Response sendPrivatePoke(Long userid) {
        var h = new HashMap<String, String>();
        h.put("user_id", String.valueOf(userid));
        return client.post("/friend_poke", JSON.toJSONString(h));
    }

    public Response deleteMsg(Long msgid) {
        var h = new HashMap<String, String>();
        h.put("message_id", String.valueOf(msgid));
        return client.post("/delete_msg", JSON.toJSONString(h));
    }
    //剩余2个先不管了
}
