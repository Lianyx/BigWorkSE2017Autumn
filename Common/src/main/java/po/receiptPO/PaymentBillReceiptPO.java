package po.receiptPO;

import po.TransferItemPO;
import po.receiptPO.ReceiptPO;
import util.BillCategory;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PaymentBillReceiptPO extends ReceiptPO {

    private String clerkName;
    private int clientID;
    private TransferItemPO[] transferList;
    private double sum;

    public PaymentBillReceiptPO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,String clerkName, int clientID, TransferItemPO[] transferList, double sum) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.clerkName = clerkName;
        this.clientID = clientID;
        this.transferList = transferList;
        this.sum = sum;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public TransferItemPO[] getTransferList() {
        return transferList;
    }

    public void setTransferList(TransferItemPO[] transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
