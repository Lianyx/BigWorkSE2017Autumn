package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;

public class LogPO{
    private int ID;
    private String username;
    private UserCategory userCategory;
    private EventCategory eventCategory;
    private String comment;
    private boolean isDelete=false;

    public LogPO(String username, UserCategory userCategory, EventCategory eventCategory, String comment) {
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
        this.comment = comment;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
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
