package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import blServiceStub.cashbillreceiptblservice_Stub.CashBillReceiptblService_Stub;
import blServiceStub.chargebillreceiptblservice_Stub.ChargeBillReceiptblService_Stub;
import blServiceStub.paymentbillreceiptblservice_Stub.PaymentBillReceiptblService_Stub;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.util.BoardController;
import ui.util.PaneSwitchAnimation;
import ui.util.TopBar;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountantUIController implements Initializable{

    @FXML
    StackPane mainpane;

    @FXML
    AnchorPane mainAnchorPane;

    @FXML
    JFXListView<HBox> navigation;

    @FXML
    TopBar bar;

    @FXML
    StackPane board;

    @FXML
    private BoardController boardController;

    AccountblService accountblService;
    //ReceiptblService paymentBillReceiptblService;
    //ReceiptblService chargeBillReceiptblService;
    //ReceiptblService cashBillReceiptblService;
    PaymentBillReceiptblService paymentBillReceiptblService;
    ChargeBillReceiptblService chargeBillReceiptblService;
    CashBillReceiptblService cashBillReceiptblService;


    @Override
    public void initialize(URL location, ResourceBundle resources){

        bar.setBoardController(boardController);
        BoardController.setBoardController(boardController);
        accountblService = new AccountblService_Stub();
        paymentBillReceiptblService = new PaymentBillReceiptblService_Stub();
        chargeBillReceiptblService = new ChargeBillReceiptblService_Stub();
        cashBillReceiptblService = new CashBillReceiptblService_Stub();
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));

            AccountListPane tempaccountListPane = new AccountListPane(accountblService,boardController,mainpane);
            tempaccountListPane.historyAdd = true;
            tempaccountListPane.refresh(false);

            //board.getChildren().setAll(tempaccountListPane);
            //HistoricalRecord.addPane(tempaccountListPane);
            //boardController.switchTo(tempaccountListPane);

            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("accountList")) {
                                    System.out.println("账户管理");
                                    AccountListPane accountListPane = new AccountListPane(accountblService,boardController,mainpane);
                                    accountListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("paymentBillList")) {
                                    System.out.println("付款单");
                                    PaymentReceiptListPane paymentReceiptListPane = new PaymentReceiptListPane(true);
                                    paymentReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("chargeBillList")) {
                                    System.out.println("收款单");
                                    ChargeReceiptListPane chargeReceiptListPane = new ChargeReceiptListPane(true);
                                    chargeReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("cashBillList")) {
                                    System.out.println("现金费用单");
                                    CashReceiptListPane cashReceiptListPane = new CashReceiptListPane(true);
                                    cashReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("销售明细")) {
                                    System.out.println("销售明细");
                                }
                                else if (newVal.getId().equals("经营历程")) {
                                    System.out.println("经营历程");
                                }
                                else if (newVal.getId().equals("经营情况")) {
                                    System.out.println("经营情况");
                                }
                                else if (newVal.getId().equals("期初建账")) {
                                    System.out.println("期初建账");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
