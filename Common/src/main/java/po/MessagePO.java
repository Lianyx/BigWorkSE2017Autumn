package po;

import sun.plugin2.message.Message;
import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessagePO {
    private int messageID;
    private LocalDateTime createTime;
    private EventCategory eventCategory;
    private UserCategory from;
    private UserCategory to;
    private String userNameFrom;
    private String userNameto;
    private String comment;
    private boolean hasRead = false;


    public MessagePO() {
    }

    public MessagePO(LocalDateTime createTime, EventCategory eventCategory, UserCategory from, UserCategory to, String userNameFrom, String userNameto, String comment) {
        this.createTime = createTime;
        this.eventCategory = eventCategory;
        this.from = from;
        this.to = to;
        this.userNameFrom = userNameFrom;
        this.userNameto = userNameto;
        this.comment = comment;
    }

    public MessagePO(int messageID, LocalDateTime createTime, EventCategory eventCategory, UserCategory from, UserCategory to, String userNameFrom, String userNameto, String comment) {
        this.messageID = messageID;
        this.createTime = createTime;
        this.eventCategory = eventCategory;
        this.from = from;
        this.to = to;
        this.userNameFrom = userNameFrom;
        this.userNameto = userNameto;
        this.comment = comment;
    }


    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public UserCategory getFrom() {
        return from;
    }

    public void setFrom(UserCategory from) {
        this.from = from;
    }

    public UserCategory getTo() {
        return to;
    }

    public void setTo(UserCategory to) {
        this.to = to;
    }

    public String getUserNameFrom() {
        return userNameFrom;
    }

    public void setUserNameFrom(String userNameFrom) {
        this.userNameFrom = userNameFrom;
    }

    public String getUserNameto() {
        return userNameto;
    }

    public void setUserNameto(String userNameto) {
        this.userNameto = userNameto;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }
}