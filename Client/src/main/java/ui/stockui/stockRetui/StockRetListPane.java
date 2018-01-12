package ui.stockui.stockRetui;

import blService.checkblService.ReceiptblService;
import blService.stockblService.StockRetblService;
import ui.stockui.StockListPane;
import vo.receiptVO.StockRetListVO;
import vo.receiptVO.StockRetReceiptVO;

public class StockRetListPane extends StockListPane<StockRetListVO,StockRetReceiptVO> {


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new StockRetTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<StockRetReceiptVO>> getServiceClass() {
        return StockRetblService.class;
    }

}
