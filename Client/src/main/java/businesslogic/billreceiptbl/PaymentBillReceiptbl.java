package businesslogic.billreceiptbl;

import blService.billblService.PaymentBillReceiptblService;
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

    @Override
    public ResultMessage delete(String id){
        return ResultMessage.SUCCESS;
    }

    public ResultMessage update(PaymentReceiptVO paymentReceiptVO){
        return ResultMessage.SUCCESS;
    }

    public int getDayId(){
        return 1;
    }
    public ResultMessage add(PaymentReceiptVO paymentReceiptVO){
        return ResultMessage.SUCCESS;
    }

    public Set<PaymentReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO, boolean isPur){
        return null;
    }
    public PaymentReceiptVO showDetail(String id){
        return null;
    }
    public Set<PaymentReceiptListVO> getALL(boolean isPur){
        return null;
    }
}
