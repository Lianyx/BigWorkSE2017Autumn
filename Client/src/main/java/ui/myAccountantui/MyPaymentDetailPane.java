package ui.myAccountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.UserInfomation;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;


public class MyPaymentDetailPane extends MyReceiptDetailPane<PaymentReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    @FXML
    private PaymentItemTreeTable paymentItemTreeTable;
    @FXML
    private TextField operator;
    @FXML
    private JFXRippler addTransferButton;



    /**
     * Constructor related
     */
    public MyPaymentDetailPane() {
    }

    public MyPaymentDetailPane(PaymentReceiptVO receiptVO) {
        super(receiptVO);

        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        paymentItemTreeTable.sumProperty().addListener(t->{sumField.setText(paymentItemTreeTable.getSum()+"");});

    }

    @Override
    protected void initiate() {
        super.initiate();
        // 需要set disable property吗？
    }

    /**
     * implement methods
     */
    @Override
    protected String getURL() {
        return "/myAccountantui/myPaymentDetailPane.fxml";
    }

    @Override
    protected Class<? extends ReceiptblService<PaymentReceiptVO>> getServiceClass() {
        return PaymentBillReceiptblService.class;
    }



    @Override
    protected boolean validate() {
        return super.validate() && isDouble(sumField.getText()) && isInteger(clientField.getText());
    }

    private boolean isDouble(String doublish) { // 这两个其实用regex写出来更好看
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
    protected void updateReceiptVO() { // 这里不需要再检查了
        super.updateReceiptVO();
        receiptVO.setSum(Double.parseDouble(sumField.getText()));
        receiptVO.setclientID(Integer.parseInt(clientField.getText()));
        receiptVO.setTransferList(paymentItemTreeTable.getList());

    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(UserInfomation.username);
        sumField.setText(String.valueOf(receiptVO.getSum()));
        paymentItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getclientID()));
    }


    /**
     * FXML methods
     */
    @FXML
    private void addTransfer() {
        paymentItemTreeTable.add(new TransferItemVO(0,0,"TODO"));
    }

}
