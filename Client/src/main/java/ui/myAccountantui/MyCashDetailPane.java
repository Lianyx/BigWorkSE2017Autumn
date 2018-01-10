package ui.myAccountantui;

import blService.billblservice.CashBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import vo.billReceiptVO.CashReceiptVO;

public class MyCashDetailPane extends MyReceiptDetailPane<CashReceiptVO> {
    @FXML
    private TextField accountField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    public MyCashDetailPane() {
    }

    public MyCashDetailPane(CashReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initiate() {
        super.initiate();
    }


    @Override
    protected String getURL() {
        return "/myAccountantui/myCashDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<CashReceiptVO>> getServiceClass() {
        return CashBillReceiptblService.class;
    }


    @Override
    protected boolean validate() {
        return super.validate() && isDouble(sumField.getText()) && isInteger(accountField.getText());
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
        receiptVO.setTotal(Double.parseDouble(sumField.getText()));
        receiptVO.setAccountID(Integer.parseInt(accountField.getText()));
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        sumField.setText(String.valueOf(receiptVO.getTotal()));
        accountField.setText(String.valueOf(receiptVO.getAccountID()));
    }


    @FXML
    private void addTransfer() {
        // TODO
    }
}
