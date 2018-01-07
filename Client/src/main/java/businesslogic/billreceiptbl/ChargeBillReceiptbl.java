package businesslogic.billreceiptbl;

import blService.billblService.ChargeBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.ChargeBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.BillReceiptSearchVO;
import vo.billReceiptVO.ChargeReceiptVO;
import vo.billReceiptVO.PaymentReceiptListVO;
import vo.billReceiptVO.PaymentReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class ChargeBillReceiptbl extends Receiptbl<ChargeReceiptVO,ChargeBillReceiptPO> {

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeReceiptVO.class, ChargeBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(ChargeReceiptVO receiptVO) throws RemoteException {
        return null;
    }


}
