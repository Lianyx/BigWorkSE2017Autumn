package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.billblService.CashBillReceiptblService;
import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import blServiceStub.billblservice_Stub.CashblService_Stub;
import blServiceStub.billblservice_Stub.ChargeblService_Stub;
import blServiceStub.billblservice_Stub.PaymentblService_Stub;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    JFXListView<Label> navigation;

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
        accountblService = new AccountblService_Stub();
        paymentBillReceiptblService = new PaymentblService_Stub();
        chargeBillReceiptblService = new ChargeblService_Stub();
        cashBillReceiptblService = new CashblService_Stub();
        try {

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));

            AccountListPane tempaccountListPane = new AccountListPane(accountblService,boardController,mainpane);
            tempaccountListPane.historyAdd = true;
            tempaccountListPane.refresh(false);

            //board.getChildren().setAll(tempaccountListPane);
            //HistoricalRecord.addPane(tempaccountListPane);
            //boardController.switchTo(tempaccountListPane);

            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                new Thread(() -> {
                    Platform.runLater(() -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getText().equals("账户管理")) {
                                    System.out.println("账户管理");
                                    AccountListPane accountListPane = new AccountListPane(accountblService,boardController,mainpane);
                                    //board.getChildren().setAll(accountListPane);
                                    //HistoricalRecord.addPane(accountListPane);
                                    //boardController.switchTo(accountListPane);
                                    accountListPane.refresh(true);
                                }
                                else if (newVal.getText().equals("付款单")) {
                                    System.out.println("付款单");
                                    BillReceiptListPane billReceiptListPane = new BillReceiptListPane(paymentBillReceiptblService,boardController);
                                    billReceiptListPane.setMainpane(mainpane);
                                    //board.getChildren().setAll(billReceiptListPane);
                                    //HistoricalRecord.addPane(billReceiptListPane);
                                    boardController.switchTo(billReceiptListPane);
                                }
                                else if (newVal.getText().equals("收款单")) {
                                    System.out.println("收款单");
                                    BillReceiptListPane billReceiptListPane = new BillReceiptListPane(chargeBillReceiptblService,boardController);
                                    billReceiptListPane.setMainpane(mainpane);
                                    //board.getChildren().setAll(billReceiptListPane);
                                    //HistoricalRecord.addPane(billReceiptListPane);
                                    boardController.switchTo(billReceiptListPane);
                                    //StockblService stockblService= new Stock_Stub();
                                    //StockReceiptPane stockReceiptPane = new StockReceiptPane(boardController,mainpane);
                                    //stockReceiptPane.refresh(true);
                                }
                                else if (newVal.getText().equals("现金费用单")) {
                                    System.out.println("现金费用单");
                                    BillReceiptListPane billReceiptListPane = new BillReceiptListPane(cashBillReceiptblService,boardController);
                                    billReceiptListPane.setMainpane(mainpane);
                                    //board.getChildren().setAll(billReceiptListPane);
                                    //HistoricalRecord.addPane(billReceiptListPane);
                                    boardController.switchTo(billReceiptListPane);
                                }
                                else if (newVal.getText().equals("销售明细")) {
                                    System.out.println("销售明细");
                                }
                                else if (newVal.getText().equals("经营历程")) {
                                    System.out.println("经营历程");
                                }
                                else if (newVal.getText().equals("经营情况")) {
                                    System.out.println("经营情况");
                                }
                                else if (newVal.getText().equals("期初建账")) {
                                    System.out.println("期初建账");
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                }).start();
            });


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
