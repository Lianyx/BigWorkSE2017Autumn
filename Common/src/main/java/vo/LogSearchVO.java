package vo;

import util.EventCategory;
import util.UserCategory;

import java.awt.*;
import java.util.Date;

public class LogSearchVO {
    long timeFrom;
    long timeTo;
    String username;
    UserCategory userCategory;
    EventCategory eventCategory;

    public LogSearchVO(long timeFrom, long timeTo, String username, UserCategory userCategory, EventCategory eventCategory) {
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
    }

    public long getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(long timeFrom) {
        this.timeFrom = timeFrom;
    }

    public long getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(long timeTo) {
        this.timeTo = timeTo;
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
}
