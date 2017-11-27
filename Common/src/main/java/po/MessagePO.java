package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;

public class MessagePO {
    private int ID;
    private long time;
    private boolean isDelete=false;
    private EventCategory eventCategory;
    private UserCategory userCategory;
    private String userName;
    private String comment;

    public MessagePO(long time, EventCategory eventCategory, UserCategory userCategory, String userName, String comment) {
        this.time = time;
        this.eventCategory = eventCategory;
        this.userCategory = userCategory;
        this.userName = userName;
        this.comment = comment;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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