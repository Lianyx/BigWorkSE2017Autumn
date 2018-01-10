package businesslogic.billbl;

import blService.billblservice.PaymentBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.PaymentBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class PaymentBillReceiptbl extends Receiptbl<PaymentReceiptVO,PaymentBillReceiptPO> implements PaymentBillReceiptblService {

    public PaymentBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(PaymentReceiptVO.class, PaymentBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(PaymentReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}
