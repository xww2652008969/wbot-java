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

    public ChatMessage() {
        groupId = 0;
        userId = 0;
        message = new ArrayList<ChatMessageData>();
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

    // 嵌套类：ChatMessageData（对应 Go 中的 ChatMessageData）
    public static class ChatMessageData {
        @JSONField(name = "type")
        private String type;

        @JSONField(name = "data")
        private MessagePayload data; // 内部类 MessagePayload

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
        private String qq; // 可选字段（JSON 中可能不存在）

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
        private Integer subType; // Go 中是 int，Java 用 Integer 允许 null

        @JSONField(name = "file_Size")
        private String fileSize; // 注意：Java 字段名与 JSON 键名 "file_Size" 映射

        @JSONField(name = "type")
        private String type; // 与外层 ChatMessageData 的 type 字段同名，但通过 JSON 键区分

        @JSONField(name = "data")
        private String data; // 与外层 ChatMessageData 的 data 字段同名，但通过 JSON 键区分

        // 无参构造函数
        public MessagePayload() {
        }

        // getter 和 setter（示例）
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
}