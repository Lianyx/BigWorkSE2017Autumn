package ui.salesui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.salesblService.SalesblService;
import businesslogic.salesbl.SalesRetReceiptbl;
import businesslogic.salesbl.SalesSellReceiptbl;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import ui.util.*;
import util.RespectiveReceiptSearchCondition;
import vo.receiptVO.SalesReceiptListVO;

import java.rmi.RemoteException;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SalesListPane extends ReceiptListPane<SalesReceiptListVO> {

    SalesblService salesblService;

    SimpleBooleanProperty isSell = new SimpleBooleanProperty();

    SimpleStringProperty match = new SimpleStringProperty("");

    static RespectiveReceiptSearchCondition respectiveReceiptSearchCondition = new RespectiveReceiptSearchCondition();

    public SalesListPane(boolean isSell) throws Exception {
        super("/stockui/stocklistpane.fxml");
        receiptTreeTable = new SalesTreeTable();

        if(isSell){
            salesblService = new SalesSellReceiptbl();
        }else{
            salesblService = new SalesRetReceiptbl();

        }


        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        FilterPane slp = new FilterPane(filterPopOver, respectiveReceiptSearchCondition);
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
        if (!searchField.getText().equals("")) {
            match.setValue(searchField.getText().toLowerCase());
            Set<SalesReceiptListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getReceiptState().name().contains(match.get()) ||
                            s.getId().contains(match.get())||
                            s.getMemberName().contains(match.get())||
                            s.getStockName().contains(match.get())
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
        SalesReceiptPane salesReceiptPane = new SalesReceiptPane(isSell.get());
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
                try {
                    if(isSell.get())
                    if ((set = (salesblService.searchForList(respectiveReceiptSearchCondition))) != null) {
                        System.out.println(set.size());
                        return true;
                    }
                }catch (RemoteException e){
                    e.printStackTrace();
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
