package ui.managerui.businessProgressui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXCheckBoxOldSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.ReceiptSearchCondition;

import java.io.IOException;

public class BusinessProgressFilterPane extends AnchorPane {
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;
    @FXML
    private JFXCheckBox salesSellBox, salesRetBox, stockPurBox, stockRetBox, paymentBox, chargeBox, cashBox, overflowBox, damageBox, giftBox;
//    @FXML
//    private

    private ReceiptSearchCondition receiptSearchCondition;


    public BusinessProgressFilterPane(BusinessProgressPane businessProgressPane, ReceiptSearchCondition receiptSearchCondition) {
        this.receiptSearchCondition = receiptSearchCondition;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/businessProgressFilterPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        select.setOnAction(e -> {
            setSearchCondition();
            businessProgressPane.refresh(false);
        });
    }

    private void setSearchCondition() {

    }
}
