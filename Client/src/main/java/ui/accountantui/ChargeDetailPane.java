package ui.accountantui;

import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import blService.stockblService.StockblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import ui.stockui.StockListItemTreeTable;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

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
       chargeItemTreeTable.add(new TransferItemVO(1,1,"1"));

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
            ChargeDetailPane.SwitchTask task = new ChargeDetailPane.SwitchTask(chargeBillReceiptVO);
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(task.getIntegerProperty()==1){
                        try{
                            id.setText(chargeBillReceiptVO.getId());
                            client.setText(String.valueOf(chargeBillReceiptVO.getClientID()));
                            operator.setText(String.valueOf(chargeBillReceiptVO.getOperatorId()));
                            state.setText(chargeBillReceiptVO.getReceiptState().toString());
                            sum.setText(String.valueOf(chargeBillReceiptVO.getSum()));
                            createtime.setText(chargeBillReceiptVO.getCreateTime().toString());
                            lastmodifiedtime.setText(chargeBillReceiptVO.getLastModifiedTime().toString());
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
        private ChargeBillReceiptVO chargeBillReceiptVO;

        public SwitchTask(ChargeBillReceiptVO chargeBillReceiptVO){
            this.chargeBillReceiptVO = chargeBillReceiptVO;
        }



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        public ChargeBillReceiptVO getChargeBillReceiptVO() {
            return chargeBillReceiptVO;
        }

        public void setChargeBillReceiptVO(ChargeBillReceiptVO chargeBillReceiptVO) {
            this.chargeBillReceiptVO = chargeBillReceiptVO;
        }

        @Override
        protected Boolean call() throws Exception{

            if(chargeBillReceiptVO!=null){
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
