package ui.util;

import com.jfoenix.controls.JFXListView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.swing.event.ChangeListener;

public class ListPopup extends JFXListView {


    @FXML
    HBox listview;
    @FXML
    HBox listdelete;


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

    public HBox getListview() {
        return listview;
    }

    public HBox getListdelete() {
        return listdelete;
    }
}
