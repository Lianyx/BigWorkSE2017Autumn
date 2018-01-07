package ui.accountantui;

import blService.billblService.ChargeBillReceiptblService;
import blService.billblService.PaymentBillReceiptblService;
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
import vo.billReceiptVO.ChargeReceiptListVO;
import vo.billReceiptVO.PaymentReceiptListVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ChargeReceiptListPane extends ReceiptListPane<ChargeReceiptListVO> {

    static Set<ChargeReceiptListVO> set = new HashSet<>();

    ChargeBillReceiptblService chargeBillReceiptblService;

    SimpleBooleanProperty isSell = new SimpleBooleanProperty();

    SimpleStringProperty match = new SimpleStringProperty("");

    static BillReceiptSearchVO billReceiptSearchVO = new BillReceiptSearchVO();

    public ChargeReceiptListPane(boolean isSell) throws Exception {
        super("/accountantui/billReceiptListPane.fxml");
        this.chargeBillReceiptblService = ServiceFactory_Stub.getService(ChargeBillReceiptblService.class.getName());
        this.isSell.set(isSell);
        receiptTreeTable = new ChargeReceiptTreeTable();

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
            Set<ChargeReceiptListVO> hashSet;
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
        ChargeDetailPane chargeDetailPane = new ChargeDetailPane(isSell.get());

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
                if ((set = chargeBillReceiptblService.search(billReceiptSearchVO, isSell.get())) != null) {
                    System.out.println(set.size());
                    return true;
                }
                return false;
            };
            GetTask<HashSet<ChargeReceiptListVO>, ChargeBillReceiptblService> getTask =
                    new GetTask<>(() -> {
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
