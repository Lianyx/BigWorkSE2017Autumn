package ui.accountantui;

import blService.billblservice.ChargeBillReceiptblService;
import blService.genericblService.ReceiptblService;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;


    public MyChargeDetailPane() {
        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        chargeItemTreeTable.sumProperty().addListener(t->{sumField.setText(chargeItemTreeTable.getSum()+"");});
    }

    public MyChargeDetailPane(ChargeReceiptVO receiptVO) {
        super(receiptVO);

        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        clientField.disableProperty().bind(modifyState.not());
        addTransferButton.visibleProperty().bind(modifyState);

        chargeItemTreeTable.sumProperty().addListener(t->{sumField.setText(chargeItemTreeTable.getSum()+"");});


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
        receiptVO.setTransferList(chargeItemTreeTable.getList());
    }

    @Override
    protected void setRedCredit(ChargeReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setClientID(receiptVO.getClientID());
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
        chargeItemTreeTable.setList(receiptVO.getTransferList());
        clientField.setText(String.valueOf(receiptVO.getClientID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }


    @FXML
    private void addTransfer() {
        ArrayList<TransferItemVO> temp = chargeItemTreeTable.getList();
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
        chargeItemTreeTable.add(new TransferItemVO(0,0,"TODO"));
    }
}
