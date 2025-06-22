package com.xww.model;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Message {
    @JSONField(name = "self_id")
    private long selfId;

    @JSONField(name = "user_id")
    private long userId;

    @JSONField(name = "time")
    private long time;

    @JSONField(name = "message_id")
    private long messageId;

    @JSONField(name = "message_seq")
    private int messageSeq;

    @JSONField(name = "real_id")
    private int realId;

    @JSONField(name = "message_type")
    private String messageType;

    @JSONField(name = "sender")
    private Sender sender;

    // 消息内容列表（假设 ChatMessageData 是已定义的类）
    @JSONField(name = "message")
    private List<ChatMessage.ChatMessageData> message;

    @JSONField(name = "raw_message")
    private String rawMessage;

    @JSONField(name = "font")
    private int font;

    @JSONField(name = "sub_type")
    private String subType;

    @JSONField(name = "message_format")
    private String messageFormat;

    @JSONField(name = "post_type")
    private String postType;

    @JSONField(name = "group_id")
    private long groupId;

    // 可选字段（omitempty，用包装类兼容 null）
    @JSONField(name = "notice_type")
    private String noticeType; // 可能为 null

    @JSONField(name = "target_id")
    private Long targetId;

    @JSONField(name = "sender_id")
    private Integer senderId;

    // 嵌套结构体 RawInfo（静态内部类，元素可选）
    @JSONField(name = "raw_info")
    private List<RawInfoItem> rawInfo;

    // 嵌套类 Sender（静态内部类）
    @Data
    public static class Sender {
        @JSONField(name = "user_id")
        private int userId;

        @JSONField(name = "nickname")
        private String nickname;

        @JSONField(name = "card")
        private String card;

        @JSONField(name = "role")
        private String role;

    }

    @Data
    // 嵌套类 RawInfoItem（静态内部类，对应 Go 中的匿名结构体）
    public static class RawInfoItem {
        @JSONField(name = "col")
        private String col;

        @JSONField(name = "nm")
        private String nm;

        @JSONField(name = "type")
        private String type;

        @JSONField(name = "uid")
        private String uid;

        @JSONField(name = "jp")
        private String jp;

        @JSONField(name = "src")
        private String src;

        @JSONField(name = "txt")
        private String txt;

        @JSONField(name = "tp")
        private String tp;
    }
}