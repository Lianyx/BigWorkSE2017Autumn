package businesslogic.billreceiptbl;

import blService.billblService.PaymentBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.PaymentBillReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.PaymentBillReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PaymentBillReceiptbl extends Receiptbl<PaymentBillReceiptVO,PaymentBillReceiptPO> implements PaymentBillReceiptblService{

    public PaymentBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(PaymentBillReceiptVO.class, PaymentBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(PaymentBillReceiptPO receiptPO) throws RemoteException {
        return null;
    }
}
