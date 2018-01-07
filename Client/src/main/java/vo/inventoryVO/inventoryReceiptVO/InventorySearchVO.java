package vo.inventoryVO.inventoryReceiptVO;

import util.ReceiptState;

import java.util.HashSet;

public class InventorySearchVO {
    HashSet<ReceiptState> receiptStates;

    public InventorySearchVO(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }

    public HashSet<ReceiptState> getReceiptStates() {
        return receiptStates;
    }

    public InventorySearchVO() {
        receiptStates = new HashSet<>();
    }

    public void setReceiptStates(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }
}
