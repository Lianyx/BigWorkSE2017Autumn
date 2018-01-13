package ui.accountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.memberui.ChooseList;
import ui.common.MyReceiptDetailPane;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.MemberVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;


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
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;

    public MyPaymentDetailPane() {
        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        paymentItemTreeTable.sumProperty().addListener(t->{sumField.setText(paymentItemTreeTable.getSum()+"");});
    }

    public MyPaymentDetailPane(PaymentReceiptVO receiptVO) {
        super(receiptVO);

        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        paymentItemTreeTable.sumProperty().addListener(t->{sumField.setText(paymentItemTreeTable.getSum()+"");});

    }

    @Override
    protected void initiate() {
        super.initiate();
    }



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
        receiptVO.setclientID(Integer.parseInt(clientField.getText()));
        receiptVO.setTransferList(paymentItemTreeTable.getList());

    }

    @Override
    protected void setRedCredit(PaymentReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setclientID(receiptVO.getclientID());
        redCreditVO.setSum(-redCreditVO.getSum());
        List<TransferItemVO> list = redCreditVO.getTransferList();
        List<TransferItemVO> temp = new ArrayList<>();
        for(TransferItemVO vo:list){
            temp.add(new TransferItemVO(vo.getAccountID(),-vo.getSum(),vo.getComment()));
        }
        redCreditVO.setTransferList(temp);
    }



    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(UserInfomation.username);
        sumField.setText(String.valueOf(receiptVO.getSum()));
        paymentItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getclientID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }


    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO,()->{clientField.setText(memberVO.getMemberId()+"");});
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(),chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }

    @FXML
    private void addTransfer() {
        ArrayList<TransferItemVO> temp = paymentItemTreeTable.getList();
        boolean flag = false;
        for(TransferItemVO vo:temp){
            if(vo.getAccountID()==-1){
                flag  =true;
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","请先完成已添加的转账信息","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
            }
            break;
        }
        if(!flag)
        paymentItemTreeTable.add(new TransferItemVO(-1,0,"TODO"));
    }



}
