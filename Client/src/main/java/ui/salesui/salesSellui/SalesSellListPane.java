package ui.salesui.salesSellui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import blService.salesblService.SalesSellblService;
import ui.salesui.SalesListPane;
import ui.salesui.salesRetui.SalesRetTablePane;
import ui.util.Refreshable;
import vo.receiptVO.SalesRetListVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellListVO;
import vo.receiptVO.SalesSellReceiptVO;

public class SalesSellListPane extends SalesListPane<SalesSellListVO,SalesSellReceiptVO> {


    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new SalesSellTablePane(chosenItems, searchField.textProperty());

    }

    @Override
    protected Class<? extends ReceiptblService<SalesSellReceiptVO>> getServiceClass() {
        return SalesSellblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new SalesSellDetailPane();
    }
}