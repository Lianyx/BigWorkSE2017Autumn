package ui.salesui.salesRetui;

import blService.checkblService.ReceiptblService;
import blService.salesblService.SalesRetblService;
import ui.salesui.SalesReceiptPane;
import vo.ListGoodsItemVO;
import vo.billReceiptVO.TransferItemVO;
import vo.receiptVO.SalesReceiptVO;
import vo.receiptVO.SalesRetReceiptVO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    protected void setRedCredit(SalesRetReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setOriginSum(-redCreditVO.getOriginSum());
        ArrayList<ListGoodsItemVO> list = redCreditVO.getItems();
        for(ListGoodsItemVO vo:list){
            vo.setSum(-vo.getSum());
        }
        redCreditVO.setItems(list);
    }
}
