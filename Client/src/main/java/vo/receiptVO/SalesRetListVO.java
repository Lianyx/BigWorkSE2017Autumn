package vo.receiptVO;

import util.ReceiptState;

import java.time.LocalDateTime;

public class SalesRetListVO extends SalesReceiptListVO<SalesRetListVO>{
    private SalesRetReceiptVO salesRetReceiptVO;

    public SalesRetListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum) {
        super(id, createTime, receiptState, memberName, stockName, sum);
    }

    public SalesRetListVO(SalesRetReceiptVO salesRetReceiptVO) {
        this.salesRetReceiptVO = salesRetReceiptVO;
    }

    @Override
    public SalesRetReceiptVO toVO() {
        return salesRetReceiptVO;
    }
}
