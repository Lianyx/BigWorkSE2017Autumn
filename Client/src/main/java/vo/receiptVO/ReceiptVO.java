package vo.receiptVO;

import util.ReceiptState;

import java.time.LocalDateTime;

public abstract class ReceiptVO {
    private String id;
    private int operatorId; // 很可能会改成名字之类

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;

    public ReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
