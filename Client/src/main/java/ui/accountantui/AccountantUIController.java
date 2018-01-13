package ui.accountantui;

import blService.accountblService.AccountblService;
import blService.billblservice.CashBillReceiptblService;
import blService.billblservice.ChargeBillReceiptblService;
import blService.billblservice.PaymentBillReceiptblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;

import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.managerui.businessConditionui.BusinessConditionPane;
import ui.managerui.businessProgressui.BusinessProgressPane;
import ui.managerui.businessSalesDetail.BusinessSalesListPane;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTopBar;
import ui.myAccountantui.MyCashReceiptListPane;
import ui.myAccountantui.MyChargeReceiptListPane;
import ui.myAccountantui.MyPaymentReceiptListPane;
import ui.util.BoardController;
import ui.util.PaneFactory;
import ui.util.PaneSwitchAnimation;
import ui.util.TopBar;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountantUIController implements Initializable{


    @FXML
    JFXListView<HBox> navigation;


    @FXML
    MyTopBar bar;

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



        BoardController.setBoardController(boardController);
        boardController = MyBoardController.getMyBoardController();
        BoardController.setBoardController(boardController);

        // 这个是不得不set，因为是同时生成的，但是这样很不好，希望可以改掉
        bar.setBoardController(boardController);


       // paymentBillReceiptblService = new PaymentBillReceiptblService_Stub();
//        chargeBillReceiptblService = new ChargeBillReceiptblService_Stub();
//        cashBillReceiptblService = new CashBillReceiptblService_Stub();
        try {

            accountblService = new Accountbl();

            boardController.setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(150),  board));

            AccountListPane tempaccountListPane = new AccountListPane();
            tempaccountListPane.historyAdd = true;
            tempaccountListPane.refresh(false);

            navigation.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
                        try {
                            if (newVal != null) {
                                if (newVal.getId().equals("accountList")) {
                                    System.out.println("账户管理");
                                    AccountListPane accountListPane = new AccountListPane();
                                    accountListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("paymentBillList")) {
                                    System.out.println("付款单");
                                    //PaymentReceiptListPane paymentReceiptListPane = new PaymentReceiptListPane();
                                    MyPaymentReceiptListPane paymentReceiptListPane = new MyPaymentReceiptListPane();
                                    paymentReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("chargeBillList")) {
                                    System.out.println("收款单");
                                    //ChargeReceiptListPane chargeReceiptListPane = new ChargeReceiptListPane();
                                    MyChargeReceiptListPane chargeReceiptListPane = new MyChargeReceiptListPane();
                                    chargeReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("cashBillList")) {
                                    System.out.println("现金费用单");
                                    //CashReceiptListPane cashReceiptListPane = new CashReceiptListPane();
                                    MyCashReceiptListPane cashReceiptListPane =new MyCashReceiptListPane();
                                    cashReceiptListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("salesDetail")) {
                                    System.out.println("销售明细");
                                    BusinessSalesListPane businessSalesListPane = new BusinessSalesListPane();
                                    businessSalesListPane.refresh(true);
                                }
                                else if (newVal.getId().equals("businessProgress")) {
                                    System.out.println("经营历程");
                                    BusinessProgressPane businessProgressPane = new BusinessProgressPane();
                                    businessProgressPane.refresh(true);
                                }
                                else if (newVal.getId().equals("businessCondition")) {
                                    System.out.println("经营情况");
                                    BusinessConditionPane businessConditionPane = new BusinessConditionPane();
                                    businessConditionPane.refresh(true);
                                }
                                else if (newVal.getId().equals("initialList")) {
                                    System.out.println("期初建账");
                                    InitialListPane initialListPane = new InitialListPane();
                                    initialListPane.refresh(true);
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
