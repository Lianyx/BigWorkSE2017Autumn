package vo.billReceiptVO;

import po.receiptPO.ChargeBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.time.LocalDateTime;
import java.util.List;

public class ChargeBillReceiptVO extends ReceiptVO{


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;

    public ChargeBillReceiptVO(){

    }

    public ChargeBillReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState,  int clientID, List<TransferItemVO> transferList, double sum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);

        this.clientID =clientID;
        this.transferList = transferList;
        this.sum = sum;
    }

    public ChargeBillReceiptPO toPO(){
        return new ChargeBillReceiptPO();
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
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
