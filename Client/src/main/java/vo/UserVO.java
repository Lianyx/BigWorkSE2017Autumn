package vo;

import javafx.scene.image.Image;
import util.UserCategory;

public class UserVO {
    private int id;
    private Image image;
    private String username;
    private UserCategory usertype;
    private String facebook;
    private String github;
    private String twitter;
    private String email;
    private String phone;
    private String comment;
    private String date;
    private String password;

    public UserVO(int id,Image image, String username, UserCategory usertype, String facebook, String github, String twitter, String email, String phone, String comment, String date, String password) {
        this.id=id;
        this.image = image;
        this.username = username;
        this.usertype = usertype;
        this.facebook = facebook;
        this.github = github;
        this.twitter = twitter;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.date = date;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCategory getUsertype() {
        return usertype;
    }

    public void setUsertype(UserCategory usertype) {
        this.usertype = usertype;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
