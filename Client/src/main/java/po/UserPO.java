package po;


import util.UserCategory;

import java.io.Serializable;

public class UserPO implements Serializable{

    private int ID;
    private String name;
    private String password;
    private String answer;
    private UserCategory userCategory;

    public UserPO(int ID, String name, String password, String answer, UserCategory userCategory) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.answer = answer;
        this.userCategory = userCategory;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", answer='" + answer + '\'' +
                ", userCategory=" + userCategory +
                '}';
    }
}
