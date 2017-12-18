package blService.checkblService;

import util.ResultMessage;
import vo.ReceiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CheckblService {
    ArrayList<ReceiptVO> initCheck() throws RemoteException;
//    public ReceiptVO showDetail(int id);
    ResultMessage approve(ReceiptVO receiptVO) throws RemoteException;
    ResultMessage reject(ReceiptVO receiptVO) throws RemoteException;
    ResultMessage update(ReceiptVO receiptVO) throws RemoteException;
//    public ArrayList<ReceiptVO> search(CheckSearchVO checkSearchVO);
}
