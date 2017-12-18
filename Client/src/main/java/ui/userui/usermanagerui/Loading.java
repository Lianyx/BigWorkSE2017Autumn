package ui.userui.usermanagerui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class Loading extends VBox {

    public Loading(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/loading.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
