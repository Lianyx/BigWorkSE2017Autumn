package ui.userui.usermanagerui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javax.annotation.PostConstruct;
import javax.jws.soap.SOAPBinding;


public class UserSearchBox extends AnchorPane{

    public UserSearchBox(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/searchbox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();


        }catch (Exception e){

        }

    }
}
