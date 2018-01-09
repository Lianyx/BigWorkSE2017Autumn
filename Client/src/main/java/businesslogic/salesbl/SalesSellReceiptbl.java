package businesslogic.salesbl;

import blService.salesblService.SalesblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.SalesSellReceiptPO;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SalesSellReceiptbl  extends Receiptbl<SalesSellReceiptVO, SalesSellReceiptPO> implements SalesblService<SalesSellReceiptVO> {

    public SalesSellReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesSellReceiptVO.class, SalesSellReceiptPO.class);
    }




    @Override
    public Set<SalesReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        ArrayList<SalesSellReceiptVO> list = search(respectiveReceiptSearchCondition);
        HashSet<SalesReceiptListVO> set = list.stream().map(SalesSellReceiptVO::toListVO).collect(Collectors.toCollection(HashSet::new));

        return set;
    }

    @Override
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException {
        SalesSellReceiptVO salesSellReceiptVO = new SalesSellReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        if(delete(salesSellReceiptVO)==ResultMessage.SUCCESS)
            return ResultMessage.SUCCESS;
        return ResultMessage.FAIL;
    }

    @Override
    public SalesReceiptVO search(String id, LocalDateTime localDateTime) throws RemoteException {
        SalesSellReceiptVO salesSellReceiptVO = new SalesSellReceiptVO();
        salesSellReceiptVO.setCreateTime(localDateTime);
        salesSellReceiptVO.setId(id);
        return selectByMold(salesSellReceiptVO);
    }

    @Override
    public ResultMessage approve(SalesSellReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}
