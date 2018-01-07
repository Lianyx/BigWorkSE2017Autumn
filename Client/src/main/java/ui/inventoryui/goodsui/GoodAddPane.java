package ui.inventoryui.goodsui;

import blService.goodsblService.GoodsblService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import vo.inventoryVO.GoodsVO;

public class GoodAddPane extends AnchorPane {
    @FXML
    JFXTextField goodId;
    @FXML
    JFXTextField goodName;
    @FXML
    JFXTextField goodType;
    @FXML
    JFXTextField classifyId;
    @FXML
    JFXTextField inventoryNum;
    @FXML
    JFXTextField alarmNum;
    @FXML
    JFXTextField salePrice;
    @FXML
    JFXTextField purPrice;
    @FXML
    JFXTextField recentSalePrice;
    @FXML
    JFXTextField recentPurPrice;

    @FXML
    AnchorPane pane1;
    @FXML
    AnchorPane pane2;
    @FXML
    AnchorPane pane3;
    @FXML
    AnchorPane pane4;
    @FXML
    AnchorPane pane5;

    GoodsVO goodsVO;

    GoodsblService goodsblService;

    JFXDialog dialog;

    public GoodAddPane(GoodsblService goodsblService){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodui/goodAddPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.goodsblService = goodsblService;

        goodId.focusedProperty().addListener(colorFocus(pane1));
        goodName.focusedProperty().addListener(colorFocus(pane1));
        goodType.focusedProperty().addListener(colorFocus(pane2));
        classifyId.focusedProperty().addListener(colorFocus(pane2));
        inventoryNum.focusedProperty().addListener(colorFocus(pane3));
        alarmNum.focusedProperty().addListener(colorFocus(pane3));
        purPrice.focusedProperty().addListener(colorFocus(pane4));
        salePrice.focusedProperty().addListener(colorFocus(pane4));
        recentPurPrice.focusedProperty().addListener(colorFocus(pane5));
        recentSalePrice.focusedProperty().addListener(colorFocus(pane5));

    }

    public ChangeListener<Boolean> colorFocus(AnchorPane pane){
        return new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue==true){
                    pane.setStyle("-fx-background-color: rgb(248,250,253);");
                }else{
                    pane.setStyle("-fx-background-color: white;");
                }
            }
        };
    }

    @FXML
    public void save(){
        goodsVO = new GoodsVO(goodId.getText(),goodName.getText(),goodType.getText(),classifyId.getText(),Integer.parseInt(inventoryNum.getText()),
                Double.parseDouble(purPrice.getText()),Double.parseDouble(salePrice.getText()),Double.parseDouble(recentPurPrice.getText()),
                Double.parseDouble(recentSalePrice.getText()),Integer.parseInt(alarmNum.getText()));
        goodsblService.addGoods(goodsVO);
        dialog.close();
    }

    public GoodsVO getGoodsVO() {
        return goodsVO;
    }

    public void setDialog(JFXDialog dialog) {
        this.dialog = dialog;
    }
}
