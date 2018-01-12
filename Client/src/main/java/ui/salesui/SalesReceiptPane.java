package ui.salesui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import ui.memberui.ChooseList;
import ui.myAccountantui.common.ItemTreeTable;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.*;
import vo.ListGoodsItemVO;
import vo.MemberVO;
import vo.receiptVO.*;

import java.time.LocalDateTime;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;
import static ui.util.ValidatorDecorator.isDouble;

public abstract class SalesReceiptPane<T extends SalesReceiptVO> extends MyReceiptDetailPane<T> {

    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;
    @FXML
    JFXButton member;


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

    SimpleDoubleProperty textSum;

    @FXML
    ItemTreeTable itemTreeTable;

    public SalesReceiptPane() {
    }

    public SalesReceiptPane(T receiptVO) {
        super(receiptVO);
    }

    @Override
    protected String getURL() {
        return "/salesui/salesreceipt.fxml";
    }

    @Override
    public void initiate(){
        super.initiate();
        provider.setDisable(true);
        operator.setDisable(true);
        original.setDisable(true);
        sum.setDisable(true);

        stock.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());
//        user.disableProperty().bind(modifyState.not());
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

        original.setText("0");
        sum.setText("0");
        token.setText("0");
        discount.setText("0");


        textSum = new SimpleDoubleProperty(0);
        textSum.addListener((b,o,n)->{
            sum.setText(""+n);
        });

        itemTreeTable.sumProperty().addListener(t->{original.setText(itemTreeTable.getSum()+"");});

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

    @Override
    public void reset(){
        super.reset();
        provider.setText(receiptVO.getMemberName());
        operator.setText(UserInfomation.username);
        stock.setText(receiptVO.getStockName());
        comment.setText(receiptVO.getComment());
        idLabel.setText(receiptVO.getId());
        clerk.setText(receiptVO.getClerkName());
        sum.setText((receiptVO.getOriginSum()-receiptVO.getTokenAmount())+"");
        token.setText(receiptVO.getTokenAmount()+"");
        discount.setText(receiptVO.getDiscountAmount()+"");
        original.setText(receiptVO.getOriginSum()+"");
        itemTreeTable.setList(receiptVO.getItems());
    }


    @Override
    public void updateReceiptVO(){
        super.updateReceiptVO();
        receiptVO.setOperatorId(UserInfomation.userid);
        receiptVO.setLastModifiedTime(LocalDateTime.now());
        receiptVO.setMemberId(3);  ///////////////
        receiptVO.setMemberName(provider.getText());
        receiptVO.setClerkName(clerk.getText());
        receiptVO.setStockName(stock.getText());
        receiptVO.setItems(itemTreeTable.getList());
        receiptVO.setComment(comment.getText());
        receiptVO.setDiscountAmount(Double.parseDouble(discount.getText()));
        receiptVO.setTokenAmount(Double.parseDouble(token.getText()));
        receiptVO.setOriginSum(Double.parseDouble(original.getText()));
    }
    @Override
    protected boolean validate() {
        return super.validate() && isDouble(discount.getText()) && isDouble(original.getText()) && isDouble(token.getText());
    }

    @FXML
    public void addTransfer(){
        itemTreeTable.add((new ListGoodsItemVO("a", "54", "a", 1, 1, "a")));
    }

    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO,()->{provider.setText(memberVO.getName());});
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(),chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }

    /*

    public SalesReceiptPane(SalesReceiptListVO salesReceiptListVO) {
        super("/salesui/salesreceipt.fxml",false);
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
        this.salesReceiptListVO = salesReceiptListVO;
        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);
        RequireValid(clerk);
        DoubleValid(token);
        DoubleValid(discount);

        if(salesReceiptListVO.getId().split("-")[0].equals("JHD")){
            this.isSell.set(true);
            try {
                salesblService = new SalesSellbl();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            this.isSell.set(false);
            try {
                salesblService = new SalesRetbl();
            }catch (Exception e){
                e.printStackTrace();
            }
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
            try {
                salesblService = new SalesSellbl();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            head.setText("XSTHD-");
            try {
                salesblService = new SalesRetbl();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try{
           this.vo = salesblService.getNew();
        }catch(Exception e){
           e.printStackTrace();
        }


       id.setText("-"+ String.format("%05d", vo.getId().split("-")[0]));
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
            Predicate<Integer> p = (i)->{
                try {
                    vo = salesblService.search(vo.getId(), vo.getCreateTime());
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(vo!=null)
                    return true;
                return false;
            };
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
        if(isSell.get()) {
            this.vo = new SalesSellReceiptVO(vo.getId(),
                    UserInfomation.userid,
                    vo.getCreateTime(), LocalDateTime.now(),
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
                    null, 0);
        }else {
            this.vo = new SalesRetReceiptVO(vo.getId(),
                    UserInfomation.userid,
                    vo.getCreateTime(), LocalDateTime.now(),
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
        }
        try {
            if (updateState.get()) {
                if(isSell.get())
                salesblService.insert(kengdie(vo));
            }else{
                salesblService.update(kengdie(vo));
            }
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
            if(isSell.get())
                this.vo = new SalesSellReceiptVO(vo.getId(),
                        UserInfomation.userid,
                        vo.getCreateTime(),LocalDateTime.now(),
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
                this.vo = new SalesRetReceiptVO(vo.getId(),
                        UserInfomation.userid,
                        vo.getCreateTime(),LocalDateTime.now(),
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
                salesblService.insert(kengdie(vo));
            else
                salesblService.update(kengdie(vo));
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
    private <TF extends SalesReceiptVO> TF kengdie(SalesReceiptVO salesReceiptVO) {
        try {
            return (TF) salesReceiptVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    */
}
