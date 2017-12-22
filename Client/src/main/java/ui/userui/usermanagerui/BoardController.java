package ui.userui.usermanagerui;


import blService.userblService.UserManagerblService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import ui.util.ContainerAnimations;
import ui.util.HistoricalRecord;
import ui.util.PaneSwitchAnimation;


public class BoardController{

    @FXML
    StackPane board;

    UserManagerUIController userManagerUIController;

    UserManagerblService userManagerblService;

    PaneSwitchAnimation paneSwitchAnimation;

    public void setUserManagerUIController(UserManagerUIController userManagerUIController){
        this.userManagerblService=userManagerblService;
    }

    public void setUserManagerblService(UserManagerblService userManagerblService) {
        this.userManagerblService = userManagerblService;
    }




    public void setPaneSwitchAnimation(PaneSwitchAnimation paneSwitchAnimation) {
        this.paneSwitchAnimation = paneSwitchAnimation;
    }

    public void switchTo(AnchorPane pane){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                 if(HistoricalRecord.addRecord(pane)){
                        setLeftAnimation();
                        paneSwitchAnimation.setNode(pane);
                    }
                }catch (Exception e){

                }
            }
        });
    }

    public void Loading(){
         board.getChildren().setAll(new Loading());
    }


    public void setLeftAnimation(){ paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);  }

    public void setRightAnimation(){ paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_RIGHT);}

    public void historicalSwitchTo(AnchorPane pane){
        paneSwitchAnimation.setNode(pane);
    }



    public String getId(){
        return board.getChildren().get(0).getId();
    }







}
