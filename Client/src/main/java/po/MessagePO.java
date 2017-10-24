package po;

import java.io.Serializable;

public class MessagePO implements Serializable
{
    private int ID;
    private String message;
    private String username;
    private String time;
    private String receiver;
    public MessagePO(int ID, String message, String username, String time, String receiver) {
        this.ID = ID;
        this.message = message;
        this.username = username;
        this.time = time;
        this.receiver=receiver;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    @Override
    public String toString() {
        return "MessagePO{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
