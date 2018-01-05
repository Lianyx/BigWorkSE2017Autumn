package ui.accountantui;

import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import blServiceStub.billblservice_Stub.ChargeblService_Stub;
import blServiceStub.billblservice_Stub.PaymentblService_Stub;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import ui.util.Refreshable;
import util.ReceiptState;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class ChargeAddPane extends Refreshable {
    @FXML
    ChargeItemTreeTable chargeItemTreeTable;

    BoardController boardController;

    //ReceiptblService receiptblService;
    //ChargeBillReceiptblService chargeBillReceiptblService;

    ReceiptblService receiptblService;

    StackPane mainpane;

    @FXML
    TextField id;
    @FXML
    TextField client;
    @FXML
    TextField operator;
    @FXML
    TextField state;
    @FXML
    TextField sum;
    @FXML
    TextField createtime;
    @FXML
    TextField lastmodifiedtime;

    @FXML
    JFXButton modify;

    @FXML
    JFXButton save;

    @FXML
    MaterialDesignIconView pen;

    LocalDateTime time;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    public ChargeAddPane(){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/ChargeDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public ChargeAddPane(ReceiptblService receiptblService,BoardController boardController, StackPane mainpane){
        this();
        this.receiptblService = receiptblService;
        this.boardController = boardController;
        this.mainpane = mainpane;
        //paymentItemTreeTable.setStockblService(stockblService);

        time = LocalDateTime.now();


        createtime.setText(time.toString());
        lastmodifiedtime.setText(time.toString());

        pen.setVisible(false);
        //save.visibleProperty().bind(modifyState);
        createtime.setDisable(true);
        lastmodifiedtime.setDisable(true);

        //paymentItemTreeTable.setPaymentBillReceiptblService(paymentBillReceiptblService);
        //paymentItemTreeTable.setBoardController(boardController);
        //paymentItemTreeTable.setMainpane(mainpane);

    }


    @FXML
    public void save()throws RemoteException {
        ChargeBillReceiptVO chargeBillReceiptVO=new ChargeBillReceiptVO(id.getText(),Integer.parseInt(operator.getText()),time,time, ReceiptState.DRAFT,Integer.parseInt(client.getText()),chargeItemTreeTable.getList(),Double.parseDouble(sum.getText()));
        ((ChargeblService_Stub)receiptblService).insert(chargeBillReceiptVO);
    }

    @FXML
    public void add(){

        chargeItemTreeTable.add(new TransferItemVO(1,1,"1"));
    }

    @FXML
    public void modify(){

    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
