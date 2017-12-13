package ui.userui.usermanagerui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class Manager extends VBox {


    public Manager(){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/manager.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        }catch (Exception e){

        }

    }

}
