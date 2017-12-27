package vo.receiptVO;

import javafx.beans.property.SimpleBooleanProperty;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public abstract class ReceiptVO {
    private String id;
    private int operatorId; // 很可能会改成名字之类

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private ReceiptState receiptState;

    private SimpleBooleanProperty selected = new SimpleBooleanProperty(false);

    public ReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.receiptState = receiptState;
    }

    public abstract <T extends ReceiptPO> T toPO();

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

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }
}
