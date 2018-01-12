package businesslogic.billbl;

import blService.billblservice.ChargeBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.ChargeBillReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.ChargeReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChargeBillReceiptbl extends Receiptbl<ChargeReceiptVO,ChargeBillReceiptPO> implements ChargeBillReceiptblService {

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeReceiptVO.class, ChargeBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(ChargeReceiptVO receiptVO) throws RemoteException {
        return null;
    }


}
