package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;

public class LogPO implements Serializable {
    private int ID;
    private String username;
    private UserCategory userCategory;
    private EventCategory eventCategory;
    private String time;

    public LogPO(int ID, String username, UserCategory userCategory, EventCategory eventCategory, String time) {
        this.ID = ID;
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LogPO{" +
                ", username='" + username + '\'' +
                ", userCategory=" + userCategory +
                ", eventCategory=" + eventCategory +
                ", time='" + time + '\'' +
                '}';
    }
}
