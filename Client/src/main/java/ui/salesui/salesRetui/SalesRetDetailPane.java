package ui.salesui.salesRetui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import ui.salesui.SalesReceiptPane;
import vo.receiptVO.SalesRetReceiptVO;

public class SalesRetDetailPane extends SalesReceiptPane<SalesRetReceiptVO> {


    @Override
    protected Class<? extends ReceiptblService<SalesRetReceiptVO>> getServiceClass() {
        return SalesRetblService.class;
    }
}
