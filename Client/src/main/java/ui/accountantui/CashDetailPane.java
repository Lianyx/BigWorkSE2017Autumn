package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.CashItemVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;

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
        cashItemTreeTable.add(new CashItemVO("1",1,"1"));

    }







    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            boardController.setAll(this);
        }
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try{
            CashDetailPane.SwitchTask task = new CashDetailPane.SwitchTask(cashBillReceiptVO);
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(task.getIntegerProperty()==1){
                        try{
                            id.setText(cashBillReceiptVO.getId());
                            account.setText(String.valueOf(cashBillReceiptVO.getAccountID()));
                            operator.setText(String.valueOf(cashBillReceiptVO.getOperatorId()));
                            state.setText(cashBillReceiptVO.getReceiptState().toString());
                            sum.setText(String.valueOf(cashBillReceiptVO.getTotal()));
                            createtime.setText(cashBillReceiptVO.getCreateTime().toString());
                            lastmodifiedtime.setText(cashBillReceiptVO.getLastModifiedTime().toString());
                            switchPane(toSwitch);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if(task.getIntegerProperty()==0) {
                        try {
                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Accept");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane,jfxDialogLayout,JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e->{
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e->{
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button,re);
                            dialog.show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            new Thread(task).start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class SwitchTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);
        private CashBillReceiptVO cashBillReceiptVO;

        public SwitchTask(CashBillReceiptVO cashBillReceiptVO){
            this.cashBillReceiptVO = cashBillReceiptVO;
        }



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        public CashBillReceiptVO getCashBillReceiptVO() {
            return cashBillReceiptVO;
        }

        public void setCashBillReceiptVO(CashBillReceiptVO cashBillReceiptVO) {
            this.cashBillReceiptVO = cashBillReceiptVO;
        }

        @Override
        protected Boolean call() throws Exception{

            if(cashBillReceiptVO!=null){
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            }else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }
}
