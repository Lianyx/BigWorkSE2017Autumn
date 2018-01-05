package businesslogic.billreceiptbl;

import blService.billblService.ChargeBillReceiptblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.ChargeBillReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.billReceiptVO.ChargeBillReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChargeBillReceiptbl extends Receiptbl<ChargeBillReceiptVO,ChargeBillReceiptPO> implements ChargeBillReceiptblService{

    public ChargeBillReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(ChargeBillReceiptVO.class, ChargeBillReceiptPO.class);
    }

    @Override
    public ResultMessage approve(ChargeBillReceiptPO receiptPO) throws RemoteException {
        return null;
    }
}
