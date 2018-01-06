package ui.accountantui;

import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.stockui.StockListItemTreeTable;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;

public class ChargeDetailPane extends Refreshable{
    @FXML
    ChargeItemTreeTable chargeItemTreeTable;

    BoardController boardController;

    //ReceiptblService receiptblService;
    ChargeBillReceiptblService chargeBillReceiptblService;

    ChargeBillReceiptVO chargeBillReceiptVO;

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

    public ChargeDetailPane(){
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

    public ChargeDetailPane(ChargeBillReceiptVO chargeBillReceiptVO, BoardController boardController, StackPane mainpane){
        this();
        //this.paymentBillReceiptblService = paymentBillReceiptblService;
        this.chargeBillReceiptVO = chargeBillReceiptVO;
        this.boardController = boardController;
        this.mainpane = mainpane;
        //paymentItemTreeTable.setStockblService(stockblService);

        id.setText(chargeBillReceiptVO.getId());
        client.setText(String.valueOf(chargeBillReceiptVO.getClientID()));
        operator.setText(String.valueOf(chargeBillReceiptVO.getOperatorId()));
        state.setText(chargeBillReceiptVO.getReceiptState().toString());
        sum.setText(String.valueOf(chargeBillReceiptVO.getSum()));
        createtime.setText(chargeBillReceiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(chargeBillReceiptVO.getLastModifiedTime().toString());

        chargeItemTreeTable.setList(chargeBillReceiptVO.getTransferList());
        chargeItemTreeTable.setChargeBillReceiptblService(chargeBillReceiptblService);
        chargeItemTreeTable.setBoardController(boardController);
        chargeItemTreeTable.setMainpane(mainpane);
        // paymentItemTreeTable.setBoardController(boardController);
        //paymentItemTreeTable.setMainpane(mainpane);
    }


    /*public PaymentDetailPane(BoardController boardController, StackPane mainpane){
        this(null,boardController,mainpane);
    }*/


    @FXML
    public void add(){
        //stockListItemTreeTable.addGood(new ListGoodsItemVO("a",1,"a",1,1,"a"));

    }







    @Override
    public void refresh(boolean toSwitch) {

    }
}
