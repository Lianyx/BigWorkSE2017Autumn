package ui.salesui.salesSellui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import ui.myAccountantui.common.MyReceiptDetailPane;
import ui.salesui.SalesReceiptPane;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

public class SalesSellDetailPane extends SalesReceiptPane<SalesSellReceiptVO> {


    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }
}
