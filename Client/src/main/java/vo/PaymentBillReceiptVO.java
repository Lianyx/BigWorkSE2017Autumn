package vo;

import po.TransferItemPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;

import java.time.LocalDateTime;

public class PaymentBillReceiptVO extends ReceiptVO {
    private int supplierID;
    private TransferItemVO[] transferList;
    private double sum;

    public PaymentBillReceiptVO(){

    }



    public PaymentBillReceiptVO(int dayId, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int supplierID, TransferItemVO[] transferList, double sum) {
        super(dayId, operatorId, createTime, lastModifiedTime, receiptState);
        this.supplierID = supplierID;
        this.transferList = transferList;
        this.sum = sum;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public TransferItemVO[] getTransferList() {
        return transferList;
    }

    public void setTransferList(TransferItemVO[] transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}


