package ui.accountantui;

import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.stockui.StockListItemTreeTable;
import ui.userui.usermanagerui.BoardController;
import ui.util.Refreshable;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.PaymentBillReceiptVO;

public class PaymentDetailPane extends Refreshable{
    @FXML
    PaymentItemTreeTable paymentItemTreeTable;

    BoardController boardController;

    //ReceiptblService receiptblService;
    PaymentBillReceiptblService paymentBillReceiptblService;

    PaymentBillReceiptVO paymentBillReceiptVO;

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

    public PaymentDetailPane(){
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

    public PaymentDetailPane(PaymentBillReceiptVO paymentBillReceiptVO,BoardController boardController, StackPane mainpane){
        this();
        //this.paymentBillReceiptblService = paymentBillReceiptblService;
        this.paymentBillReceiptVO = paymentBillReceiptVO;
        this.boardController = boardController;
        this.mainpane = mainpane;
        //paymentItemTreeTable.setStockblService(stockblService);

        id.setText(paymentBillReceiptVO.getId());
        client.setText(String.valueOf(paymentBillReceiptVO.getclientID()));
        operator.setText(String.valueOf(paymentBillReceiptVO.getOperatorId()));
        state.setText(paymentBillReceiptVO.getReceiptState().toString());
        sum.setText(String.valueOf(paymentBillReceiptVO.getSum()));
        createtime.setText(paymentBillReceiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(paymentBillReceiptVO.getLastModifiedTime().toString());

        paymentItemTreeTable.setList(paymentBillReceiptVO.getTransferList());
        paymentItemTreeTable.setPaymentBillReceiptblService(paymentBillReceiptblService);
        paymentItemTreeTable.setBoardController(boardController);
        paymentItemTreeTable.setMainpane(mainpane);
       // paymentItemTreeTable.setBoardController(boardController);
        //paymentItemTreeTable.setMainpane(mainpane);
    }


    public PaymentDetailPane(BoardController boardController, StackPane mainpane){
        this(null,boardController,mainpane);
    }


    @FXML
    public void add(){
        //stockListItemTreeTable.addGood(new ListGoodsItemVO("a",1,"a",1,1,"a"));

    }







    @Override
    public void refresh(boolean toSwitch) {

    }
}
