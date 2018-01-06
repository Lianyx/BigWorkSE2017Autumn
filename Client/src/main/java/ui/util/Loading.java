package ui.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class Loading extends AnchorPane {

    public Loading(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/util/loading.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
