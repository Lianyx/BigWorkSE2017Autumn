package vo.billReceiptVO;

import businesslogic.billreceiptbl.CashBillReceipt;
import po.CashItemPO;
import po.receiptPO.CashBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.time.LocalDateTime;

public class CashBillReceiptVO extends ReceiptVO{

    private int accountID;
    private double total;
    private CashItemVO[] itemList;

    public CashBillReceiptVO(){

    }

    public CashBillReceiptVO(int accountID, double total, CashItemVO[] itemList) {
        this.accountID = accountID;
        this.total = total;
        this.itemList = itemList;
    }

    public CashBillReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int accountID, double total, CashItemVO[] itemList) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.accountID = accountID;
        this.total = total;
        this.itemList = itemList;
    }

    public CashBillReceiptPO toPO(){
        return new CashBillReceiptPO();
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

    public CashItemVO[] getItemList() {
        return itemList;
    }

    public void setItemList(CashItemVO[] itemList) {
        this.itemList = itemList;
    }
}
