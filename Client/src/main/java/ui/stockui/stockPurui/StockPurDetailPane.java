package ui.stockui.stockPurui;

import blService.checkblService.ReceiptblService;
import blService.stockblService.StockPurblService;
import ui.stockui.StockReceiptPane;
import vo.receiptVO.StockPurReceiptVO;

public class StockPurDetailPane extends StockReceiptPane<StockPurReceiptVO> {


    @Override
    protected Class<? extends ReceiptblService<StockPurReceiptVO>> getServiceClass() {
        return StockPurblService.class;
    }
}
