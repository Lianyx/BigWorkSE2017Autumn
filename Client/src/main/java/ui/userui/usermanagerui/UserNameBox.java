package ui.userui.usermanagerui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import ui.util.CircleImageView;
import vo.UserListVO;
import javafx.scene.control.*;
public class UserNameBox extends HBox {


    @FXML
    CircleImageView civ;

    @FXML
    Label username;


    public UserNameBox(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/usernamebox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();

        }catch (Exception e){

        }
    }
    public void setInfo(Image image, String text){
        this.civ.setImage(image);
        this.username.setText(text);
    }


    public void setText(String text){
     this.username.setText(text);
    }

}
