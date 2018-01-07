package vo.billReceiptVO;

import blService.blServiceFactory.ServiceFactory;
import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.TransferItemPO;
import po.receiptPO.PaymentBillReceiptPO;
import po.receiptPO.ReceiptPO;
import ui.accountantui.PaymentDetailPane;
import util.ReceiptState;

import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentReceiptVO extends ReceiptVO {


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;
    private boolean isSell;


    public PaymentReceiptVO(){

    }

    public PaymentReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum,boolean isSell) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.clientID = clientID;
        this.transferList = transferList;
        this.sum = sum;
        this.isSell = isSell;
    }

    @Override
    public String getCodeName() {
        return "SKD";
    }

    @Override
    public PaymentBillReceiptPO toPO(){
        PaymentBillReceiptPO result = toReceiptPO(PaymentBillReceiptPO.class);
        result.setClientID(clientID);
        TransferItemPO temp[] = new TransferItemPO[transferList.size()];
        for(int i=0;i<transferList.size();i++){
            temp[i] = transferList.get(i).toPO();
        }
        result.setTransferList(temp);
        return result;
    }

    @Override
    public CheckInfo<PaymentReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return new PaymentDetailPane(true);
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

    public void setSum(double sum)
    {
        this.sum = sum;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }
}


