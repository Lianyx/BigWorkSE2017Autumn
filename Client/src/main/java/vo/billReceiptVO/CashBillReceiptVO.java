package vo.billReceiptVO;

import blService.checkblService.ReceiptblService;
import businesslogic.billreceiptbl.CashBillReceipt;
import javafx.scene.Node;
import po.CashItemPO;
import po.receiptPO.CashBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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

    @Override
    protected String getCodeName() {
        return "";
    }

    public CashBillReceiptPO toPO(){
        return new CashBillReceiptPO();
    }

    @Override
    public ReceiptblService getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return null;
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
