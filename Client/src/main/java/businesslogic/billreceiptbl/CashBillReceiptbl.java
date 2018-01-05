package businesslogic.billreceiptbl;

import blService.billblService.CashBillReceiptblService;
import blService.checkblService.ReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.CashBillReceiptPO;
import po.receiptPO.PaymentBillReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.CashBillReceiptVO;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class CashBillReceiptbl extends Receiptbl<CashBillReceiptVO,CashBillReceiptPO> implements CashBillReceiptblService{

    public CashBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(CashBillReceiptVO.class, CashBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(CashBillReceiptPO receiptPO) throws RemoteException {
        return null;
    }

}
