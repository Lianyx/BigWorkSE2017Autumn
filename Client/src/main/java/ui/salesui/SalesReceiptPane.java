package ui.salesui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.salesblService.SalesblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ui.stockui.StockListItemTreeTable;
import ui.util.*;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.receiptVO.SalesReceiptVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockReceiptVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class SalesReceiptPane extends ReceiptDetailPane<SalesReceiptVO> {

    @FXML
    SalesListItemTreeTable salesListItemTreeTable;

    SalesblService salesblService;

    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;
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

    @FXML
    JFXTextField original;

    @FXML
    JFXTextField discount;

    @FXML
    JFXTextField token;

    @FXML
    TextField clerk;



    @FXML
    TextArea comment;

    SimpleBooleanProperty isSell = new SimpleBooleanProperty();

    SimpleDoubleProperty textSum = new SimpleDoubleProperty(0);


    public SalesReceiptPane(String id) {
        super("/salesui/salesreceipt.fxml",id);
        salesblService = ServiceFactory_Stub.getService(SalesblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);
        original.setDisable(true);
        sum.setDisable(true);
        stock.disableProperty().bind(modifyState.not());
        date.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());
        user.disableProperty().bind(modifyState.not());
        clerk.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        token.disableProperty().bind(modifyState.not());
        discount.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);
        RequireValid(clerk);
        DoubleValid(token);
        DoubleValid(discount);

        if(id.split("-")[0].equals("JHD")){
            this.isSell.set(true);
        }else{
            this.isSell.set(false);
        }

        original.setText("0");
        sum.setText("0");
        token.setText("0");
        discount.setText("0");

        salesListItemTreeTable.sumProperty().addListener(t->{original.setText(salesListItemTreeTable.getSum()+"");});

        textSum.addListener((b,o,n)->{
            sum.setText(""+n);
        });
        discount.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
               if(event.getCode() == KeyCode.ENTER){
                   try {
                       textSum.set(Double.parseDouble(original.getText()) - Double.parseDouble(discount.getText()));
                   }catch (Exception e){
                       e.printStackTrace();
                   }
               }
            }
        });

    }

    public SalesReceiptPane(boolean isSell){
        super("/salesui/salesreceipt.fxml");
        salesblService = ServiceFactory_Stub.getService(SalesblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        operator.setText(UserInfomation.username);
        date.setValue(LocalDate.now());
        RequireValid(provider);
        RequireValid(stock);
        switchPane(true);
        this.isSell.set(isSell);
        if(isSell){
            head.setText("XSD-");
        }else{
            head.setText("XSTHD-");
        }
        id.setText("-"+ String.format("%05d", salesblService.getDayId()));
        original.setText("0");
        sum.setText("0");
        token.setText("0");
        discount.setText("0");
        salesListItemTreeTable.sumProperty().addListener(t->{original.setText(salesListItemTreeTable.getSum()+"");});
        textSum.addListener((b,o,n)->{
            sum.setText(""+n);
        });
        token.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    textSum.set(salesListItemTreeTable.getSum()-Integer.parseInt(token.getText()));
                }
            }
        });
    }


    @FXML
    public void add() {
        salesListItemTreeTable.addGood(new ListGoodsItemVO("a", 1, "a", 1, 1, "a"));
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
        provider.setText("sabi");
        this.memberId = 5;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{
            if(!updateState.get()){
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane,"Wrong","sabi","Last","Ret");
            buttonDialog.setButtonTwo(()->boardController.Ret());
            buttonDialog.setButtonTwo(()->refresh(false));
            Predicate<Integer> p = (i)->{if((vo = salesblService.showDetail(receiptid))!=null) return true;return false;};
            GetTask task =
                    new GetTask(()-> {
                        provider.setText(vo.getMemberName());
                        operator.setText(UserInfomation.username);
                        stock.setText(vo.getStockName());
                        date.setValue(vo.getCreateTime().toLocalDate());
                        comment.setText(vo.getComment());
                        id.setText("-"+vo.getId().split("-")[2]);
                        memberId = vo.getMemberId();
                        head.setText(vo.getId().split("-")[0]+"-");
                        clerk.setText(vo.getClerkName());
                        sum.setText((vo.getOriginSum()-vo.getTokenAmount())+"");
                        token.setText(vo.getTokenAmount()+"");
                        discount.setText(vo.getDiscountAmount()+"");
                        original.setText(vo.getOriginSum()+"");
                        salesListItemTreeTable.setList(vo.getItems());
                        switchPane(toSwitch);
                    }, buttonDialog,p);

            new Thread(task).start();
            }else{
                switchPane(toSwitch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void savePendingReceipt() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.setButtonOne(() -> {
        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        if(isSell.get())
        this.vo = new SalesSellReceiptVO(receiptid,
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
                null,0);
        else
            this.vo = new SalesRetReceiptVO(receiptid,
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
                    Double.parseDouble(original.getText()));
        try {
            if (updateState.get())
                salesblService.insert(this.vo);
            else
                salesblService.update(this.vo);
            setBack();
        }catch (Exception e){
            e.printStackTrace();
        }
            });
        doubleButtonDialog.show();
    }

    @Override
    public void saveDraftReceipt() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonTwo(() -> {
        });
        doubleButtonDialog.setButtonOne(() -> {
        if(sum.getText().equals("")){
            sum.setText("1");
        }
        if(date.getValue()==null)
            date.setValue(LocalDate.now());
        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
            if(isSell.get())
                this.vo = new SalesSellReceiptVO(receiptid,
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
                        null,0);
            else
                this.vo = new SalesRetReceiptVO(receiptid,
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
                        Double.parseDouble(original.getText()));
        try {
            if (updateState.get())
                salesblService.insert(this.vo);
            else
                salesblService.update(this.vo);
            setBack();
        }catch (Exception e){
            e.printStackTrace();
        }
        });
        doubleButtonDialog.show();
    }

    public boolean valid(){
        if(date.getValue()!=null&&!operator.getText().equals("")&&!provider.getText().equals("")&&!stock.getText().equals("")&&!sum.getText().equals(""))
            return true;
        return false;
    }

}
