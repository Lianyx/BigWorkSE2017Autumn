package ui.accountantui;

import blService.billblservice.PaymentBillReceiptblService;
import blService.blServiceFactory.ServiceFactory_Stub;
import blService.salesblService.SalesblService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import ui.salesui.SalesReceiptPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import util.ReceiptState;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;
import vo.receiptVO.SalesReceiptListVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PaymentReceiptListPane extends ReceiptListPane<PaymentReceiptListVO> {

    PaymentBillReceiptblService paymentBillReceiptblService;

    SimpleStringProperty match = new SimpleStringProperty("");

    static BillReceiptSearchVO billReceiptSearchVO = new BillReceiptSearchVO();

    public PaymentReceiptListPane() throws Exception {
        super("/accountantui/billReceiptListPane.fxml");
        this.paymentBillReceiptblService = ServiceFactory_Stub.getService(PaymentBillReceiptblService.class.getName());
        receiptTreeTable = new PaymentReceiptTreeTable();

        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        for (ReceiptState receiptState : ReceiptState.values()) {
            billReceiptSearchVO.getReceiptStates().add(receiptState);
        }

        //SalesFilterPane slp = new SalesFilterPane(filterPopOver, salesSearchVO);
        BillReceiptFilterPane billReceiptFilterPane = new BillReceiptFilterPane(filterPopOver,billReceiptSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(billReceiptFilterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }


    @Override
    public void deleteList() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
    }

    @Override
    public void search() {
        if (searchField.getText() != ""&&searchField.getText() != null) {
            match.setValue(searchField.getText().toLowerCase());
            Set<PaymentReceiptListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getReceiptState().name().toLowerCase().contains(match.get()) ||
                            s.getId().toLowerCase().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }

    @Override
    public void add() {
        PaymentDetailPane paymentDetailPane = new PaymentDetailPane();
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));
            Predicate<Integer> p = (s) -> {
                if ((set = paymentBillReceiptblService.search(billReceiptSearchVO)) != null) {
                    System.out.println(set.size());
                    return true;
                }
                return false;
            };
            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderpane.setBottom(pagination);
                        switchPane(toSwitch);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {

            e.printStackTrace();

        }
    }

}
