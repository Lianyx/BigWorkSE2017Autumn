package businesslogic.accountbl;

import com.sun.org.apache.regexp.internal.RE;
import dataService.accountDataService.AccountDataService;
import po.AccountPO;
import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import javax.xml.transform.Result;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private AccountDataService accountDataService;

    private AccountPO accountPO;

    public Account(){
        try {
            accountDataService = (AccountDataService) Naming.lookup("rmi://localhost:1099/AccountDataService");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public List<AccountListVO> showAllAccounts()throws RemoteException{
        List<AccountPO> POList = accountDataService.getAll();
        List<AccountListVO> VOList = new ArrayList<>();
        for(AccountPO po: POList){
            AccountListVO vo = new AccountListVO(po.getID(),po.getName(),po.getBalance());
            VOList.add(vo);
        }
        return VOList;
    }

    public List<AccountListVO> search(String keyword)throws RemoteException{
        List<AccountPO> POList = accountDataService.selectInEffect(keyword);
        List<AccountListVO> VOList = new ArrayList<>();
        for(AccountPO po: POList){
            AccountListVO vo = new AccountListVO(po.getID(),po.getName(),po.getBalance());
            VOList.add(vo);
        }
        return VOList;
    }

    public ResultMessage add(AccountVO accountVO)throws RemoteException{
        accountPO = new AccountPO(null,accountVO.getName(),accountVO.getBalance());
        return accountDataService.insert(accountPO);
    }

    public ResultMessage delete(int ID)throws RemoteException{
        return accountDataService.delete(ID);
    }

    public ResultMessage update(AccountVO accountVO)throws RemoteException{
        accountPO = new AccountPO(accountVO.getID(),accountVO.getName(),accountVO.getBalance());
        return accountDataService.update(accountPO);
    }
}



