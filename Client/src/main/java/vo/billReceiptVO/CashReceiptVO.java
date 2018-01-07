package vo.billReceiptVO;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import javafx.scene.Node;
import po.CashItemPO;
import po.TransferItemPO;
import po.receiptPO.CashBillReceiptPO;
import po.receiptPO.PaymentBillReceiptPO;
import ui.accountantui.CashDetailPane;
import ui.accountantui.PaymentDetailPane;
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
    public String getCodeName() {
        return "XJFYD";
    }

    @Override
    public CashBillReceiptPO toPO(){
        CashBillReceiptPO result = toReceiptPO(CashBillReceiptPO.class);
        result.setAccountID(accountID);
        CashItemPO temp[] = new CashItemPO[cashList.size()];
        for(int i=0;i<cashList.size();i++){
            temp[i] = cashList.get(i).toPO();
        }
        result.setItemList(temp);
        return result;
    }

    @Override
    public CheckInfo<CashReceiptVO> getService() throws RemoteException, NotBoundException, MalformedURLException {
        return null;
    }

    @Override
    public Node getDetailPane() {
        return new CashDetailPane(true);
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