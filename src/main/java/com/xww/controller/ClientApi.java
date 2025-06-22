package com.xww.controller;

import com.alibaba.fastjson2.JSON;
import com.xww.client.Httpclient;
import com.xww.model.ChatMessage;
import com.xww.model.HttpResult;

import java.util.HashMap;

public class ClientApi {
    public ChatMessage chatMessage;
    private final Httpclient client;

    public ClientApi(Httpclient c) {
        client = c;
    }

    /**
     * 创建一个用于发送群聊消息的 ChatMessage 对象。
     *
     * @param groupId 群号
     */
    public void createGroupMessage(long groupId) {
        chatMessage = new ChatMessage(groupId, 0);
    }

    /**
     * 创建一个用于发送私聊消息的 ChatMessage 对象。
     *
     * @param userId 用户 QQ 号
     */
    public void createPrivateMessage(long userId) {
        chatMessage = new ChatMessage(0, userId);
    }

    /**
     * 发送群消息
     *
     * @return 返回一个HttpResult
     */
    public HttpResult sendGroupMsg() {
        String jsonString = JSON.toJSONString(chatMessage);
        return client.post("/send_group_msg", jsonString);
    }

    /**
     * 发送私聊
     */
    public HttpResult sendPrivateMsg() {
        String jsonString = JSON.toJSONString(chatMessage);
        return client.post("/send_private_msg", jsonString);
    }

    //这个要构建对象先不干嘻嘻
    public HttpResult sendForwardMsg(String data) {
        return client.post("/send_forward_msg", data);
    }

    public HttpResult sendGroupPoke(Long groupId, Long userId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("group_id", String.valueOf(groupId));
        h.put("user_id", String.valueOf(userId));
        return client.post("/group_poke", JSON.toJSONString(h));
    }

    /**
     * 发送私聊戳戳
     *
     * @param userId qq号
     */
    public HttpResult sendPrivatePoke(Long userId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("user_id", String.valueOf(userId));
        return client.post("/friend_poke", JSON.toJSONString(h));
    }

    /**
     * 撤回消息
     *
     * @param msgId
     * @return
     */
    public HttpResult deleteMsg(Long msgId) {
        HashMap<String, String> h = new HashMap<>();
        h.put("message_id", String.valueOf(msgId));
        return client.post("/delete_msg", JSON.toJSONString(h));
    }

    /**
     * 获取群的成员列表
     *
     * @param groupId
     * @return
     */
    public HttpResult getGroupMemberList(Long groupId) {
        HashMap<String, String> da = new HashMap<>();
        da.put("group_id", String.valueOf(groupId));
        return client.post("/get_group_member_list", JSON.toJSONString(da));
    }

    /**
     * 获取群成员信息
     *
     * @param groupid
     * @param userId
     * @return
     */
    public HttpResult getGroupMemberInfo(Long groupid, Long userId) {
        HashMap<String, String> da = new HashMap<>();
        da.put("group_id", String.valueOf(groupid));
        da.put("user_id", String.valueOf(userId));
        return client.post("/get_group_member_info", JSON.toJSONString(da));
    }
}
