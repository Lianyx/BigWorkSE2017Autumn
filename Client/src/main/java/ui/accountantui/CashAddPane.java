package ui.accountantui;

import blService.checkblService.ReceiptblService;
import blServiceStub.billblservice_Stub.CashblService_Stub;
import blServiceStub.billblservice_Stub.ChargeblService_Stub;
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
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class CashAddPane extends Refreshable {
    @FXML
    CashItemTreeTable cashItemTreeTable;

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

    public CashAddPane(){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/CashDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public CashAddPane(ReceiptblService receiptblService,BoardController boardController, StackPane mainpane){
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
        CashBillReceiptVO cashBillReceiptVO = new CashBillReceiptVO(id.getText(),Integer.parseInt(operator.getText()),time,time, ReceiptState.DRAFT,Integer.parseInt(client.getText()),Double.parseDouble(sum.getText()),cashItemTreeTable.getList());
        ((CashblService_Stub)receiptblService).insert(cashBillReceiptVO);
    }

    @FXML
    public void add(){

        cashItemTreeTable.add(new CashItemVO("1",1,"1"));
    }

    @FXML
    public void modify(){

    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
