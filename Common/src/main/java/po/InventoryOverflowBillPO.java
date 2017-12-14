package po;

import util.ReceiptState;

import java.time.LocalDateTime;

public class InventoryOverflowBillPO extends InventoryBillPO {
    public InventoryOverflowBillPO() {
    }

    public InventoryOverflowBillPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                   ReceiptState receiptState, String clerkName, InventoryBillGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clerkName, goodsList, comment);
    }
}
