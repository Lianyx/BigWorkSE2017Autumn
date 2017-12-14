package po;

import util.ReceiptState;
import util.InventoryBillCategory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class InventoryBillPO extends ReceiptPO{
    private String clerkName; // 业务员

    private InventoryBillGoodsItemPO[] goodsList;

    /** 备注*/
    private String comment;
    public InventoryBillPO() {}

    public InventoryBillPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime,
                           ReceiptState receiptState, String clerkName, InventoryBillGoodsItemPO[] goodsList, String comment) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.clerkName = clerkName;
        this.goodsList = goodsList;
        this.comment = comment;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public InventoryBillGoodsItemPO[] getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(InventoryBillGoodsItemPO[] goodsList) {
        this.goodsList = goodsList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "InventoryBillPO{" +
                ", clerkName='" + clerkName + '\'' +
                ", goodsList=" + Arrays.toString(goodsList) +
                ", comment='" + comment + '\'' +
                '}';
    }
}
