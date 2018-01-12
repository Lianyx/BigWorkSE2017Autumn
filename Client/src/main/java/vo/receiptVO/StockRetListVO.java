package vo.receiptVO;

import util.ReceiptState;

import java.time.LocalDateTime;

public class StockRetListVO extends StockReceiptListVO<StockRetListVO>{
    private StockRetReceiptVO stockRetReceiptVO;
    public StockRetListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum) {
        super(id, createTime, receiptState, memberName, stockName, sum);
    }

    public StockRetListVO(StockRetReceiptVO stockRetReceiptVO){
        this.stockRetReceiptVO = stockRetReceiptVO;
    }

    @Override
    public StockRetReceiptVO toVO() {
        return this.stockRetReceiptVO;
    }
}
