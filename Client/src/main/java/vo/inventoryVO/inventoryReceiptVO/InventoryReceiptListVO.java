package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;
import vo.abstractVO.SelectableVO;

import java.io.Serializable;

public class InventoryReceiptListVO extends SelectableVO<InventoryReceiptListVO> implements Serializable{
    private String id;
    private ReceiptState receiptState;
    private String memberName;
    private boolean multiple = true;
    InventoryReceiptType receiptType;

    public InventoryReceiptListVO(String id, ReceiptState receiptState, String memberName, boolean multiple, InventoryReceiptType receiptType) {
        this.id = id;
        this.receiptState = receiptState;
        this.memberName = memberName;
        this.multiple = multiple;
        this.receiptType = receiptType;
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

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public InventoryReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(InventoryReceiptType receiptType) {
        this.receiptType = receiptType;
    }
}
