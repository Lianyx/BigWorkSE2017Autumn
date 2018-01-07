package ui.inventoryui.goodsui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.goodsblService.GoodsblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ui.util.BoardController;
import ui.util.ReceiptDetailPane;
import ui.util.Refreshable;
import vo.inventoryVO.GoodsVO;


import java.io.File;
import java.time.LocalDate;

import static ui.util.ValidatorDecorator.RequireValid;

public class GoodDetailPane extends ReceiptDetailPane<GoodsVO> {

    String goodId = "-1";

    GoodsblService goodsblService;

    private static String g = "";
    private static String f = "";
    private static String tw = "";

    @FXML
    JFXTextField goodName;

    @FXML
    JFXButton reset;

    @FXML
    Label date;

    @FXML
    TextField salePrice;

    public GoodDetailPane(String id) {
        this(false);
        this.goodId = id;

        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });

       // goodName.disableProperty.
    }

    public GoodDetailPane(boolean isAdd) {
        super("inventoryui/goodui/goodsdetail.fxml");
        goodsblService = ServiceFactory_Stub.getService(GoodsblService.class.getName());

        delete.setVisible(false);
        date.setText(LocalDate.now().toString());

        RequireValid(goodName);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }


    }

    @Override
    public void delete() {

    }

    @Override
    public void savePendingReceipt() {

    }

    @Override
    public void saveDraftReceipt() {

    }

    @Override
    public boolean valid() {
        return false;
    }

    @Override
    public void refresh(boolean toSwitch) {

    }
}
