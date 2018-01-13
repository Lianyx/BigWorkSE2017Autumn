package ui.inventoryui.inventoryViewui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryViewblService;
import businesslogic.inventorybl.InventoryViewbl;
import javafx.scene.layout.BorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import vo.inventoryVO.InventoryViewItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.function.Predicate;

public class InventoryViewListPane extends ReceiptListPane<InventoryViewItemVO> {

    InventoryViewblService inventoryViewblService;

    LocalDate startTime;
    LocalDate endTime;

    public InventoryViewListPane() throws Exception {
        super("/inventoryui/inventoryviewui/inventoryviewlistpane.fxml");
        this.inventoryViewblService = new InventoryViewbl();//ServiceFactory_Stub.getService(InventoryViewblService.class.getName());
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

                System.out.println("In View"+startTime.toString());
                System.out.println(endTime.toString());

                try {
                    if ((set = inventoryViewblService.inventoryView(startTime,endTime).getViewList()) != null) {
                        System.out.println(set.size());
                        return true;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotBoundException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
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
            System.exit(1);
            e.printStackTrace();

        }

    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }
}
