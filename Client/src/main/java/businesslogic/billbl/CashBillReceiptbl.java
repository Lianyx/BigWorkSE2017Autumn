package businesslogic.billbl;

import businesslogic.checkbl.Receiptbl;
import po.receiptPO.CashBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.CashReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CashBillReceiptbl extends Receiptbl<CashReceiptVO,CashBillReceiptPO>{

    public CashBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(CashReceiptVO.class, CashBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(CashReceiptVO receiptVO) throws RemoteException {
        return null;
    }

}
