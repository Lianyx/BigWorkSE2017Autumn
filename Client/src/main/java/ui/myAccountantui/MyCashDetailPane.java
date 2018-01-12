package ui.myAccountantui;

import blService.billblservice.CashBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.UserInfomation;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;

import java.util.ArrayList;

public class MyCashDetailPane extends MyReceiptDetailPane<CashReceiptVO> {
    @FXML
    private TextField accountField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    @FXML
    private CashItemTreeTable cashItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addCashButton;


    public MyCashDetailPane() {
    }

    public MyCashDetailPane(CashReceiptVO receiptVO) {
        super(receiptVO);
        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);

        accountField.disableProperty().bind(modifyState.not());
        addCashButton.visibleProperty().bind(modifyState);

        cashItemTreeTable.sumProperty().addListener(t->{sumField.setText(cashItemTreeTable.getSum()+"");});

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
        receiptVO.setCashList(cashItemTreeTable.getList());
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(UserInfomation.username);
        sumField.setText(String.valueOf(receiptVO.getTotal()));
        cashItemTreeTable.setList(receiptVO.getCashList());
        accountField.setText(String.valueOf(receiptVO.getAccountID()));
    }

    @FXML
    private void addTransfer() {
        cashItemTreeTable.add(new CashItemVO("TODO",0,"TODO"));
    }
}
