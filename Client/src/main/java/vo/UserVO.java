package vo;

import javafx.scene.image.Image;
import po.UserPO;
import util.ImageConvertor;
import util.UserCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static util.ImageConvertor.getFXImage;

public class UserVO {
    private int id;
    private Image image;
    private String username;
    private UserCategory usertype;
    private LocalDateTime createTime;
    private String facebook;
    private String github;
    private String twitter;
    private String email;
    private String phone;
    private String comment;
    private String date;
    private String password;
    private boolean isDelete = false;


    public UserVO(){

    }

    public UserVO(UserPO po){
        this.id=po.getUserId();
        this.image = ImageConvertor.getFXImage(ImageConvertor.getImage(po.getImage()));
        this.username = po.getUsername();
        this.usertype = po.getUsertype();
        this.facebook = po.getFacebook();
        this.github = po.getGithub();
        this.twitter = po.getTwitter();
        this.email = po.getEmail();
        this.phone = po.getPhone();
        this.comment = po.getComment();
        this.date = po.getDate();
        this.password = po.getPassword();

    }

    public UserVO(int id, String username, UserCategory usertype, LocalDateTime createTime, String facebook, String github, String twitter, String email, String phone, String comment, String date, String password, boolean isDelete) {
        this.id = id;
        this.username = username;
        this.usertype = usertype;
        this.createTime = createTime;
        this.facebook = facebook;
        this.github = github;
        this.twitter = twitter;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.date = date;
        this.password = password;
        this.isDelete = isDelete;
    }


    public UserVO(int id, Image image, String username, UserCategory usertype, LocalDateTime createTime, String facebook, String github, String twitter, String email, String phone, String comment, String date, String password) {
        this.id = id;
        this.image = image;
        this.username = username;
        this.usertype = usertype;
        this.createTime = createTime;
        this.facebook = facebook;
        this.github = github;
        this.twitter = twitter;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
        this.date = date;
        this.password = password;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
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

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public UserPO toPO(){
        UserPO userPO = new UserPO();
        userPO.setComment(comment);
        userPO.setCreateTime(createTime);
        userPO.setDate(date);
        userPO.setEmail(email);
        userPO.setFacebook(facebook);
        userPO.setGithub(github);
        userPO.setImage(ImageConvertor.getByte(image));
        userPO.setTwitter(twitter);
        userPO.setPassword(password);
        userPO.setPhone(phone);
        userPO.setUserId(id);
        userPO.setUsername(username);
        userPO.setUsertype(usertype);
        userPO.setIsDeleted(isDelete?1:0);
        return userPO;
    }

    public UserListVO toListVO(){
        UserListVO userListVO = new UserListVO();
        userListVO.setUserid(id);
        userListVO.setImage(image);
        userListVO.setEmail(email);
        userListVO.setPhone(phone);
        userListVO.setUserCategory(usertype);
        userListVO.setUsername(username);
        return userListVO;
    }



}
