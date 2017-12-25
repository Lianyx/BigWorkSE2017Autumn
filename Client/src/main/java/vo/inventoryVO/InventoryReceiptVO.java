package vo.inventoryVO;

import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.time.LocalDateTime;

public abstract class InventoryReceiptVO extends ReceiptVO{
    //业务员姓名
    protected String clerkName;
    //备注
    protected String comment;

    public InventoryReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
