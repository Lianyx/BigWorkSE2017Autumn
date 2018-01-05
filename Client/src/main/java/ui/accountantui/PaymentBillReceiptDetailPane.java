package ui.accountantui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.billReceiptVO.PaymentBillReceiptVO;
import javafx.scene.control.TextField;

import javax.xml.soap.Text;
import java.awt.*;

public class PaymentBillReceiptDetailPane extends Refreshable{

    PaymentBillReceiptVO paymentBillReceiptVO;

    BoardController boardController;

    @FXML
    TextField id;
    @FXML
    TextField operator;
    @FXML
    TextField createtime;
    @FXML
    TextField lastmodifiedtime;
    @FXML
    TextField receiptstate;
    @FXML
    TextField clientid;
    @FXML
    TextField sum;
    @FXML
    JFXButton choose;
    @FXML
    JFXButton sure;
    @FXML
    JFXButton modify;

    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    public PaymentBillReceiptDetailPane(PaymentBillReceiptVO paymentBillReceiptVO, BoardController boardController) {
        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/paymentBillReceiptDetailPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        this.paymentBillReceiptVO = paymentBillReceiptVO;
        this.boardController = boardController;

        id.setText(paymentBillReceiptVO.getId());
        operator.setText(String.valueOf(paymentBillReceiptVO.getOperatorId()));
        createtime.setText(paymentBillReceiptVO.getCreateTime().toString());
        lastmodifiedtime.setText(paymentBillReceiptVO.getLastModifiedTime().toString());
        receiptstate.setText(paymentBillReceiptVO.getReceiptState().toString());
        clientid.setText(String.valueOf(paymentBillReceiptVO.getclientID()));
        sum.setText(String.valueOf(paymentBillReceiptVO.getSum()));

        id.disableProperty().bind(modifyState.not());
        operator.disableProperty().bind(modifyState.not());
        createtime.disableProperty().bind(modifyState.not());
        lastmodifiedtime.disableProperty().bind(modifyState.not());
        receiptstate.disableProperty().bind(modifyState.not());
        clientid.disableProperty().bind(modifyState.not());
        sum.disableProperty().bind(modifyState.not());
        sure.visibleProperty().bind(modifyState);


        //imageview.setImage(userListVO.getImage());
    }


    @FXML
    public void modify(){
        modifyState.setValue(!modifyState.getValue());
        if(modifyState.getValue()==true){
            modify.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.valueOf("#DA4CEE"), modify.getBackground() == null ? null : modify.getBackground().getFills().get(0).getRadii(), null)));
        }else{
            modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));

        }
    }

    @Override
    public void refresh(boolean toSwitch){
    }
}
