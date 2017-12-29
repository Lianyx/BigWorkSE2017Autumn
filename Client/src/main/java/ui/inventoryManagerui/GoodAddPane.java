package ui.inventoryManagerui;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class GoodAddPane extends AnchorPane {
    @FXML
    JFXTextField text1;


    @FXML
    JFXTextField text2;
    @FXML
    JFXTextField text3;
    @FXML
    JFXTextField text4;


    @FXML
    AnchorPane pane1;


    @FXML
    AnchorPane pane2;
    @FXML
    AnchorPane pane3;

    public GoodAddPane(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodAddPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        text1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane1.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane1.setStyle("-fx-background-color: white;");
                }
            }
        });
        text2.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane2.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane2.setStyle("-fx-background-color: white;");
                }
            }
        });
        text3.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane3.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane3.setStyle("-fx-background-color: white;");
                }
            }
        });
        text4.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane3.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane3.setStyle("-fx-background-color: white;");
                }
            }
        });


    }
}
