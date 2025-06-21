package com.xww.model;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage {
    // 主类字段
    @JSONField(name = "group_id")
    private long groupId;

    @JSONField(name = "user_id")
    private long userId;

    @JSONField(name = "message")
    private List<ChatMessageData> message;

    public ChatMessage(long groupid, long userid) {
        groupId = groupid;
        userId = groupid;
        message = new ArrayList<>();
    }

    // getter 和 setter
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<ChatMessageData> getMessage() {
        return message;
    }

    public void setMessage(List<ChatMessageData> message) {
        this.message = message;
    }

    public static class ChatMessageData {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "data")
        private MessagePayload data;

        // 无参构造函数
        public ChatMessageData(String type, MessagePayload data) {
            this.type = type;
            this.data = data;
        }

        // getter 和 setter
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public MessagePayload getData() {
            return data;
        }

        public void setData(MessagePayload data) {
            this.data = data;
        }
    }

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

        // 无参构造函数
        public MessagePayload() {
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Integer getSubType() {
            return subType;
        }

        public void setSubType(Integer subType) {
            this.subType = subType;
        }

        public String getFileSize() {
            return fileSize;
        }

        public void setFileSize(String fileSize) {
            this.fileSize = fileSize;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
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
        var d = new ChatMessage.MessagePayload();
        d.setFile(data);
        return addMessage(MessageType.MsgTypeImage, d);
    }

    public ChatMessage addFace(int id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MsgTypeFace, payload);
    }

    public ChatMessage addRecord(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeRecord, payload);
    }

    public ChatMessage addVideo(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeVideo, payload);
    }

    public ChatMessage addReply(long id) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setId(String.valueOf(id));
        return addMessage(MessageType.MsgTypeReply, payload);
    }

    public ChatMessage addMusicCard(String t, int id) {
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

    public ChatMessage addFile(String url) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setFile(url);
        return addMessage(MessageType.MsgTypeFile, payload);
    }

    public ChatMessage addText(String text) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setText(text);
        return addMessage(MessageType.MsgTypeText, payload);
    }

    public ChatMessage addAt(long qq) {
        ChatMessage.MessagePayload payload = new ChatMessage.MessagePayload();
        payload.setQq(qq == 0 ? "all" : String.valueOf(qq));
        return addMessage(MessageType.MsgTypeAt, payload).addText(" ");
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
}