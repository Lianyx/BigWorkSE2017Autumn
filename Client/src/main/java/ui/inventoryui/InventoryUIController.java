package ui.inventoryui;

import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import ui.inventoryui.goodsclassificationui.GoodsClassificationPane;
import ui.inventoryui.goodsui.GoodsListPane;
import ui.inventoryui.inventoryCheckui.InventoryCheckPane;
import ui.inventoryui.inventoryReceiptui.InventoryGiftListPane;
import ui.inventoryui.inventoryViewui.InventoryViewListPane;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTopBar;
import ui.util.BoardController;
import ui.util.PaneFactory;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryUIController implements Initializable {
    @FXML
    MyTopBar bar;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    StackPane board;

    @FXML
    BoardController boardController;

    @FXML
    StackPane mainpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /*  BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);

        boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150), board));*/
        PaneFactory.setMainPane(mainpane);

        BoardController.setBoardController(boardController);
        boardController = MyBoardController.getMyBoardController();
        // 这样再set回去，以后从boardController里面拿的就都是MyBoardController了，但是以后仍然需要强转
        BoardController.setBoardController(boardController);

        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);

        try {
            InventoryGiftListPane inventoryGiftListPane = new InventoryGiftListPane();
            inventoryGiftListPane.refresh(true);
         /*   InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryGift);
            inventoryListPane.historyAdd = true;
            inventoryListPane.refresh(false);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            try {
                if (newVal != null) {
                    if (newVal.getId().equals("goods")) {
                        GoodsListPane goodsListPane = new GoodsListPane();
                        goodsListPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryView")){
                        InventoryViewListPane inventoryViewListPane = new InventoryViewListPane();
                        inventoryViewListPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryCheck")){
                        InventoryCheckPane inventoryCheckPane = new InventoryCheckPane();
                        inventoryCheckPane.refresh(true);
                    }else if(newVal.getId().equals("goodsClassification")){
                        GoodsClassificationPane goodsClassificationPane = new GoodsClassificationPane(boardController, PaneFactory.getMainPane());
                        goodsClassificationPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryGift")){
                   /*     InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryGift);
                        inventoryListPane.refresh(true);*/
                    }else if(newVal.getId().equals("inventoryDamage")){
                     /*   InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryDamage);
                        inventoryListPane.refresh(true);*/
                    }else if(newVal.getId().equals("inventoryOverflow")){
                     /*   InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryOverflow);
                        inventoryListPane.refresh(true);*/
                    }else if(newVal.getId().equals("inventoryWarning")){
                       /* InventoryListPane inventoryListPane = new InventoryListPane(InventoryReceiptType.InventoryWarning);
                        inventoryListPane.refresh(true);*/
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }
}
