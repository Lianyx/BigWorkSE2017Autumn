package businesslogic.salesbl;

import blService.salesblService.SalesRetblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.SalesRetReceiptPO;
import util.ResultMessage;
import vo.receiptVO.SalesRetReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SalesRetbl extends Receiptbl<SalesRetReceiptVO, SalesRetReceiptPO> implements SalesRetblService {

    public SalesRetbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesRetReceiptVO.class, SalesRetReceiptPO.class);
    }

    @Override
    public ResultMessage approve(SalesRetReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}

