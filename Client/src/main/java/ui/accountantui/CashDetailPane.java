package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.salesblService.SalesblService;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.util.*;
import vo.billReceiptVO.CashReceiptVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.receiptVO.SalesReceiptVO;

import java.time.LocalDate;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.RequireValid;

public class CashDetailPane extends ReceiptDetailPane<CashReceiptVO> {

    @FXML
    CashItemTreeTable cashItemTreeTable;

    CashBillReceiptblService cashBillReceiptblService;


    @FXML
    TextField operator;
    @FXML
    TextField account;
    @FXML
    JFXTextField sum;
    @FXML
    JFXDatePicker date;

    @FXML
    Label head;
    @FXML
    JFXButton member;
    @FXML
    JFXButton user;

    @FXML
    Label id;

    int memberId = 0;



    SimpleDoubleProperty textSum = new SimpleDoubleProperty(0);


    public CashDetailPane(String id) {
        super("/accountantui/CashDetailPane.fxml",id);
        cashBillReceiptblService = ServiceFactory_Stub.getService(CashBillReceiptblService.class.getName());
        operator.setDisable(true);
        sum.setDisable(true);

        date.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());
        user.disableProperty().bind(modifyState.not());

        RequireValid(operator);




        sum.setText("0");


        cashItemTreeTable.sumProperty().addListener(t->{sum.setText(cashItemTreeTable.getSum()+"");});

        textSum.addListener((b,o,n)->{
            sum.setText(""+n);
        });
    }

    public CashDetailPane(){
        super("/accountantui/cashDetailPane.fxml");
        cashBillReceiptblService = ServiceFactory_Stub.getService(CashBillReceiptblService.class.getName());
        account.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        operator.setText(UserInfomation.username);
        date.setValue(LocalDate.now());
        RequireValid(account);
        switchPane(true);

        head.setText("XJFYD-");

        sum.setText("0");

        cashItemTreeTable.sumProperty().addListener(t->{sum.setText(cashItemTreeTable.getSum()+"");});
        textSum.addListener((b,o,n)->{
            sum.setText(""+n);
        });
    }


    @FXML
    public void add() {

        cashItemTreeTable.add(new CashItemVO("1",1,"1"));
    }


    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{});
        doubleButtonDialog.setButtonTwo(()->{
            boardController.setRightAnimation();
            boardController.historicalSwitchTo((Refreshable) HistoricalRecord.pop());
            boardController.refresh();
        });
        doubleButtonDialog.show();
    }


    @FXML
    public void currentUser() {
        operator.setText(UserInfomation.username);
    }

    @FXML
    public void selectMember() {
        //provider.setText("sabi");
        this.memberId = 5;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane,"Wrong","sabi","Last","Ret");
            buttonDialog.setButtonTwo(()->boardController.Ret());
            buttonDialog.setButtonTwo(()->refresh(false));
            Predicate<Integer> p = (i)->{if((vo = cashBillReceiptblService.showDetail(receiptid))!=null) return true;return false;};
            GetTask task =
                    new GetTask(()-> {
                        operator.setText(UserInfomation.username);
                        date.setValue(vo.getCreateTime().toLocalDate());
                        id.setText("-"+vo.getId().split("-")[2]);
                        head.setText(vo.getId().split("-")[0]+"-");


                        cashItemTreeTable.setList(vo.getCashList());
                        switchPane(toSwitch);
                    }, buttonDialog,p);

            new Thread(task).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void savePendingReceipt() {
        /*this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        this.vo = new SalesReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,
                Double.parseDouble(discount.getText()),
                Double.parseDouble(token.getText()),
                Double.parseDouble(original.getText()),
                isSell.get());
        if(updateState.get())
            paymentBillReceiptblService.add(this.vo);
        else
            paymentBillReceiptblService.update(this.vo);*/
    }

    @Override
    public void saveDraftReceipt() {
        /*if(sum.getText().equals("")||sum.getText()==null){
            sum.setText("1");
        }
        if(date.getValue()==null)
            date.setValue(LocalDate.now());
        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        this.vo = new SalesReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,
                provider.getText(),
                clerk.getText(),
                stock.getText(),
                salesListItemTreeTable.getList(),
                comment.getText(),
                Double.parseDouble(discount.getText()),
                Double.parseDouble(token.getText()),
                Double.parseDouble(original.getText()),
                isSell.get());
        if(updateState.get())
            paymentBillReceiptblService.add(this.vo);
        else
            paymentBillReceiptblService.update(this.vo);*/
    }

    public boolean valid(){
        if(date.getValue()!=null&&!operator.getText().equals("")&&!account.getText().equals("")&&!sum.getText().equals(""))
            return true;
        return false;
    }

}
