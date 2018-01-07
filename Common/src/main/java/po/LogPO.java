package po;

import util.EventCategory;
import util.UserCategory;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogPO implements Serializable{
    private int logId;
    private LocalDateTime createTime;
    private String username;
    private UserCategory userCategory;
    private EventCategory eventCategory;
    private String comment;

    public LogPO() {
    }

    public LogPO(int logId, LocalDateTime createTime, String username, UserCategory userCategory, EventCategory eventCategory, String comment) {
        this.logId = logId;
        this.createTime = createTime;
        this.username = username;
        this.userCategory = userCategory;
        this.eventCategory = eventCategory;
        this.comment = comment;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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
