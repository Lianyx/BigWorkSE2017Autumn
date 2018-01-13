package businesslogic.stockbl;

import blService.stockblService.StockPurblService;
import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import po.receiptPO.StockPurReceiptPO;
import util.ResultMessage;
import vo.receiptVO.StockPurReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class StockPurbl extends Receiptbl<StockPurReceiptVO, StockPurReceiptPO> implements StockPurblService {
    public StockPurbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockPurReceiptVO.class, StockPurReceiptPO.class);
    }

    @Override
    public ResultMessage approve(StockPurReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        new GoodsSalesUpdate().goodsUpdateStockPur(receiptVO.getItems());
        return ResultMessage.SUCCESS;
    }

}
