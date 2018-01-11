package ui.myAccountantui;

import blService.billblservice.ChargeBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.UserInfomation;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;

public class MyChargeDetailPane extends MyReceiptDetailPane<ChargeReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    @FXML
    private ChargeItemTreeTable chargeItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addTransferButton;

    ChargeReceiptVO chargeReceiptVO;

    public MyChargeDetailPane() {
    }

    public MyChargeDetailPane(ChargeReceiptVO receiptVO) {
        super(receiptVO);

        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        chargeItemTreeTable.sumProperty().addListener(t->{sumField.setText(chargeItemTreeTable.getSum()+"");});

        this.chargeReceiptVO = receiptVO;

    }

    @Override
    protected void initiate() {
        super.initiate();
    }


    @Override
    protected String getURL() {
        return "/myAccountantui/myChargeDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<ChargeReceiptVO>> getServiceClass() {
        return ChargeBillReceiptblService.class;
    }


    @Override
    protected boolean validate() {
        return super.validate() && isDouble(sumField.getText()) && isInteger(clientField.getText());
    }

    private boolean isDouble(String doublish) {
        try {
            Double.parseDouble(doublish);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String intish) {
        try {
            Integer.parseInt(intish);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    protected void updateReceiptVO() {
        super.updateReceiptVO();
        receiptVO.setSum(Double.parseDouble(sumField.getText()));
        receiptVO.setClientID(Integer.parseInt(clientField.getText()));
        receiptVO.setTransferList(chargeReceiptVO.getTransferList());
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(UserInfomation.username);
        sumField.setText(String.valueOf(receiptVO.getSum()));
        chargeItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getClientID()));
    }


    @FXML
    private void addTransfer() {

        chargeItemTreeTable.add(new TransferItemVO(0,0,"TODO"));
    }
}
