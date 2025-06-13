package com.xww.model;

import com.alibaba.fastjson2.annotation.JSONField;
import java.util.List;

public class Message {
    // 基础字段（基本类型或包装类，可选字段用包装类）
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

    // 嵌套结构体 Sender（静态内部类）
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
    private Long targetId; // Go 中是 int64，Java 用 Long 允许 null

    @JSONField(name = "sender_id")
    private Integer senderId; // Go 中是 int，Java 用 Integer 允许 null

    // 嵌套结构体 RawInfo（静态内部类，元素可选）
    @JSONField(name = "raw_info")
    private List<RawInfoItem> rawInfo; // 可能为 null 或空列表

    // 无参构造函数（Fastjson 2 反序列化需要）
    public Message() {}

    // getter 和 setter（必须！Fastjson 通过反射访问字段）
    public long getSelfId() { return selfId; }
    public void setSelfId(long selfId) { this.selfId = selfId; }

    public long getUserId() { return userId; }
    public void setUserId(long userId) { this.userId = userId; }

    public long getTime() { return time; }
    public void setTime(long time) { this.time = time; }

    public long getMessageId() { return messageId; }
    public void setMessageId(long messageId) { this.messageId = messageId; }

    public int getMessageSeq() { return messageSeq; }
    public void setMessageSeq(int messageSeq) { this.messageSeq = messageSeq; }

    public int getRealId() { return realId; }
    public void setRealId(int realId) { this.realId = realId; }

    public String getMessageType() { return messageType; }
    public void setMessageType(String messageType) { this.messageType = messageType; }

    public Sender getSender() { return sender; }
    public void setSender(Sender sender) { this.sender = sender; }

    public List<ChatMessage.ChatMessageData> getMessage() { return message; }
    public void setMessage(List<ChatMessage.ChatMessageData> message) { this.message = message; }

    public String getRawMessage() { return rawMessage; }
    public void setRawMessage(String rawMessage) { this.rawMessage = rawMessage; }

    public int getFont() { return font; }
    public void setFont(int font) { this.font = font; }

    public String getSubType() { return subType; }
    public void setSubType(String subType) { this.subType = subType; }

    public String getMessageFormat() { return messageFormat; }
    public void setMessageFormat(String messageFormat) { this.messageFormat = messageFormat; }

    public String getPostType() { return postType; }
    public void setPostType(String postType) { this.postType = postType; }

    public long getGroupId() { return groupId; }
    public void setGroupId(long groupId) { this.groupId = groupId; }

    public String getNoticeType() { return noticeType; }
    public void setNoticeType(String noticeType) { this.noticeType = noticeType; }

    public Long getTargetId() { return targetId; }
    public void setTargetId(Long targetId) { this.targetId = targetId; }

    public Integer getSenderId() { return senderId; }
    public void setSenderId(Integer senderId) { this.senderId = senderId; }

    public List<RawInfoItem> getRawInfo() { return rawInfo; }
    public void setRawInfo(List<RawInfoItem> rawInfo) { this.rawInfo = rawInfo; }

    // 嵌套类 Sender（静态内部类）
    public static class Sender {
        @JSONField(name = "user_id")
        private int userId;

        @JSONField(name = "nickname")
        private String nickname;

        @JSONField(name = "card")
        private String card;

        @JSONField(name = "role")
        private String role;

        // 无参构造函数
        public Sender() {}

        // getter 和 setter
        public int getUserId() { return userId; }
        public void setUserId(int userId) { this.userId = userId; }

        public String getNickname() { return nickname; }
        public void setNickname(String nickname) { this.nickname = nickname; }

        public String getCard() { return card; }
        public void setCard(String card) { this.card = card; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

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

        // 无参构造函数
        public RawInfoItem() {}

        // getter 和 setter（示例）
        public String getCol() { return col; }
        public void setCol(String col) { this.col = col; }

        public String getNm() { return nm; }
        public void setNm(String nm) { this.nm = nm; }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getUid() { return uid; }
        public void setUid(String uid) { this.uid = uid; }

        public String getJp() { return jp; }
        public void setJp(String jp) { this.jp = jp; }

        public String getSrc() { return src; }
        public void setSrc(String src) { this.src = src; }

        public String getTxt() { return txt; }
        public void setTxt(String txt) { this.txt = txt; }

        public String getTp() { return tp; }
        public void setTp(String tp) { this.tp = tp; }
    }
}