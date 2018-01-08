package ui.inventoryui.inventoryViewui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryViewblService;
import javafx.scene.layout.BorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import vo.inventoryVO.InventoryViewItemVO;

import java.util.function.Predicate;

public class InventoryViewListPane extends ReceiptListPane<InventoryViewItemVO> {

    InventoryViewblService inventoryViewblService;

    public InventoryViewListPane() throws Exception {
        super("/inventoryui/inventoryviewui/inventoryviewlistpane.fxml");
        this.inventoryViewblService = ServiceFactory_Stub.getService(InventoryViewblService.class.getName());
        receiptTreeTable = new InventoryViewTreeTable();
        receiptTreeTable.setPrefSize(600,435);
      //  receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
    }

    @Override
    public void deleteList() {

    }

    @Override
    public void search() {

    }

    @Override
    public void add() {

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
                if ((set = inventoryViewblService.inventoryView("","").getViewList()) != null) {
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
            System.exit(1);
            e.printStackTrace();

        }

    }
}
