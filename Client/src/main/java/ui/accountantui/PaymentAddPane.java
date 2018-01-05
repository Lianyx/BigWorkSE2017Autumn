package ui.accountantui;

import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import blServiceStub.billblservice_Stub.PaymentblService_Stub;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ui.util.BoardController;
import ui.util.Loading;
import ui.util.Refreshable;
import util.ReceiptState;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class PaymentAddPane extends Refreshable{
    @FXML
    PaymentItemTreeTable paymentItemTreeTable;

    BoardController boardController;

    //ReceiptblService receiptblService;
    PaymentBillReceiptblService paymentBillReceiptblService;

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

    public PaymentAddPane(){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/PaymentDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public PaymentAddPane(ReceiptblService receiptblService,BoardController boardController, StackPane mainpane){
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
    public void save()throws RemoteException{
        PaymentBillReceiptVO paymentBillReceiptVO=new PaymentBillReceiptVO(id.getText(),Integer.parseInt(operator.getText()),time,time,ReceiptState.DRAFT,Integer.parseInt(client.getText()),paymentItemTreeTable.getList(),Double.parseDouble(sum.getText()));
        ((PaymentblService_Stub)receiptblService).insert(paymentBillReceiptVO);
    }

    @FXML
    public void add(){
        //stockListItemTreeTable.addGood(new ListGoodsItemVO("a",1,"a",1,1,"a"));
        paymentItemTreeTable.add(new TransferItemVO(1,1,"1"));
    }

    @FXML
    public void modify(){

    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
