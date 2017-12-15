package po;

import util.ReceiptState;

import java.time.LocalDateTime;

public class InventoryWarningReceiptPO extends InventoryReceiptPO {
    public InventoryWarningReceiptPO() {
    }

    public InventoryWarningReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                     ReceiptState receiptState, String clerkName, InventoryReceiptGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clerkName, goodsList, comment);
    }
}
