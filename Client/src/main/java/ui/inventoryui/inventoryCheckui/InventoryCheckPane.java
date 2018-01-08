package ui.inventoryui.inventoryCheckui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryCheckblService;
import blService.inventoryblService.InventoryShowblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.util.*;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;

import java.io.IOException;
import java.util.Set;
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
