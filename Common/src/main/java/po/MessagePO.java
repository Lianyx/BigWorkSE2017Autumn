package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessagePO {
    private int ID;
    private LocalDateTime createTime;
    private EventCategory eventCategory;
    private UserCategory userCategory;
    private String userName;
    private String comment;

    public MessagePO(int ID, LocalDateTime createTime, EventCategory eventCategory, UserCategory userCategory, String userName, String comment) {
        this.ID = ID;
        this.createTime = createTime;
        this.eventCategory = eventCategory;
        this.userCategory = userCategory;
        this.userName = userName;
        this.comment = comment;
    }

    public MessagePO(){}

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}