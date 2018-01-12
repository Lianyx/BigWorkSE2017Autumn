package businesslogic.stockbl;

import blService.stockblService.StockRetblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.StockRetReceiptPO;
import util.ResultMessage;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StockRetReceiptbl extends Receiptbl<StockRetReceiptVO, StockRetReceiptPO> implements StockRetblService {
    public StockRetReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockRetReceiptVO.class, StockRetReceiptPO.class);
    }

    @Override
    public ResultMessage approve(StockRetReceiptVO receiptVO) throws RemoteException {
        return null;
    }


}
