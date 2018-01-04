package vo;

import util.ReceiptState;

import java.util.ArrayList;
import java.util.HashSet;

public class StockSearchVO {
    HashSet<ReceiptState> receiptStates;

    public StockSearchVO(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }

    public HashSet<ReceiptState> getReceiptStates() {
        return receiptStates;
    }

    public StockSearchVO() {
        receiptStates = new HashSet<>();
    }

    public void setReceiptStates(HashSet<ReceiptState> receiptStates) {
        this.receiptStates = receiptStates;
    }
}
