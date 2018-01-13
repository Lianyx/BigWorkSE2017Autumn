package ui.stockui;

import com.jfoenix.controls.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.inventoryui.goodsui.GoodChoose;
import ui.memberui.ChooseList;
import ui.common.ItemTreeTable;
import ui.common.bigPane.MyReceiptDetailPane;
import ui.util.*;
import vo.MemberVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;
import vo.receiptVO.StockReceiptVO;

import java.time.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static ui.util.ValidatorDecorator.RequireValid;
import static ui.util.ValidatorDecorator.isDouble;


public abstract class StockReceiptPane<T extends StockReceiptVO> extends MyReceiptDetailPane<T> {
    @FXML
    TextField operator;
    @FXML
    TextField provider;
    @FXML
    JFXTextField sum;
    @FXML
    TextField stock;

    @FXML
    ModifyButton modify;

    @FXML
    JFXButton member;

    @FXML
    TextArea comment;

    @FXML
    ItemTreeTable itemTreeTable;

    public StockReceiptPane() {
    }

    public StockReceiptPane(T receiptVO) {
        super(receiptVO);
    }

    @Override
    public void initiate() {
        super.initiate();
        provider.setDisable(true);
        operator.setDisable(true);
        sum.setDisable(true);
        sum.disableProperty().bind(modifyState.not());
        stock.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);


        comment.disableProperty().bind(modifyState.not());
        sum.setText("0");
        itemTreeTable.sumProperty().addListener(t -> {
            sum.setText(itemTreeTable.getSum() + "");
        });

        modifyState.bind(modify.modifyProperty());

    }


    @Override
    protected String getURL() {
        return "/stockui/stockreceipt.fxml";
    }

    @Override
    protected void updateReceiptVO() {
        super.updateReceiptVO();
        receiptVO.setOperatorId(UserInfomation.userid);
        receiptVO.setLastModifiedTime(LocalDateTime.now());
        receiptVO.setMemberId(3);
        receiptVO.setMemberName(provider.getText());
        receiptVO.setStockName(stock.getText());
        receiptVO.setSum(Double.parseDouble(sum.getText()));
        receiptVO.setItems(itemTreeTable.getList());
        System.out.println("this is the killer");
        System.out.println(itemTreeTable.getList());

        receiptVO.setComment(comment.getText());
    }

    @Override
    protected void reset() {
        super.reset();
        provider.setText(receiptVO.getMemberName());
        operator.setText(UserInfomation.username);
        stock.setText(receiptVO.getStockName());
        comment.setText(receiptVO.getComment());
        sum.setText(receiptVO.getSum() + "");
        itemTreeTable.setList(receiptVO.getItems());
    }

    @Override
    public boolean validate() {
        if (super.validate() && isDouble(sum.getText())) {
            return true;
        }
        return false;
    }

    @FXML
    public void addTransfer() throws  Exception {
        GoodChoose goodChoose = new GoodChoose();
        ObservableList<ReceiptGoodsItemVO> observableList = FXCollections.observableArrayList();
        SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(0);
        goodChoose.choose(observableList,integerProperty);
        integerProperty.addListener((b,o,n)->{
            if(n.intValue()==1){
                itemTreeTable.setList(observableList.stream().map(t->t.toListGoodsItemVO()).collect(Collectors.toCollection(ArrayList::new)));
                sum.setText(String.valueOf(itemTreeTable.getSum()));

            }
        });

    }

    @FXML
    public void selectMember() {
        MemberVO memberVO = new MemberVO();
        ChooseList chooseList = new ChooseList(memberVO,()->{provider.setText(memberVO.getName());});
        JFXDialog dialog = new JFXDialog(PaneFactory.getMainPane(),chooseList, JFXDialog.DialogTransition.CENTER);
        chooseList.setDialog(dialog);
        dialog.show();
    }


}
