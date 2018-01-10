package ui.accountantui;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.managerui.common.MyBoardController;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.Refreshable;

import java.rmi.RemoteException;
import java.util.function.Predicate;


public class InitialListPane extends Refreshable {

    @FXML
    BorderPane borderPane;

    MyBoardController myBoardController;

    StackPane mainpane;

    @FXML
    JFXButton accountinitial;

    public InitialListPane()throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/InitialListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.myBoardController = MyBoardController.getMyBoardController();
        this.mainpane = PaneFactory.getMainPane();
    }

    public void refresh(boolean toSwitch){
        myBoardController.Loading();
        myBoardController.switchTo(this);
    }



}
