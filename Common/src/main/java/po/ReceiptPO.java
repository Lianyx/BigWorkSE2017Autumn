package po;

import util.ReceiptState;

import java.time.LocalDateTime;

public abstract class ReceiptPO {
    // TODO 不确定要不要有一个ReceiptType
    private int dayId;
    private int operatorId;
    // TODO 还是用的id，没有用String operatorName。但是下面业务员用的String，因为业务员不一定是系统里的吧？

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;

    public ReceiptPO() {
    }

    public ReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.dayId = dayId;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }
}
