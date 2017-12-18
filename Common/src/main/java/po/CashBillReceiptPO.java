package po;

import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CashBillReceiptPO extends ReceiptPO {

    private String clerkName;
    private int accountID;
    private double total;
    private CashItemPO[] itemList;

    public CashBillReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, String clerkName,int accountID, double total, CashItemPO[] itemList) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.clerkName  =clerkName;
        this.accountID = accountID;
        this.total = total;
        this.itemList = itemList;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CashItemPO[] getItemList() {
        return itemList;
    }

    public void setItemList(CashItemPO[] itemList) {
        this.itemList = itemList;
    }
}
