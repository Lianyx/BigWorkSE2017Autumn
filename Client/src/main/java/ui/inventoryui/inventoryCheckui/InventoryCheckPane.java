package ui.inventoryui.inventoryCheckui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryCheckblService;
import javafx.scene.layout.BorderPane;
import ui.util.*;
import vo.inventoryVO.InventoryCheckItemVO;

import java.util.function.Predicate;

public class InventoryCheckPane extends ReceiptListPane<InventoryCheckItemVO> {

    InventoryCheckblService inventoryCheckblService;

    public InventoryCheckPane() throws Exception {
        super("/inventoryui/inventorycheckpane.fxml");
        this.inventoryCheckblService = ServiceFactory_Stub.getService(InventoryCheckblService.class.getName());
        receiptTreeTable = new InventoryCheckTreeTable();
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
                if ((set = inventoryCheckblService.inventoryCheck().getCheckList()) != null) {
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
