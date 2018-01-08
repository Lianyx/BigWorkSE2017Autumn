package ui.accountantui;

import blService.billblService.CashBillReceiptblService;
import blService.blServiceFactory.ServiceFactory_Stub;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import util.ReceiptState;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.CashReceiptListVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CashReceiptListPane extends ReceiptListPane<CashReceiptListVO> {

    static Set<CashReceiptListVO> set = new HashSet<>();

    CashBillReceiptblService cashBillReceiptblService;

    SimpleBooleanProperty isSell = new SimpleBooleanProperty();

    SimpleStringProperty match = new SimpleStringProperty("");

    static BillReceiptSearchVO billReceiptSearchVO = new BillReceiptSearchVO();

    public CashReceiptListPane(boolean isSell) throws Exception {
        super("/accountantui/billReceiptListPane.fxml");
        this.cashBillReceiptblService = ServiceFactory_Stub.getService(CashBillReceiptblService.class.getName());
        this.isSell.set(isSell);
        receiptTreeTable = new CashReceiptTreeTable();

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
            Set<CashReceiptListVO> hashSet;
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
        CashDetailPane cashDetailPane = new CashDetailPane(isSell.get());
        //SalesReceiptPane salesReceiptPane = new SalesReceiptPane(isSell.get());
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
                if ((set = cashBillReceiptblService.search(billReceiptSearchVO, isSell.get())) != null) {
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
