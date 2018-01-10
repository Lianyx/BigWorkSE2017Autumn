package businesslogic.accountbl;

import blService.accountblService.AccountblService;
import dataService.accountDataService.AccountDataService;
import po.AccountPO;
import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Accountbl implements AccountblService{

    AccountDataService accountDataService;

    //public ResultMessage add(AccountListVO accountListVO)throws RemoteException;
    //public ResultMessage delete(int id);
    //public ResultMessage update(AccountListVO accountListVO);
    //public Set<MemberListVO> search(MemberSearchVO memberSearchVO);
    //public MemberVO showDetail(int id);
    //public Set<AccountListVO> getAll();

    public Accountbl()throws NotBoundException,RemoteException,MalformedURLException{
        accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:1099/AccountData");
    }

    public Set<AccountListVO> getAll()throws RemoteException{
       /*ArrayList<AccountListVO> tem = new ArrayList<>();
       tem.add(new AccountListVO(1,"1",1));
       tem.add(new AccountListVO(2,"2",2));
       tem.add(new AccountListVO(3,"3",3));*/

        ArrayList<AccountPO> POList = accountDataService.getAll();
        ArrayList<AccountListVO> VOList = new ArrayList<>();
        for(AccountPO po: POList){
            AccountListVO vo = new AccountListVO(po.getID(),po.getName(),po.getBalance());
            VOList.add(vo);
        }
        return new HashSet<AccountListVO>(VOList);
        //return new HashSet<>(tem);
    }

    /*public ArrayList<AccountListVO> search(String keyword)throws RemoteException{
        ArrayList<AccountPO> POList = accountDataService.selectInEffect(keyword);
        ArrayList<AccountListVO> VOList = new ArrayList<>();
        for(AccountPO po: POList){
            AccountListVO vo = new AccountListVO(po.getID(),po.getName(),po.getBalance());
            VOList.add(vo);
        }
        return VOList;
    }*/

    public ResultMessage add(AccountListVO accountListVO)throws RemoteException{
        AccountPO accountPO = new AccountPO(null,accountListVO.getName(),accountListVO.getBalance());
        return accountDataService.insert(accountPO);
    }

    public ResultMessage delete(int ID)throws RemoteException{
        return accountDataService.delete(ID);
    }

    public ResultMessage update(AccountListVO accountVO)throws RemoteException{
        AccountPO accountPO = new AccountPO(accountVO.getID(),accountVO.getName(),accountVO.getBalance());
        return accountDataService.update(accountPO);
    }

}
