package ui.accountantui;

import blService.checkblService.ReceiptblService;
import ui.common.MyReceiptListPane;
import ui.util.Refreshable;
import vo.billReceiptVO.ChargeReceiptListVO;
import vo.billReceiptVO.ChargeReceiptVO;

public class MyChargeReceiptListPane extends MyReceiptListPane<ChargeReceiptListVO, ChargeReceiptVO> {
    @Override
    protected void initiateTreeTable() {
        receiptListTreeTable = new MyChargeTablePane(chosenItems, searchField.textProperty());
    }

    @Override
    protected Class<? extends ReceiptblService<ChargeReceiptVO>> getServiceClass() {
        return blService.billblservice.ChargeBillReceiptblService.class;
    }

    @Override
    protected Refreshable getNewDetailPane() {
        return new MyChargeDetailPane();
    }
}
