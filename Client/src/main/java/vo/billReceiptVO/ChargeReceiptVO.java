package vo.billReceiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import businesslogic.checkbl.MyServiceFactory;
import javafx.scene.Node;
import po.TransferItemPO;
import po.receiptPO.ChargeBillReceiptPO;
import po.receiptPO.PaymentBillReceiptPO;
import ui.accountantui.ChargeDetailPane;
import ui.accountantui.PaymentDetailPane;
import util.ReceiptState;
import vo.receiptVO.ReceiptListVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChargeReceiptVO extends ReceiptVO{


    private int clientID;
    private List<TransferItemVO> transferList;
    private double sum;


    public ChargeReceiptVO(){

    }

    public ChargeReceiptVO(ChargeBillReceiptPO chargeBillReceiptPO){
        super(chargeBillReceiptPO);
        this.clientID = chargeBillReceiptPO.getClientId();
        this.sum = chargeBillReceiptPO.getSum();
        List<TransferItemVO> temp = new ArrayList<>();
        if (chargeBillReceiptPO.getTransferList() != null) {
            for (TransferItemPO transferItemPO : chargeBillReceiptPO.getTransferList()) {
                TransferItemVO vo = new TransferItemVO(transferItemPO.getAccountID(), transferItemPO.getSum(), transferItemPO.getComment());
                temp.add(vo);
            }
        }
        this.transferList = temp;
    }


    public ChargeReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int clientID, List<TransferItemVO> transferList, double sum) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);

        this.clientID =clientID;
        this.transferList = transferList;
        this.sum = sum;
    }

    @Override
    public String getCodeName() {
        return "SKD";
    }

    @Override
    public ChargeBillReceiptPO toPO(){
        ChargeBillReceiptPO result = toReceiptPO(ChargeBillReceiptPO.class);
        result.setClientId(clientID);
        TransferItemPO temp[] = new TransferItemPO[transferList.size()];
        for(int i=0;i<transferList.size();i++){
            temp[i] = transferList.get(i).toPO();
        }
        result.setTransferList(temp);
        return result;
    }

    @Override
    public CheckInfo<ChargeReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return MyServiceFactory.getChargeReceiptVOCheckInfo();
    }

    @Override
    public Node getDetailPane() {
        return new ChargeDetailPane();
    }

    @Override
    public <TL extends ReceiptListVO<TL>> TL toListVO() {
        return null;
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
