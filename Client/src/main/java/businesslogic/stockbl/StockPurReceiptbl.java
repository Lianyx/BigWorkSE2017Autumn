package businesslogic.stockbl;

import blService.stockblService.StockblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.StockPurReceiptPO;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.SalesSellReceiptVO;
import vo.receiptVO.StockPurReceiptVO;
import vo.receiptVO.StockReceiptListVO;
import vo.receiptVO.StockReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class StockPurReceiptbl extends Receiptbl<StockPurReceiptVO, StockPurReceiptPO> implements StockblService<StockPurReceiptVO> {
    public StockPurReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(StockPurReceiptVO.class, StockPurReceiptPO.class);
    }

    @Override
    public ResultMessage approve(StockPurReceiptVO receiptVO) throws RemoteException {
        return null;
    }


    @Override
    public Set<StockReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
//        ArrayList<StockPurReceiptVO> list = search(respectiveReceiptSearchCondition);
//        HashSet<StockReceiptListVO> set = list.stream().map(StockPurReceiptVO::toListVO).collect(Collectors.toCollection(HashSet::new));
//
//        return set;
        return null;
    }

    @Override
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException {
        StockPurReceiptVO salesSellReceiptVO = new StockPurReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        if(delete(salesSellReceiptVO)==ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        return ResultMessage.FAIL;
    }

    @Override
    public StockReceiptVO search(String id, LocalDateTime localDateTime) throws RemoteException {
        StockPurReceiptVO salesSellReceiptVO = new StockPurReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        return selectByMold(salesSellReceiptVO);
    }
}
