package ui.stockui.stockRetui;

import blService.checkblService.ReceiptblService;
import blService.stockblService.StockRetblService;
import ui.stockui.StockReceiptPane;
import vo.receiptVO.StockRetReceiptVO;

public class StockRetDetailPane extends StockReceiptPane<StockRetReceiptVO> {


    @Override
    protected Class<? extends ReceiptblService<StockRetReceiptVO>> getServiceClass() {
        return StockRetblService.class;
    }
}