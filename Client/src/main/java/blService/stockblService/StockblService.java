package blService.stockblService;

import blService.checkblService.ReceiptblService;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;
import vo.receiptVO.StockReceiptListVO;
import vo.receiptVO.StockReceiptVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.Set;

public interface StockblService<T extends StockReceiptVO> extends ReceiptblService<T>{
    public  Set<StockReceiptListVO> searchForList(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException;
    public ResultMessage delete(String id, LocalDateTime localDateTime) throws RemoteException;
    public StockReceiptVO search(String id,LocalDateTime localDateTime) throws RemoteException;

}
