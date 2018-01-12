package businesslogic.stockbl;

import blService.stockblService.StockPurblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.StockPurReceiptPO;
import util.ResultMessage;
import vo.receiptVO.StockPurReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StockPurReceiptbl extends Receiptbl<StockPurReceiptVO, StockPurReceiptPO> implements StockPurblService {
    public StockPurReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockPurReceiptVO.class, StockPurReceiptPO.class);
    }

    @Override
    public ResultMessage approve(StockPurReceiptVO receiptVO) throws RemoteException {
        return null;
    }

}
