package com.xww.model;

import com.alibaba.fastjson2.annotation.JSONField;
import com.xww.constants.MessageType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatMessage {
    // 主类字段
    @JSONField(name = "group_id")
    private long groupId;

    @JSONField(name = "user_id")
    private long userId;

    @JSONField(name = "message")
    private List<ChatMessageData> message;

    public ChatMessage(long groupId, long userid) {
        this.groupId = groupId;
        userId = userid;
        message = new ArrayList<>();
    }

    @Data
    @AllArgsConstructor
    public static class ChatMessageData {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "data")
        private MessagePayload data;
    }

    @Data
    public static class MessagePayload {
        @JSONField(name = "qq")
        private String qq;

        @JSONField(name = "name")
        private String name;

        @JSONField(name = "text")
        private String text;

        @JSONField(name = "file")
        private String file;

        @JSONField(name = "id")
        private String id;

        @JSONField(name = "url")
        private String url;

        @JSONField(name = "sub_Type")
        private Integer subType;

        @JSONField(name = "file_Size")
        private String fileSize;

        @JSONField(name = "type")
        private String type;

        @JSONField(name = "data")
        private String data;
    }


    private ChatMessage addMessage(String msgType, MessagePayload data) {
        message.add(new ChatMessage.ChatMessageData(msgType, data));
        return this;
    }

    /**
     * @param data 图片url 或者本地目录
     * @return 返回对象
     */
    public ChatMessage addImage(String data) {
        var d = new MessagePayload();
        d.setFile(data);
        return addMessage(MessageType.MSG_TYPE_IMAGE, d);
    }

    public ChatMessage addFace(int id) {
        MessagePayload payload = new MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MSG_TYPE_FACE, payload);
    }

    public ChatMessage addRecord(String url) {
        MessagePayload payload = new MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MSG_TYPE_RECORD, payload);
    }

    public ChatMessage addVideo(String url) {
        MessagePayload payload = new MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MSG_TYPE_VIDEO, payload);
    }

    public ChatMessage addReply(long id) {
        MessagePayload payload = new MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MSG_TYPE_REPLY, payload);
    }

    public ChatMessage addMusicCard(String t, int id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setType(t);
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MSG_TYPE_MUSIC, payload);
    }

//    public ChatMessage AddDice() {
//        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
//        payload.setType(MsgTypeDice);  // 直接使用枚举名（与 Go 的 MsgTypeDice 对应）
//        return addMessage(MsgTypeDice, payload);
//    }

    public ChatMessage addFile(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MSG_TYPE_FILE, payload);
    }

    public ChatMessage addText(String text) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setText(text);
        return addMessage(MessageType.MSG_TYPE_TEXT, payload);
    }

    public ChatMessage addAt(long qq) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setQq(qq == 0 ? "all" : String.valueOf(qq));
        return addMessage(MessageType.MSG_TYPE_AT, payload).addText(" ");
    }
}