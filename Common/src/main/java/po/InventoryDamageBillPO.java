package po;

import util.ReceiptState;

import java.time.LocalDateTime;

public class InventoryDamageBillPO extends InventoryBillPO {
    public InventoryDamageBillPO() { }

    public InventoryDamageBillPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                                 ReceiptState receiptState, String clerkName, InventoryBillGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState, clerkName, goodsList, comment);
    }
}
