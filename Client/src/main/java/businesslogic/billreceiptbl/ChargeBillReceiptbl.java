package businesslogic.billreceiptbl;

import blService.billblService.ChargeBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.ChargeBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.ChargeReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/*public class ChargeBillReceiptbl extends Receiptbl<ChargeReceiptVO,ChargeBillReceiptPO> implements ChargeBillReceiptblService{

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeReceiptVO.class, ChargeBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(ChargeBillReceiptPO receiptPO) throws RemoteException {
        return null;
    }
}*/
