package ui.inventoryManagerui.inventoryGiftReceiptui;

import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.inventoryVO.uiReceipt.GiftuiGoodsItemVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;

import java.awt.*;
import java.io.IOException;

public class InventoryGiftDetailPane extends Refreshable {
    InventoryGiftuiVO giftVO;

    @FXML
    GiftListItemTreeTable giftListItemTreeTable;

    BoardController boardController;

    InventoryblService inventoryblService;

    StackPane mainpane;

    @FXML
    JFXTextField operatorId;


    public InventoryGiftDetailPane() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryGiftDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public InventoryGiftDetailPane(InventoryGiftuiVO giftVO,BoardController boardController,StackPane mainpane) {
        this();
        this.giftVO = giftVO;
        this.boardController = boardController;
        this.mainpane = mainpane;
        //  giftListItemTreeTable = new GiftListItemTreeTable();
        giftListItemTreeTable.setBoardController(boardController);
        giftListItemTreeTable.setMainpane(mainpane);
        giftListItemTreeTable.setList(giftVO.getSet());

        /*
        在这里使用textfield会报错的
         */
        operatorId.setText(Integer.toString(giftVO.getOperatorId()));

    }


    @FXML
    public void add(){

    }

    @Override
    public void refresh(boolean toSwitch) {

    }
}
