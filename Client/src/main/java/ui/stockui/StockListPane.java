package ui.stockui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.stockblService.StockblService;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.util.*;
import util.ReceiptState;
import vo.receiptVO.StockReceiptListVO;
import vo.StockSearchVO;
import vo.receiptVO.StockReceiptVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StockListPane extends ReceiptListPane<StockReceiptListVO> {

    static Set<StockReceiptListVO> set = new HashSet<>();

    StockblService stockblService;

    SimpleBooleanProperty isPur = new SimpleBooleanProperty();

    SimpleStringProperty match = new SimpleStringProperty("");

    static StockSearchVO stockSearchVO = new StockSearchVO();

    public StockListPane(boolean isPur) throws Exception {
        super("/stockui/stocklistpane.fxml");
        this.stockblService = ServiceFactory_Stub.getService(StockblService.class.getName());
        this.isPur.set(isPur);
        receiptTreeTable = new StockTreeTable();
        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        for (ReceiptState receiptState : ReceiptState.values()) {
            stockSearchVO.getReceiptStates().add(receiptState);
        }
        StockFilterPane slp = new StockFilterPane(filterPopOver, stockSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(slp);
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
            Set<StockReceiptListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getReceiptState().name().toLowerCase().contains(match.get()) ||
                            s.getId().toLowerCase().contains(match.get())||
                            s.getMemberName().toLowerCase().contains(match.get())||
                            s.getStockName().toLowerCase().contains(match.get())
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
        StockReceiptPane stockReceiptPane = new StockReceiptPane(isPur.get());
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
                if ((set = stockblService.search(stockSearchVO, isPur.get())) != null) {
                    System.out.println(set.size());
                    return true;
                }
                return false;
            };
            GetTask<HashSet<StockReceiptListVO>, StockblService> getTask =
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

