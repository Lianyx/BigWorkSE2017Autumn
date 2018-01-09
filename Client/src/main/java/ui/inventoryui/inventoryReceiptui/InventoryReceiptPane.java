package ui.inventoryui.inventoryReceiptui;

import blService.blServiceFactory.ServiceFactory;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ui.util.*;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Predicate;

import static blService.blServiceFactory.ServiceFactory.stockblService;
import static ui.util.ValidatorDecorator.RequireValid;

public class InventoryReceiptPane extends ReceiptDetailPane<InventoryReceiptVO>{
    @FXML
    InventoryListItemTreeTable inventoryListItemTreeTable;

    InventoryblService inventoryblService;


    @FXML
    TextField operator;
    @FXML
    TextField operatorId;
    @FXML
    TextField receiptState;
    @FXML
    JFXDatePicker date;
    @FXML
    Label head;
    @FXML
    Label title1;
    @FXML
    Label title2;
    @FXML
    Label id;
    @FXML
    TextArea comment;

    int memberId = 0;




    InventoryReceiptType receiptType;

    public InventoryReceiptPane(String id) {
//        super("/inventoryui/inventoryreceiptui/inventoryreceipt.fxml",id);
        super("/inventoryui/inventoryreceiptui/inventoryreceipt.fxml",true);

        inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());
        operator.setDisable(true);
        operatorId.setDisable(true);
        receiptState.setDisable(true);

        date.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(operatorId);

        if(id.split("-")[0].equals("ZSD")){
            this.receiptType = InventoryReceiptType.InventoryGift;
        }else if(id.split("-")[0].equals("BSD")){
            this.receiptType = InventoryReceiptType.InventoryDamage;
        }else if(id.split("-")[0].equals("BYD")) {
            this.receiptType = InventoryReceiptType.InventoryOverflow;
        }else if(id.split("-")[0].equals("BJD")) {
            this.receiptType = InventoryReceiptType.InventoryWarning;
        }
    }

    public InventoryReceiptPane(InventoryReceiptType receiptType) {
        super("inventoryui/inventoryreceiptui/inventoryreceipt.fxml");
        this.receiptType = receiptType;

        title2.setVisible(false);

        inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());
        operator.setDisable(true);
        operatorId.setDisable(true);
        receiptState.setDisable(true);

        operator.setText(UserInfomation.username);
        date.setValue(LocalDate.now());

        RequireValid(operator);
        RequireValid(operatorId);

        switchPane(true);

        if (receiptType.equals(InventoryReceiptType.InventoryGift)) {
            head.setText("ZSD");
            title1.setText("InventoryGiftReceipt");
        } else if (receiptType.equals(InventoryReceiptType.InventoryDamage)){
            head.setText("BSD");
            title1.setText("InventoryDamageReceipt");
            title1.setVisible(false);
            title2.setVisible(true);
        }else if (receiptType.equals(InventoryReceiptType.InventoryOverflow)) {
            head.setText("BYD");
            title1.setText("InventoryOverflowReceipt");
        }else if(receiptType.equals(InventoryReceiptType.InventoryWarning)) {
            head.setText("BJD");
            title1.setText("InventoryWarningReceipt");
        }

        id.setText("-"+ String.format("%05d", inventoryblService.getDayId()));
    }

   @FXML
    public void add() {
        inventoryListItemTreeTable.addGood(new ReceiptGoodsItemVO("a", 1, 50, 1, 1, 70));


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
        /*
        未完成
         */

    }


    @FXML
    public void currentUser() {
        operator.setText(UserInfomation.username);
        operatorId.setText(Integer.toString(UserInfomation.userid));
    }


    @Override
    public void savePendingReceipt() {
//        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
//        this.vo = new InventoryReceiptVO(receiptid,
//                UserInfomation.userid,
//                LocalDateTime.now(),LocalDateTime.now(),
//                ReceiptState.PENDING,
//                operator.getText(),
//                inventoryListItemTreeTable.getList(),
//                comment.getText(),
//                receiptType);
//        if(updateState.get()) {
//            try {
//                inventoryblService.insert(this.vo);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }else {
//            try {
//                inventoryblService.update(this.vo);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void saveDraftReceipt() {
//        if(date.getValue()==null)
//            date.setValue(LocalDate.now());
//        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
//        this.vo = new InventoryReceiptVO(receiptid,
//                UserInfomation.userid,
//                LocalDateTime.now(),LocalDateTime.now(),
//                ReceiptState.PENDING,
//                operator.getText(),
//                inventoryListItemTreeTable.getList(),
//                comment.getText(),
//                receiptType);
//        if(updateState.get()) {
//            try {
//                inventoryblService.insert(this.vo);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }else {
//            try {
//                inventoryblService.update(this.vo);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }

    }

    @Override
    public boolean valid() {
        if(date.getValue()!=null&&!operator.getText().equals("")&&!operatorId.getText().equals("")&&!receiptState.getText().equals(""))
            return true;
        return false;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{
//
//            DoubleButtonDialog buttonDialog =
//                    new DoubleButtonDialog(mainpane,"Wrong","sabi","Last","Ret");
//            buttonDialog.setButtonTwo(()->boardController.Ret());
//            buttonDialog.setButtonTwo(()->refresh(false));
//            Predicate<Integer> p = (i)->{if((vo = inventoryblService.showDetail(receiptid))!=null) return true;return false;};
//            GetTask task =
//                    new GetTask(()-> {
//                        operatorId.setText(Integer.toString(vo.getMemberId()));
//                        operator.setText(UserInfomation.username);
//                        date.setValue(vo.getCreateTime().toLocalDate());
//                        comment.setText(vo.getComment());
//                        id.setText("-"+vo.getId().split("-")[2]);
//                        memberId = vo.getMemberId();
//                        head.setText(vo.getId().split("-")[0]+"-");
//                        receiptState.setText(vo.getReceiptState().toString());
//
//                        inventoryListItemTreeTable.setList(vo.getItems());
//                        switchPane(toSwitch);
//                    }, buttonDialog,p);
//
//            new Thread(task).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
