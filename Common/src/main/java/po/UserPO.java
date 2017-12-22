package po;


import util.UserCategory;

import java.io.Serializable;

public class UserPO implements Serializable{

    private int ID;
    private String username;
    private String password;
    private String answer1;
    String answer2;
    String answer3;
    private UserCategory userCategory;
    private String phone;
    private String email;
    private boolean isDelete = false;

    public UserPO(int ID, String username, String password, String answer1, String answer2, String answer3, UserCategory userCategory, String phone, String email) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.userCategory = userCategory;
        this.phone = phone;
        this.email = email;
    }

    public UserPO(){}


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUserame(String username) {
        this.username = username;
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
