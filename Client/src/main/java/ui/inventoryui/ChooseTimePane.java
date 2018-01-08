package ui.inventoryui;

import blService.inventoryblService.InventoryShowblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.inventoryui.inventoryViewui.InventoryViewListPane;
import ui.util.BoardController;

import java.io.IOException;

public class ChooseTimePane extends AnchorPane {
    @FXML
    JFXRippler close;
    @FXML
    DatePicker startTimeField;
    @FXML
    DatePicker endTimeField;
    @FXML
    JFXButton sure;

    BoardController boardController;

    StackPane mainPane;

    InventoryShowblService showblService;

    JFXDialog dialog;

    public ChooseTimePane(InventoryShowblService showblService,BoardController boardController,StackPane mainPane){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/ChooseTimePane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.boardController = boardController;
        this.showblService = showblService;
        this.mainPane = mainPane;

    }

    @FXML
    public void sureTime(){
        try {
            dialog.close();
            boardController.switchTo(new InventoryViewListPane());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
