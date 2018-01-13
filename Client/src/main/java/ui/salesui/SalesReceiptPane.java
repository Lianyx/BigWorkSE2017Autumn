package ui.salesui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import ui.inventoryui.goodsui.GoodChoose;
import ui.memberui.ChooseList;
import ui.myAccountantui.common.ItemTreeTable;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.util.*;
import vo.ListGoodsItemVO;
import vo.MemberVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.billReceiptVO.TransferItemVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.receiptVO.*;

import java.time.LocalDateTime;
import java.util.List;

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

    protected ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();

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
//        IntegerProperty toBeListened = new SimpleIntegerProperty(0);
//        try {
//            GoodChoose goodsChoose = new GoodChoose();
//            goodsChoose.choose(observableList, toBeListened);
//        } catch (Exception e) { // TODO 异常先不管了，而且下面这样写现在好像是一次性的，也没有回设num，不过先不管了
//            e.printStackTrace();
//        }
//
//
//        toBeListened.addListener((observable, oldValue, newValue) -> {
//            if (newValue.equals(1)) {
//                observableList.stream().map(ReceiptGoodsItemVO::toListGoodsItemVO)
//                        .forEach(i -> itemTreeTable.add(i));
//            }
//        });
    }

    @FXML
    private void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO,()->{provider.setText(memberVO.getName());});
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(),chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }


}
