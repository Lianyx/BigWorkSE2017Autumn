package businesslogic.stockbl;

import blService.salesblService.SalesblService;
import blService.stockblService.StockblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.SalesSellReceiptPO;
import po.receiptPO.StockRetReceiptPO;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StockRetReceiptbl extends Receiptbl<StockRetReceiptVO, StockRetReceiptPO> implements StockblService<StockRetReceiptVO> {
    public StockRetReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockRetReceiptVO.class, StockRetReceiptPO.class);
    }

    @Override
    public ResultMessage approve(StockRetReceiptVO receiptVO) throws RemoteException {
        return null;
    }


    @Override
    public Set<StockReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        ArrayList<StockRetReceiptVO> list = search(respectiveReceiptSearchCondition);
//        HashSet<StockReceiptListVO> set = list.stream().map(StockRetReceiptVO::toListVO).collect(Collectors.toCollection(HashSet::new));


//        return set;
        return null;
    }

    @Override
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException {
        StockRetReceiptVO salesSellReceiptVO = new StockRetReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        if(delete(salesSellReceiptVO)==ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        return ResultMessage.FAIL;
    }

    @Override
    public StockReceiptVO search(String id, LocalDateTime localDateTime) throws RemoteException {
        StockRetReceiptVO salesSellReceiptVO = new StockRetReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        return selectByMold(salesSellReceiptVO);
    }
}
