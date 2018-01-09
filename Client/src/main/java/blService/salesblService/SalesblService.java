package blService.salesblService;

import blService.checkblService.ReceiptblService;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;
import vo.receiptVO.SalesReceiptListVO;
import vo.receiptVO.SalesReceiptVO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Set;

public interface SalesblService<T extends SalesReceiptVO> extends ReceiptblService<T>{
    public Set<SalesReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException;
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException;
    public SalesReceiptVO search(String id,LocalDateTime localDateTime) throws RemoteException;
}
