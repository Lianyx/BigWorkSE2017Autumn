package ui.inventoryui;

import blService.goodsblService.GoodsblService;
import blServiceStub.goodsblservice_Stub.GoodsblService_Stub;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;
import ui.inventoryui.goodsui.GoodsListPane;
import ui.inventoryui.inventoryReceiptui.InventoryListPane;
import ui.util.BoardController;
import ui.util.PaneSwitchAnimation;
import ui.util.TopBar;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryUIController implements Initializable {
    @FXML
    TopBar bar;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    StackPane board;

    @FXML
    BoardController boardController;

    GoodsblService goodsblService = new GoodsblService_Stub();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);

        boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150), board));

        try {
            InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryGift);
            inventoryListPane.historyAdd = true;
            inventoryListPane.refresh(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                    try {
                        if (newVal != null) {
                            if (newVal.getId().equals("goods")) {
                                GoodsListPane goodsListPane = new GoodsListPane();
                                goodsListPane.refresh(true);
                                //stockListPane.refresh(true);
                            }
                        }
                    }

    }*/
    }
}