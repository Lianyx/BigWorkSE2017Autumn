package vo;

import util.ReceiptState;

import java.util.HashSet;

public class SalesSearchVO {
    HashSet<ReceiptState> receiptStates;

    public SalesSearchVO(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }

    public HashSet<ReceiptState> getReceiptStates() {
        return receiptStates;
    }

    public SalesSearchVO() {
        receiptStates = new HashSet<>();
    }

    public void setReceiptStates(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }
}
