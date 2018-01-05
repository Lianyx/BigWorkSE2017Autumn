package vo;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import sun.java2d.pipe.SpanShapeRenderer;
import util.UserCategory;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;
import java.util.Comparator;

public class UserListVO extends SelectableVO<UserListVO> {

    private int userid;
    private Image image;
    private String username;
    private UserCategory userCategory;
    private String email;
    private String phone;
    private SimpleBooleanProperty selected=new SimpleBooleanProperty(false);
    private boolean multiple = true;


    public UserListVO(int userid, Image image, String username, UserCategory userCategory, String email, String phone, boolean selected) {
        this.userid = userid;
        this.image = image;
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
        this.selected.setValue(false);
    }

    public UserListVO(int userid, Image image, String username, UserCategory userCategory, String email, String phone) {
        this.userid = userid;
        this.image = image;
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
    }
    public UserListVO(int userid, String username, UserCategory userCategory, String email, String phone) {
        this.userid = userid;
        this.image = new Image("/default/timg.jpg");
        this.username = username;
        this.userCategory = userCategory;
        this.email = email;
        this.phone = phone;
    }

    public UserListVO( String username, UserCategory userCategory, String email, String phone) {
        this.userid = 0;
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

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
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

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    @Override
    public String toString() {
        return "UserListVO{" +
                "userid=" + userid +
                ", selected=" + selected +
                '}';
    }
}
