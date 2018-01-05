package ui.inventoryManagerui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import blService.goodsblService.GoodsblService;
import blService.inventoryblService.InventoryShowblService;
import blService.inventoryblService.InventoryblService;
import blServiceStub.goodsClassificationblService_Stub.GoodsClassificationblService_Stub;
import blServiceStub.goodsblService_Stub.GoodsblService_Stub;
import blServiceStub.inventoryblService_Stub.InventoryblService_Stub2;
import blServiceStub.inventoryblService_Stub.InventoryblService_Stub3;
import blServiceStub.inventoryblService_Stub.InventoryblService_Stub4;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.inventoryManagerui.inventoryGiftReceiptui.InventoryGiftDetailPane;
import ui.inventoryManagerui.inventoryGiftReceiptui.InventoryGiftPane;
import ui.util.BoardController;
import ui.util.HistoricalRecord;
import ui.util.PaneSwitchAnimation;
import ui.util.TopBar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryUIController implements Initializable{
    @FXML
    AnchorPane GoodsList;

    @FXML
    TopBar bar;

    @FXML
    JFXListView<Label> navigation;

    @FXML
    Label goodsList;

    @FXML
    StackPane board;

    @FXML
    StackPane mainpane;

    @FXML
    private BoardController boardController;

    GoodsblService goodsblService_stub;
    InventoryShowblService showblService;
    InventoryblService inventoryblService;
    GoodsClassificationblService goodsClassificationblService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bar.setBoardController(boardController);
        goodsblService_stub = new GoodsblService_Stub();

        try {
            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));
            GoodsListPane goodsListPane = new GoodsListPane(goodsblService_stub,boardController);
            goodsListPane.setMainpane(mainpane);
            board.getChildren().setAll(goodsListPane);
            HistoricalRecord.addPane(goodsListPane);
            /*
            不懂这个的作用
            但是Platfrom.runLater（）应该是去换界面？
             */
            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                new Thread(() -> {
                    Platform.runLater(() -> {
                        try {
                            if (newVal != null) {
                              /*  if (newVal.getId().equals("usermodify")) {
                                    boardController.switchTo(new UserModifyPane());
                                } else*/ if (newVal.getId().equals("商品分类")) {
                                    GoodsListPane listPane = new GoodsListPane(goodsblService_stub,boardController);
                                    goodsListPane.setMainpane(mainpane);
                                    boardController.switchTo(listPane);
                                }


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }).start();
            });




        }catch (Exception e){

        }
    }

    @FXML
    public void chooseTimePane(){
        showblService = new InventoryblService_Stub2();
        ChooseTimePane chooseTimePane = new ChooseTimePane(showblService,boardController,mainpane);
        JFXDialog dialog = new JFXDialog(mainpane,chooseTimePane,JFXDialog.DialogTransition.CENTER);
        chooseTimePane.setDialog(dialog);
        dialog.show();
    }

    @FXML
    public void inventoryCheckPane(){
        showblService = new InventoryblService_Stub3();
        try {
            boardController.switchTo(new InventoryCheckPane(showblService,boardController,mainpane));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void giftReceiptPane(){
        bar.setBoardController(boardController);

        inventoryblService = new InventoryblService_Stub4();

        //board.getChildren().setAll(new InventoryGiftDetailPane(boardController,mainpane));

        try {
            boardController.switchTo(new InventoryGiftPane(inventoryblService,boardController,mainpane));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void damageReceiptPane(){
        goodsClassificationblService = new GoodsClassificationblService_Stub();
        try {
            boardController.switchTo(new GoodsClassificationPane(goodsClassificationblService,boardController,mainpane));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void overflowReceiptPane(){

    }

    @FXML
    public void warningReceiptPane(){

    }
}
