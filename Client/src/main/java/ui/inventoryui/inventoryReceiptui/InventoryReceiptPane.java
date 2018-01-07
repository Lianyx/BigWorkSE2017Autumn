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
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptDetailPane;
import ui.util.UserInfomation;
import util.ReceiptState;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;

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
    TextArea comment;

    InventoryReceiptType receiptType;

    public InventoryReceiptPane(String id) {
        super("/inventoryui/inventoryreceiptui/inventoryreceipt.fxml",id);
        inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);

        stock.disableProperty().bind(modifyState.not());
        date.disableProperty().bind(modifyState.not());
        member.disableProperty().bind(modifyState.not());
        user.disableProperty().bind(modifyState.not());

        RequireValid(operator);
        RequireValid(provider);
        RequireValid(stock);

        comment.disableProperty().bind(modifyState.not());

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

        inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());
        provider.setDisable(true);
        operator.setDisable(true);
        operator.setText(UserInfomation.username);
        date.setValue(LocalDate.now());
        RequireValid(provider);
        RequireValid(stock);
        switchPane(true);

        if (receiptType.equals(InventoryReceiptType.InventoryGift))
            head.setText("ZSD");
        else if (receiptType.equals(InventoryReceiptType.InventoryDamage))
            head.setText("BSD");
        else if (receiptType.equals(InventoryReceiptType.InventoryOverflow))
            head.setText("BYD");
        else if(receiptType.equals(InventoryReceiptType.InventoryWarning))
            head.setText("BJD");
        id.setText("-"+ String.format("%05d", inventoryblService.getDayId()));
    }

   @FXML
    public void add() {


    }

    @Override
    public void delete() {

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
    public void savePendingReceipt() {
        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        this.vo = new InventoryReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,provider.getText(),
                inventoryListItemTreeTable.getList(),
                comment.getText(),
                receiptType);
        if(updateState.get()) {
            try {
                inventoryblService.insert(this.vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            try {
                inventoryblService.update(this.vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveDraftReceipt() {
        if(date.getValue()==null)
            date.setValue(LocalDate.now());
        this.receiptid = head.getText().replace("-","")+"-"+date.getValue().toString().replace("-","")+"-"+id.getText().replace("-","");
        this.vo = new InventoryReceiptVO(receiptid,
                UserInfomation.userid,
                LocalDateTime.now(),LocalDateTime.now(),
                ReceiptState.PENDING,
                this.memberId,provider.getText(),
                inventoryListItemTreeTable.getList(),
                comment.getText(),
                receiptType);
        if(updateState.get()) {
            try {
                inventoryblService.insert(this.vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else {
            try {
                inventoryblService.update(this.vo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean valid() {
        if(date.getValue()!=null&&!operator.getText().equals("")&&!provider.getText().equals("")&&!stock.getText().equals("")&&!sum.getText().equals(""))
            return true;
        return false;
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{

            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane,"Wrong","sabi","Last","Ret");
            buttonDialog.setButtonTwo(()->boardController.Ret());
            buttonDialog.setButtonTwo(()->refresh(false));
            Predicate<Integer> p = (i)->{if((vo = inventoryblService.showDetail(receiptid))!=null) return true;return false;};
            GetTask task =
                    new GetTask(()-> {
                        provider.setText(vo.getMemberName());
                        operator.setText(UserInfomation.username);
                        date.setValue(vo.getCreateTime().toLocalDate());
                        comment.setText(vo.getComment());
                        id.setText("-"+vo.getId().split("-")[2]);
                        memberId = vo.getMemberId();
                        head.setText(vo.getId().split("-")[0]+"-");

                        inventoryListItemTreeTable.setList(vo.getItems());
                        switchPane(toSwitch);
                    }, buttonDialog,p);

            new Thread(task).start();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
