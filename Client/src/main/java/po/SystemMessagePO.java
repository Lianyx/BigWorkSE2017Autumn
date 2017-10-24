package po;

import util.MessageType;
import util.UserCategory;

import java.io.Serializable;

public class SystemMessagePO implements Serializable{
    private int ID;
    private String username;
    private String time;
    private MessageType type;
    private String comment;
    private UserCategory receivers;
    public SystemMessagePO(int ID, String username, String time, MessageType type, String comment,UserCategory receivers) {
        this.ID=ID;
        this.username = username;
        this.time = time;
        this.type = type;
        this.comment = comment;
        this.receivers=receivers;
    }

    public UserCategory getReceivers() {
        return receivers;
    }

    public void setReceivers(UserCategory receivers) {
        this.receivers = receivers;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "SystemMessagePO{" +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", comment='" + comment + '\'' +
                ", receivers=" + receivers +
                '}';
    }
}
