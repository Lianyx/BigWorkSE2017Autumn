package ui.userui.usermanagerui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXMLLoader;

public class ListPopup extends JFXListView {
    public ListPopup(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/PopupList.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
