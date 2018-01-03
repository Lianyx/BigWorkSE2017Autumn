package vo.inventoryVO.uiReceipt;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleBooleanProperty;
import util.ReceiptState;
import vo.inventoryVO.InventoryReceiptGoodsItemVO;

import java.time.LocalDateTime;
import java.util.Set;

public class InventoryGiftuiVO extends RecursiveTreeObject<InventoryGiftuiVO> implements Comparable<InventoryGiftuiVO> {
    private String id;
    private int operatorId; // 很可能会改成名字之类

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;

    private SimpleBooleanProperty selected=new SimpleBooleanProperty(false);


    //备注
    private  String comment;

    private ReceiptState receiptState;

    Set<GiftuiGoodsItemVO> set;

    public InventoryGiftuiVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, String comment, ReceiptState receiptState, Set<GiftuiGoodsItemVO> set) {
        this.id = id;
        this.operatorId = operatorId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.comment = comment;
        this.receiptState = receiptState;
        this.set = set;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ReceiptState getReceiptState() {
        return receiptState;
    }

    public void setReceiptState(ReceiptState receiptState) {
        this.receiptState = receiptState;
    }

    public Set<GiftuiGoodsItemVO> getSet() {
        return set;
    }

    public void setSet(Set<GiftuiGoodsItemVO> set) {
        this.set = set;
    }

    @Override
    public int compareTo(InventoryGiftuiVO o) {
        return this.id.compareTo(o.id);
    }
}
