package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogPO{
    private int ID;
    private LocalDateTime createTime;
    private String username;
    private UserCategory userCategory;
    private EventCategory eventCategory;
    private String comment;

    public LogPO(String username, UserCategory userCategory, EventCategory eventCategory, String comment) {
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
        this.comment = comment;
    }


    public LogPO(int ID, LocalDateTime createTime, String username, UserCategory userCategory, EventCategory eventCategory, String comment) {
        this.ID = ID;
        this.createTime = createTime;
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
        this.comment = comment;
    }

    public LogPO(){}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
