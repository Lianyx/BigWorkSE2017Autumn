package vo;

import util.ReceiptState;

import java.time.LocalDateTime;

public abstract class ReceiptVO {
    private int dayId;
    private int operatorId;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;

    public ReceiptVO(){

    }

    public ReceiptVO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.dayId = dayId;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    public int getId() {
        return dayId;
    }

    public void setId(int dayId) {
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
