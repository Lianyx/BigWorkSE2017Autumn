package po;

import util.ReceiptState;

import java.time.LocalDateTime;

public class InventoryGiftBillPO extends InventoryBillPO {
    public InventoryGiftBillPO() {
    }

    public InventoryGiftBillPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                               ReceiptState receiptState, String clerkName, InventoryBillGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clerkName, goodsList, comment);
    }
}
