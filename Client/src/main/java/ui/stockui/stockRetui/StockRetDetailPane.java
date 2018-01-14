package ui.stockui.stockRetui;

import blService.genericblService.ReceiptblService;
import blService.stockblService.StockRetblService;
import ui.stockui.StockReceiptPane;
import vo.ListGoodsItemVO;
import vo.receiptVO.StockRetReceiptVO;

import java.util.ArrayList;

public class StockRetDetailPane extends StockReceiptPane<StockRetReceiptVO> {
    public StockRetDetailPane() {
    }

    public StockRetDetailPane(StockRetReceiptVO receiptVO) {
        super(receiptVO);
    }

    @Override
    protected Class<? extends ReceiptblService<StockRetReceiptVO>> getServiceClass() {
        return StockRetblService.class;
    }
    @Override
    protected void setRedCredit(StockRetReceiptVO redCreditVO) {
        super.setRedCredit(redCreditVO);
        redCreditVO.setSum(-redCreditVO.getSum());
        ArrayList<ListGoodsItemVO> list = redCreditVO.getItems();
        for(ListGoodsItemVO vo:list){
            vo.setSum(-vo.getSum());
        }
        redCreditVO.setItems(list);
    }

}