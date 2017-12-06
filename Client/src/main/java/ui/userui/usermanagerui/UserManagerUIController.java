package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.util.ContainerAnimations;
import ui.util.PaneSwitchAnimation;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class UserManagerUIController implements Initializable{


    @FXML
    JFXRippler closebutton;

    @FXML
    JFXRippler hidebutton;

    @FXML
    JFXBadge message;



    @FXML
    JFXRippler back;
    @FXML
    JFXRippler forward;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    HBox userlist;

    @FXML
    HBox userdetail;


    @FXML
    StackPane board;

    @FXML
    private BoardController boardController;



    UserManagerblService usermanagerblservice_stub;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        usermanagerblservice_stub=new Usermanagerblservice_Stub();

        back.disableProperty().bind(HistoricalRecord.canBack.not());
        forward.disableProperty().bind(HistoricalRecord.canForward.not());




        //set default pane
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(320), ContainerAnimations.SWIPE_RIGHT,board));
            UserListPane userListPane=new UserListPane(usermanagerblservice_stub);
            board.getChildren().setAll(userListPane);
            HistoricalRecord.addPane(userListPane);
            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                new Thread(()->{
                    Platform.runLater(()->{
                        try{
                        if (newVal != null) {
                            if(newVal.getId().equals("usermodify")){
                               boardController.switchTo(new UserModifyPane());
                           }else if(newVal.getId().equals("userlist")){
                               boardController.switchTo(new UserListPane(usermanagerblservice_stub));
                           }


                        }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                }).start();
            });





        }catch (Exception e){
            e.printStackTrace();
        }



/*
        message.setOnMouseClicked((click) -> {
            int value = Integer.parseInt(message.getText());
            if (click.getButton() == MouseButton.PRIMARY) {
                value++;
            } else if (click.getButton() == MouseButton.SECONDARY) {
                value--;
            }

            if (value == 0) {
                message.setEnabled(false);
            } else {
                message.setEnabled(true);
            }
            message.setText(String.valueOf(value));
        });
        */


    }


    @FXML
    public void close(){
        Stage stage = (Stage) closebutton.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void hide() throws Exception{
        Stage stage=(Stage)hidebutton.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    public void goback(){
        boardController.historicalSwitchTo((AnchorPane) HistoricalRecord.pop());
    }

    @FXML
    public void goforward(){
        boardController.historicalSwitchTo((AnchorPane)HistoricalRecord.push());
    }

}
