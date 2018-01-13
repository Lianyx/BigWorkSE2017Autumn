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
import ui.util.OneButtonDialog;
import ui.util.PaneFactory;
import ui.util.UserInfomation;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private TextField createtime;
    @FXML
    private TextField lastmodifiedtime;


    public MyCashDetailPane() {
        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        accountField.disableProperty().bind(modifyState.not());
        addCashButton.visibleProperty().bind(modifyState);

        cashItemTreeTable.sumProperty().addListener(t->{sumField.setText(cashItemTreeTable.getSum()+"");});
    }

    public MyCashDetailPane(CashReceiptVO receiptVO) {
        super(receiptVO);
        modifyState = new SimpleBooleanProperty(false);

        operator.setDisable(true);
        sumField.setDisable(true);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

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

    @Override
    protected void setRedCredit(CashReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setTotal(-receiptVO.getTotal());
        redCreditVO.setAccountID(receiptVO.getAccountID());

        List<CashItemVO> list = receiptVO.getCashList();
        List<CashItemVO> temp = new ArrayList<>();
        for(CashItemVO vo:list){
            temp.add(new CashItemVO(vo.getName(),-vo.getPrice(),vo.getComment()));
        }
        redCreditVO.setCashList(temp);

    }

    @FXML
    @Override
    protected void reset() {
        super.reset();
        operator.setText(UserInfomation.username);
        sumField.setText(String.valueOf(receiptVO.getTotal()));
        cashItemTreeTable.setList(receiptVO.getCashList());
        accountField.setText(String.valueOf(receiptVO.getAccountID()));
        createtime.setText(receiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(receiptVO.getLastModifiedTime().toString());
    }

    @FXML
    private void addTransfer() {
        ArrayList<CashItemVO> temp = cashItemTreeTable.getList();
        boolean flag = false;
        for(CashItemVO vo:temp){
            if(vo.getPrice()==0){
                flag  =true;
                OneButtonDialog oneButtonDialog = new OneButtonDialog(PaneFactory.getMainPane(),"","请先完成已添加的条目信息","继续");
                oneButtonDialog.setButtonOne(()->{});
                oneButtonDialog.show();
            }
            break;
        }
        if(!flag)
            cashItemTreeTable.add(new CashItemVO("TODO",0,"TODO"));
    }
}
