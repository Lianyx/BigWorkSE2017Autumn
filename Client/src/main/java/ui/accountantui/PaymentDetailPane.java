package ui.accountantui;

import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


import ui.util.Loading;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.AccountListVO;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.billReceiptVO.TransferItemVO;

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

    @FXML
    JFXButton modify;

    @FXML
    JFXButton save;

    @FXML
    MaterialDesignIconView pen;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

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


        id.disableProperty().bind(modifyState.not());
        client.disableProperty().bind(modifyState.not());
        operator.disableProperty().bind(modifyState.not());
        state.setDisable(true);
        createtime.setDisable(true);
        save.visibleProperty().bind(modifyState);


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
    public void modify(){
        modifyState.setValue(!modifyState.getValue());
        if(modifyState.getValue()==true){
            modify.setBackground(new Background(new BackgroundFill(Color.valueOf("#03A9F4"), modify.getBackground().getFills().get(0).getRadii(), null)));
            pen.setFill(Paint.valueOf("#FFFFFF"));
        }else{
            modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));
            pen.setFill(Paint.valueOf("#000000"));
        }
    }

    @FXML
    public void save(){
        PaymentBillReceiptVO paymentBillReceiptVO=new PaymentBillReceiptVO();
        JFXDialog dialog = new JFXDialog(mainpane, new BorderPane(new Loading()), JFXDialog.DialogTransition.CENTER);
        dialog.show();
    }


    @FXML
    public void add(){
        paymentItemTreeTable.add(new TransferItemVO(1,1,"1"));

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
            PaymentDetailPane.SwitchTask task = new PaymentDetailPane.SwitchTask(paymentBillReceiptVO);
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(task.getIntegerProperty()==1){
                        try{
                            id.setText(paymentBillReceiptVO.getId());
                            client.setText(String.valueOf(paymentBillReceiptVO.getclientID()));
                            operator.setText(String.valueOf(paymentBillReceiptVO.getOperatorId()));
                            state.setText(paymentBillReceiptVO.getReceiptState().toString());
                            sum.setText(String.valueOf(paymentBillReceiptVO.getSum()));
                            createtime.setText(paymentBillReceiptVO.getCreateTime().toString());
                            lastmodifiedtime.setText(paymentBillReceiptVO.getLastModifiedTime().toString());
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
        private PaymentBillReceiptVO paymentBillReceiptVO;

        public SwitchTask(PaymentBillReceiptVO paymentBillReceiptVO){
            this.paymentBillReceiptVO = paymentBillReceiptVO;
        }



        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        public PaymentBillReceiptVO getPaymentBillReceiptVO() {
            return paymentBillReceiptVO;
        }

        public void setPaymentBillReceiptVO(PaymentBillReceiptVO paymentBillReceiptVO) {
            this.paymentBillReceiptVO = paymentBillReceiptVO;
        }

        @Override
        protected Boolean call() throws Exception{

            if(paymentBillReceiptVO!=null){
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
