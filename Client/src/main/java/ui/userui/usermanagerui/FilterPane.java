package ui.userui.usermanagerui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FilterPane extends AnchorPane{


    public FilterPane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/filter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
