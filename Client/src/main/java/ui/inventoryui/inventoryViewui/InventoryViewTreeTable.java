package ui.inventoryui.inventoryViewui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryViewblService;
import com.jfoenix.controls.JFXTreeTableColumn;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.Pagination;
import ui.util.ReceiptTreeTable;
import ui.util.SearchableStringCell;
import vo.inventoryVO.InventoryViewItemVO;

import java.util.Set;

public class InventoryViewTreeTable extends ReceiptTreeTable<InventoryViewItemVO> {
    private static InventoryViewblService inventoryViewblService;

    public InventoryViewTreeTable() {
        super();
        rowsPerPage = 7;
        inventoryViewblService = ServiceFactory_Stub.getService(InventoryViewblService.class.getName());

        JFXTreeTableColumn<InventoryViewItemVO, String> goodName = new JFXTreeTableColumn("GoodName");
        goodName.setPrefWidth(60);
        columnDecorator.setupCellValueFactory(goodName,s->new ReadOnlyObjectWrapper<>(s.getGoodName()));
        goodName.setCellFactory(t->new SearchableStringCell<>(keyword));

        JFXTreeTableColumn<InventoryViewItemVO, String> goodId = new JFXTreeTableColumn("GoodId");
        goodId.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(goodId,s->new ReadOnlyObjectWrapper<>(s.getGoodId()));

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockInNum = new JFXTreeTableColumn("StockInNum");
        stockInNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockInNum,s->new ReadOnlyObjectWrapper<>(s.getStockInNum()));

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockInMoney = new JFXTreeTableColumn("StockInMoney");
        stockInMoney.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockInMoney,s->new ReadOnlyObjectWrapper<>(s.getStockInMoney()));

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockOutNum = new JFXTreeTableColumn("StockOutNum");
        stockOutNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockOutNum,s->new ReadOnlyObjectWrapper<>(s.getStockOutNum()));

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockOutMoney = new JFXTreeTableColumn("StockOutMoney");
        stockOutMoney.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockOutMoney,s->new ReadOnlyObjectWrapper<>(s.getStockOutMoney()));

        JFXTreeTableColumn<InventoryViewItemVO, Integer> saleNum = new JFXTreeTableColumn("SaleNum");
        saleNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(saleNum,s->new ReadOnlyObjectWrapper<>(s.getSaleNum()));

        JFXTreeTableColumn<InventoryViewItemVO, Double> saleMoney = new JFXTreeTableColumn("SaleMoney");
        saleMoney.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(saleMoney,s->new ReadOnlyObjectWrapper<>(s.getSaleMoney()));

        JFXTreeTableColumn<InventoryViewItemVO, Integer> stockPurNum = new JFXTreeTableColumn("StockPurNum");
        stockPurNum.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockPurNum,s->new ReadOnlyObjectWrapper<>(s.getStockPurNum()));

        JFXTreeTableColumn<InventoryViewItemVO, Double> stockPurMoney = new JFXTreeTableColumn("StockPurMoney");
        stockPurMoney.setPrefWidth(120);
        columnDecorator.setupCellValueFactory(stockPurMoney,s->new ReadOnlyObjectWrapper<>(s.getStockPurMoney()));

        this.getColumns().addAll(goodName,goodId,stockInNum,stockInMoney,stockOutNum,stockOutMoney,saleNum,saleMoney,stockPurNum,stockPurMoney);
        
    }

    public void setViewItem(Set<InventoryViewItemVO> set){observableList.setAll(set);}


    @Override
    public void delete(Pagination p) {

    }
}
