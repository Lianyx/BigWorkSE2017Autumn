package ui.myAccountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.managerui.common.MyBoardController;
import ui.myAccountantui.generic.MyReceiptDetailPane;
import ui.util.Refreshable;
import vo.billReceiptVO.PaymentReceiptVO;


public class MyPaymentDetailPane extends MyReceiptDetailPane<PaymentReceiptVO> {
    @FXML
    private TextField clientField;
    @FXML
    private JFXTextField sumField;
    @FXML
    private TextArea commentArea;

    /**
     * Constructor related
     */
    public MyPaymentDetailPane() {
    }

    public MyPaymentDetailPane(PaymentReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected void initialize() {
        super.initialize();
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

    /**
     * Override method
     */

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
        // TODO 我没管transferList相关的（下面的reset同理）。而且这个vo里面没有comment………
    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        sumField.setText(String.valueOf(receiptVO.getSum()));
        clientField.setText(String.valueOf(receiptVO.getclientID()));
    }


    /**
     * FXML methods
     */
    @FXML
    private void addTransfer() {
        // TODO
    }
}
