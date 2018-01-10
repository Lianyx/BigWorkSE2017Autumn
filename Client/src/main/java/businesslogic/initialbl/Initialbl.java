package businesslogic.initialbl;

import blService.initialblservice.InitialblService;
import dataService.initialDataService.InitialDataService;
import po.AccountInitialPO.InitialAccountPO;
import po.AccountPO;
import vo.AccountVO;
import vo.MemberVO;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Initialbl implements InitialblService{

    InitialDataService initialDataService;

    public  Initialbl()throws RemoteException,NotBoundException,MalformedURLException{
        initialDataService = (InitialDataService) Naming.lookup("rmi://localhost:1099/InitialData");
    }

    @Override
    public void initial(String year)throws RemoteException{
        initialDataService.initial(year);
    }

    @Override
    public ArrayList<AccountVO> showAccount(String year)throws RemoteException{

        ArrayList<InitialAccountPO> temp = initialDataService.showAccount(year);
        ArrayList<AccountVO> result = new ArrayList<>();
        for(InitialAccountPO po:temp){
            result.add(new AccountVO(po.getID(),po.getName(),po.getBalance()));
        }
        return result;

    }

    @Override
    public ArrayList<MemberVO> showMember(String year)throws RemoteException{
        return null;
    }


    @Override
    public ArrayList<GoodsVO> showGoods(String year)throws RemoteException{
        return null;
    }







}
