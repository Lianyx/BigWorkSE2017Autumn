package businesslogic.billbl;

import blService.billblservice.PaymentBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.PaymentBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.PaymentReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PaymentBillReceiptbl extends Receiptbl<PaymentReceiptVO,PaymentBillReceiptPO> implements PaymentBillReceiptblService {

    public PaymentBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(PaymentReceiptVO.class, PaymentBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(PaymentReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}
