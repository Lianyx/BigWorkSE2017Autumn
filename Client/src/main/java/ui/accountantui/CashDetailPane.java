package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.userui.usermanagerui.BoardController;
import ui.util.Refreshable;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.ChargeBillReceiptVO;

public class CashDetailPane extends Refreshable {
    @FXML
    CashItemTreeTable cashItemTreeTable;

    BoardController boardController;

    //ReceiptblService receiptblService;
    CashBillReceiptblService cashBillReceiptblService;

    CashBillReceiptVO cashBillReceiptVO;

    StackPane mainpane;

    @FXML
    TextField id;
    @FXML
    TextField account;
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

    public CashDetailPane(){
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

    public CashDetailPane(CashBillReceiptVO cashBillReceiptVO, BoardController boardController, StackPane mainpane){
        this();
        //this.paymentBillReceiptblService = paymentBillReceiptblService;
        this.cashBillReceiptVO = cashBillReceiptVO;
        this.boardController = boardController;
        this.mainpane = mainpane;
        //paymentItemTreeTable.setStockblService(stockblService);

        id.setText(cashBillReceiptVO.getId());
        account.setText(String.valueOf(cashBillReceiptVO.getAccountID()));
        operator.setText(String.valueOf(cashBillReceiptVO.getOperatorId()));
        state.setText(cashBillReceiptVO.getReceiptState().toString());
        sum.setText(String.valueOf(cashBillReceiptVO.getTotal()));
        createtime.setText(cashBillReceiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(cashBillReceiptVO.getLastModifiedTime().toString());

        cashItemTreeTable.setList(cashBillReceiptVO.getCashList());
        cashItemTreeTable.setCashBillReceiptblService(cashBillReceiptblService);
        cashItemTreeTable.setBoardController(boardController);
        cashItemTreeTable.setMainpane(mainpane);
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
