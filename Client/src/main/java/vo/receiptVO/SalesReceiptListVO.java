package vo.receiptVO;

import util.ReceiptState;
import vo.abstractVO.SelectableVO;

import java.time.LocalDateTime;

public class SalesReceiptListVO extends ReceiptListVO<SalesReceiptListVO>{
    private String id;
    private LocalDateTime createTime;
    private ReceiptState receiptState;
    private String memberName;
    private String stockName;
    private double sum;
    private boolean multiple = true;
    boolean isSell;

    public SalesReceiptListVO() {
    }

    public SalesReceiptListVO(String id, LocalDateTime createTime, ReceiptState receiptState, String memberName, String stockName, double sum, boolean isSell) {
        this.id = id;
        this.createTime = createTime;
        this.receiptState = receiptState;
        this.memberName = memberName;
        this.stockName = stockName;
        this.sum = sum;
        this.isSell = isSell;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }

    @Override
    public <TV extends ReceiptVO> TV toVO() {
        return null;
    }
}