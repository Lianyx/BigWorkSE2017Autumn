package blService.checkblService;

import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface CheckblService {
    ArrayList<ReceiptVO> initCheck() throws RemoteException;
//    public receiptVO showDetail(int id);
    ResultMessage approve(ReceiptVO receiptVO) throws RemoteException;
    ResultMessage reject(ReceiptVO receiptVO) throws RemoteException;
    ResultMessage update(ReceiptVO receiptVO) throws RemoteException;
//    public ArrayList<receiptVO> search(CheckSearchVO checkSearchVO);
}
