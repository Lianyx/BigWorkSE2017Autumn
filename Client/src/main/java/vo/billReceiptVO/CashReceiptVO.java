package vo.billReceiptVO;

import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.receiptPO.CashBillReceiptPO;
import util.ReceiptState;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class CashReceiptVO extends ReceiptVO{

    private int accountID;
    private double total;
    private List<CashItemVO> cashList;
    boolean isSell;

    public CashReceiptVO(){

    }



    public CashReceiptVO(String id, int operatorId, LocalDateTime createTime, LocalDateTime lastModifiedTime, ReceiptState receiptState, int accountID, double total, List<CashItemVO> cashList,boolean isSell) {
        super(id, operatorId, createTime, lastModifiedTime, receiptState);
        this.accountID = accountID;
        this.total = total;
        this.cashList = cashList;
        this.isSell = isSell;
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

    public List<CashItemVO> getCashList() {
        return cashList;
    }

    public void setCashList(List<CashItemVO> cashList) {
        this.cashList = cashList;
    }

    public boolean isSell() {
        return isSell;
    }

    public void setSell(boolean sell) {
        isSell = sell;
    }
}
