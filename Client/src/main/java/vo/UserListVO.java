package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import sun.java2d.pipe.SpanShapeRenderer;
import util.UserCategory;

import java.io.Serializable;
import java.util.Comparator;

public class UserListVO extends RecursiveTreeObject<UserListVO> implements Serializable,Comparable<UserListVO> {

    private long userid;
    private Image image;
    private String username;
    private UserCategory userCategory;
    private String email;
    private String phone;
    private SimpleBooleanProperty selected=new SimpleBooleanProperty(false);

    public UserListVO(long userid, Image image, String username, UserCategory userCategory, String email, String phone, boolean selected) {
        this.userid = userid;
        this.image = image;
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
        this.selected.setValue(false);
    }

    public UserListVO(long userid, Image image, String username, UserCategory userCategory, String email, String phone) {
        this.userid = userid;
        this.image = image;
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
    }
    public UserListVO(long userid, String username, UserCategory userCategory, String email, String phone) {
        this.userid = userid;
        this.image = new Image("/default/timg.jpg");
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
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


    @Override
    public int compareTo(UserListVO o) {
        if(userid>o.getUserid())
            return 1;
        else if(userid<o.getUserid())
            return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "UserListVO{" +
                "userid=" + userid +
                ", selected=" + selected +
                '}';
    }
}