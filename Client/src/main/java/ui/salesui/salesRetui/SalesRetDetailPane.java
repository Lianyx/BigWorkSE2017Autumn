package ui.salesui.salesRetui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import ui.salesui.SalesReceiptPane;
import vo.receiptVO.SalesRetReceiptVO;

public class SalesRetDetailPane extends SalesReceiptPane<SalesRetReceiptVO> {
    public SalesRetDetailPane() {
    }

    public SalesRetDetailPane(SalesRetReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected Class<? extends ReceiptblService<SalesRetReceiptVO>> getServiceClass() {
        return SalesRetblService.class;
    }
}
