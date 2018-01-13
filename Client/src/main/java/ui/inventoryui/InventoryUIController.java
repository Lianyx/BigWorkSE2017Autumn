package ui.inventoryui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import ui.inventoryui.goodsclassificationui.GoodsClassificationPane;
import ui.inventoryui.goodsui.GoodsListPane;
import ui.inventoryui.inventoryCheckui.InventoryCheckPane;
import ui.inventoryui.inventoryReceiptui.InventoryDamageListPane;
import ui.inventoryui.inventoryReceiptui.InventoryGiftListPane;
import ui.inventoryui.inventoryReceiptui.InventoryOverflowListPane;
import ui.inventoryui.inventoryReceiptui.InventoryWarningListPane;
import ui.inventoryui.inventoryViewui.ChooseTimePane;
import ui.inventoryui.inventoryViewui.InventoryViewListPane;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTopBar;
import ui.util.BoardController;
import ui.util.PaneFactory;

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

//    @FXML
//    StackPane mainpane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      /*  BoardController.setBoardController(boardController);

        bar.setBoardController(boardController);

        boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150), board));*/
//        PaneFactory.setMainPane(mainpane);

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
                        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                        jfxDialogLayout.setPrefWidth(220.0);
                        ChooseTimePane chooseTimePane = new ChooseTimePane();
                        jfxDialogLayout.setBody(chooseTimePane);
//                        JFXButton save = new JFXButton("Save");
                        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(), jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
                        chooseTimePane.setDialog(dialog);
                        dialog.show();

                        /*InventoryViewListPane inventoryViewListPane = new InventoryViewListPane();
                        inventoryViewListPane.refresh(true);*/
                    }else if(newVal.getId().equals("inventoryCheck")){
                        InventoryCheckPane inventoryCheckPane = new InventoryCheckPane();
                        inventoryCheckPane.refresh(true);
                    }else if(newVal.getId().equals("goodsClassification")){
                        GoodsClassificationPane goodsClassificationPane = new GoodsClassificationPane(boardController, PaneFactory.getMainPane());
                        goodsClassificationPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryGift")){
                        InventoryGiftListPane inventoryGiftListPane = new InventoryGiftListPane();
                        inventoryGiftListPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryDamage")){
                        InventoryDamageListPane inventoryDamageListPane = new InventoryDamageListPane();
                        inventoryDamageListPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryOverflow")){
                        InventoryOverflowListPane inventoryOverflowListPane = new InventoryOverflowListPane();
                        inventoryOverflowListPane.refresh(true);
                    }else if(newVal.getId().equals("inventoryWarning")){
                        InventoryWarningListPane inventoryWarningListPane = new InventoryWarningListPane();
                        inventoryWarningListPane.refresh(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }
}
