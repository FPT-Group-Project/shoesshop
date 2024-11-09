package Models;

import java.util.Date;

public class Message {
    private int messageId;
    private int conversationId;
    private int senderId;
    private String content;
    private Date sendTime;
    private int senderRole;

    public Message() {
    }

    public Message(int messageId, int conversationId, int senderId, String content, Date sendTime, int senderRole) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.content = content;
        this.sendTime = sendTime;
        this.senderRole = senderRole;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getSenderRole() {
        return senderRole;
    }

    public void setSenderRole(int senderRole) {
        this.senderRole = senderRole;
    }

   
}
