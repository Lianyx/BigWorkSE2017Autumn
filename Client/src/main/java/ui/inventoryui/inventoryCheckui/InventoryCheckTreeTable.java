package ui.inventoryui.inventoryCheckui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryCheckblService;
import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import ui.util.*;
import vo.inventoryVO.InventoryCheckItemVO;

import java.util.Set;

public class InventoryCheckTreeTable extends ReceiptTreeTable<InventoryCheckItemVO> {
    private static InventoryCheckblService inventoryCheckblService;

    public InventoryCheckTreeTable() {
        super();
        rowsPerPage = 7;
        this.inventoryCheckblService = ServiceFactory_Stub.getService(InventoryCheckblService.class.getName());

        JFXTreeTableColumn<InventoryCheckItemVO, String> goodName = new JFXTreeTableColumn("GoodName");
        goodName.setPrefWidth(60);
        columnDecorator.setupCellValueFactory(goodName,s->new ReadOnlyObjectWrapper<>(s.getGoodName()));
        goodName.setCellFactory(t->new SearchableStringCell<>(keyword));


        JFXTreeTableColumn<InventoryCheckItemVO, String> goodId = new JFXTreeTableColumn("GoodId");
        goodId.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(goodId,s->new ReadOnlyObjectWrapper<>(s.getGoodId()));

        JFXTreeTableColumn<InventoryCheckItemVO, Integer> inventoryNum = new JFXTreeTableColumn("Num");
        inventoryNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(inventoryNum,s->new ReadOnlyObjectWrapper<>(s.getInventoryNum()));

        JFXTreeTableColumn<InventoryCheckItemVO, Double> avePrice = new JFXTreeTableColumn("Price");
        avePrice.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(avePrice,s->new ReadOnlyObjectWrapper<>(s.getAvePrice()));

        JFXTreeTableColumn<InventoryCheckItemVO, String> stockOutDate = new JFXTreeTableColumn("StockOutDate");
        stockOutDate.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockOutDate,s->new ReadOnlyObjectWrapper<>(s.getStockOutDate()));

        JFXTreeTableColumn<InventoryCheckItemVO, String> batch = new JFXTreeTableColumn("Batch");
        batch.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(batch,s->new ReadOnlyObjectWrapper<>(s.getBatch()));

        JFXTreeTableColumn<InventoryCheckItemVO, String> batchNum = new JFXTreeTableColumn("BatchNum");
        batchNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(batchNum,s->new ReadOnlyObjectWrapper<>(s.getBatchNum()));

        this.getColumns().addAll(goodName,goodId,inventoryNum,avePrice,stockOutDate,batch,batchNum);
    }

    public void setCheckItem(Set<InventoryCheckItemVO> set){observableList.setAll(set);}

    @Override
    public void delete(Pagination p) {

    }
}
