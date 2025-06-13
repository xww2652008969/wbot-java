package com.xww.core;

import com.xww.model.ChatMessage;
import com.xww.model.Message;

import static com.xww.core.MessageType.MsgTypeDice;

public class MessagesApi {
    private ChatMessage message;


    public ChatMessage getMessage() {
        return message;
    }

    public MessagesApi() {
        message = new ChatMessage();
    }

    public MessagesApi(Message m) {
        message = new ChatMessage();

        if (m.getUserId() != 0) {
            message.setUserId(m.getUserId());
        }
        if (m.getGroupId() != 0) {
            message.setGroupId(m.getGroupId());
        }

    }


    private MessagesApi addMessage(String msgType, ChatMessage.MessagePayload data) {
        message.getMessage().add(new ChatMessage.ChatMessageData(msgType, data));
        return this;
    }

    /**
     * @param data 图片url 或者本地目录
     * @return 返回对象
     */
    public MessagesApi AddImage(String data) {
        var d = new ChatMessage.MessagePayload();
        d.setFile(data);
        return addMessage(MessageType.MsgTypeImage, d);
    }

    public MessagesApi AddFace(int id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MsgTypeFace, payload);
    }

    public MessagesApi AddRecord(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeRecord, payload);
    }

    public MessagesApi AddVideo(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeVideo, payload);
    }

    public MessagesApi Addreply(long id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MsgTypeReply, payload);
    }

    public MessagesApi AddMusicCard(String t, int id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setType(t);
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MsgTypeMusic, payload);
    }

//    public ChatMessage AddDice() {
//        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
//        payload.setType(MsgTypeDice);  // 直接使用枚举名（与 Go 的 MsgTypeDice 对应）
//        return addMessage(MsgTypeDice, payload);
//    }

    public MessagesApi AddFile(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeFile, payload);
    }

    public MessagesApi AddText(String text) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setText(text);
        return addMessage(MessageType.MsgTypeText, payload);
    }

    public MessagesApi AddAt(long qq) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setQq(qq == 0 ? "all" : String.valueOf(qq));
        return addMessage(MessageType.MsgTypeAt, payload);
    }
}

class MessageType {
    public static final String MsgTypeText = "text";
    public static final String MsgTypeAt = "at";
    public static final String MsgTypeImage = "image";
    public static final String MsgTypeFace = "face";
    public static final String MsgTypeRecord = "record";
    public static final String MsgTypeVideo = "video";
    public static final String MsgTypeReply = "reply";
    public static final String MsgTypeMusic = "music";
    public static final String MsgTypeDice = "dice";
    public static final String MsgTypeFile = "file";
}