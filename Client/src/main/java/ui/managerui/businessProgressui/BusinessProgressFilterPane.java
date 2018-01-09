package ui.managerui.businessProgressui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.skins.JFXCheckBoxOldSkin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.BillType;
import util.ReceiptSearchCondition;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BusinessProgressFilterPane extends AnchorPane {
    @FXML
    private JFXDatePicker beginTime, endTime;
    @FXML
    private JFXButton select;
    @FXML
    private JFXCheckBox salesSellBox, salesRetBox, stockPurBox, stockRetBox, paymentBox, chargeBox, cashBox, overflowBox, damageBox, giftBox;
    @FXML
    private JFXTextField memberField, clerkField, stockField;

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

        beginTime.setValue(LocalDate.now());
        endTime.setValue(LocalDate.now());

        select.setOnAction(e -> {
            setSearchCondition();
            businessProgressPane.refresh(false);
        });
    }

    private void setSearchCondition() {
        receiptSearchCondition.setBegin(LocalDateTime.of(beginTime.getValue(), LocalTime.MIN));
        receiptSearchCondition.setEnd(LocalDateTime.of(endTime.getValue(), LocalTime.MIN));

        receiptSearchCondition.getBillTypes().clear();
        if (salesSellBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.SalesSell);
        }
        if (salesRetBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.SalesRet);
        }
        if (stockPurBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.StockPur);
        }
        if (stockRetBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.StockRet);
        }
        if (paymentBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.BillPay);
        }
        if (chargeBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.BillCharge);
        }
        if (cashBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.Cash);
        }
        if (overflowBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryOverflow);
        }
        if (damageBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryDamage);
        }
        if (giftBox.isSelected()) {
            receiptSearchCondition.getBillTypes().add(BillType.InventoryGift);
        }

        receiptSearchCondition.setClerkName(clerkField.getText());
        receiptSearchCondition.setStockName(stockField.getText());
        try {
            receiptSearchCondition.setMemberId(Integer.parseInt(memberField.getText()));
        } catch (NumberFormatException e) {
            receiptSearchCondition.setMemberId(null);
        }

        System.out.println("Finish setSearchCondition");
    }
}
