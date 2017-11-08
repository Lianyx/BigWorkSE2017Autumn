package po;


import util.UserCategory;

import java.io.Serializable;

public class UserPO implements Serializable{

    private int ID;
    private String name;
    private String password;
    private String answer1,answer2,answer3;
    private UserCategory userCategory;

    public UserPO(String name, String password, String answer1, String answer2, String answer3, UserCategory userCategory) {
        this.name = name;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.userCategory = userCategory;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
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

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer) {
        this.answer1 = answer;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

}
