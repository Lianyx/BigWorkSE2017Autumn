package businesslogic.salesbl;

import blService.salesblService.SalesblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.SalesRetReceiptPO;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesReceiptVO;
import vo.receiptVO.SalesRetReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SalesRetReceiptbl extends Receiptbl<SalesRetReceiptVO, SalesRetReceiptPO> implements SalesblService<SalesRetReceiptVO> {

    public SalesRetReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(SalesRetReceiptVO.class, SalesRetReceiptPO.class);
    }




    @Override
    public Set<SalesReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        ArrayList<SalesRetReceiptVO> list = search(respectiveReceiptSearchCondition);
        HashSet<SalesReceiptListVO> set = list.stream().map(SalesRetReceiptVO::toListVO).collect(Collectors.toCollection(HashSet::new));
        return set;
    }

    @Override
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException {
        SalesRetReceiptVO salesRetReceiptVO = new SalesRetReceiptVO();
        salesRetReceiptVO.setCreateTime(localDateTime);
        salesRetReceiptVO.setId(id);
        if(delete(salesRetReceiptVO)==ResultMessage.SUCCESS)
        return ResultMessage.SUCCESS;
        return ResultMessage.FAIL;
    }

    @Override
    public SalesReceiptVO search(String id, LocalDateTime localDateTime) throws RemoteException {
        SalesRetReceiptVO salesRetReceiptVO = new SalesRetReceiptVO();
        salesRetReceiptVO.setCreateTime(localDateTime);
        salesRetReceiptVO.setId(id);
        return selectByMold(salesRetReceiptVO);
    }

    @Override
    public ResultMessage approve(SalesRetReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}

