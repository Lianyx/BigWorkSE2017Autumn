package vo.billReceiptVO;

import po.receiptPO.ChargeBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.time.LocalDateTime;

public class ChargeBillReceiptVO extends ReceiptVO{


    private int supplierID;
    private TransferItemVO[] transferList;
    private double sum;

    public ChargeBillReceiptVO(){

    }

    public ChargeBillReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,  int supplierID, TransferItemVO[] transferList, double sum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);

        this.supplierID = supplierID;
        this.transferList = transferList;
        this.sum = sum;
    }

    public ChargeBillReceiptPO toPO(){
        return new ChargeBillReceiptPO();
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
