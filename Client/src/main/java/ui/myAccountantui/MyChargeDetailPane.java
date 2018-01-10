package ui.myAccountantui;

import blService.billblservice.ChargeBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.billReceiptVO.ChargeReceiptVO;

public class MyChargeDetailPane extends MyReceiptDetailPane<ChargeReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    public MyChargeDetailPane() {
    }

    public MyChargeDetailPane(ChargeReceiptVO receiptVO) {
        super(receiptVO);
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
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        sumField.setText(String.valueOf(receiptVO.getSum()));
        clientField.setText(String.valueOf(receiptVO.getClientID()));
    }


    @FXML
    private void addTransfer() {
        // TODO
    }
}
