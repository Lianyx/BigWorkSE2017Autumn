package blService.initialblservice;

import vo.AccountVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InitialblService {

    public void initial(String year)throws RemoteException;

    public ArrayList<AccountVO> showAccount(String year)throws RemoteException;

    public ArrayList<MemberVO> showMember(String year)throws RemoteException;

    public ArrayList<GoodsVO> showGoods(String year)throws RemoteException;



}
