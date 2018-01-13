package ui.inventoryui.inventoryCheckui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryCheckblService;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import jxl.write.WriteException;
import ui.common.mixer.ExcelExportableMixer;
import ui.util.*;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class InventoryCheckPane extends ReceiptListPane<InventoryCheckItemVO> implements ExcelExportableMixer {

    private InventoryCheckblService inventoryCheckblService;

    private InventoryCheckVO inventoryCheckVO;

    public InventoryCheckPane() throws Exception {
        super("/inventoryui/inventorycheckpane.fxml");
        this.inventoryCheckblService = ServiceFactory_Stub.getService(InventoryCheckblService.class.getName());
        inventoryCheckVO = inventoryCheckblService.inventoryCheck();
        receiptTreeTable = new InventoryCheckTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        //  receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));

    }



    @Override
    public String getExcelName() {
        return "库存盘点";
    }

    @Override
    public void writeSheet() throws WriteException {
        int i = 0;
        myAddCell(i++,0,"商品名");
        myAddCell(i++,0,"商品编号");
        myAddCell(i++,0,"库存数量");
        myAddCell(i++,0,"售价");
        myAddCell(i++,0,"出厂日期");
        myAddCell(i++,0,"批次");
        myAddCell(i++,0,"批号");

        List<InventoryCheckItemVO> list = new ArrayList<>(inventoryCheckVO.getCheckList());

        for (int j = 0; j < list.size(); j++) {
            myAddCell(0,j+1,list.get(j).getGoodName());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(1,j+1,list.get(j).getGoodId());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(2,j+1,list.get(j).getInventoryNum());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(3,j+1,list.get(j).getAvePrice());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(4,j+1,list.get(j).getStockOutDate());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(5,j+1,list.get(j).getBatch());
        }

        for (int j = 0; j < list.size(); j++) {
            myAddCell(6,j+1,list.get(j).getBatchNum());
        }



    }

    @FXML
    public void exportExcel(){
        exportExcelMixer();
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

    @Override
    public void add() {

    }

    @Override
    public void deleteList() {

    }

    @Override
    public void search() {

    }
}
