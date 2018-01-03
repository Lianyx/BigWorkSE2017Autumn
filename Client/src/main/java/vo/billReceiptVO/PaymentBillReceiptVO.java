package vo.billReceiptVO;

import po.receiptPO.PaymentBillReceiptPO;
import util.ReceiptState;

import vo.receiptVO.ReceiptVO;

import java.time.LocalDateTime;
import java.util.List;

public class PaymentBillReceiptVO extends ReceiptVO {


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;

    public PaymentBillReceiptVO(){

    }

    public PaymentBillReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,int clientID, List<TransferItemVO> transferList, double sum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.clientID = clientID;
        this.transferList = transferList;
        this.sum = sum;
    }

    public PaymentBillReceiptPO toPO(){
        return new PaymentBillReceiptPO();
    }

    public int getclientID() {
        return clientID;
    }

    public void setclientID(int clientID) {
        this.clientID = clientID;
    }

    public List<TransferItemVO> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<TransferItemVO> transferList) {
        this.transferList = transferList;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

}


