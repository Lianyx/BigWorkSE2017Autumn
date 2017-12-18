package businesslogic.accountbl;

import blService.accountblService.AccountblService;
import util.ResultMessage;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.List;

public class AccountController implements AccountblService{

    private Account account;

    public AccountController(){
        account = new Account();
    }

    @Override
    public List<AccountVO> showAllAccounts(){
        try{
            return account.showAllAccounts();
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountVO> search(String keyword){
        try{
            return account.search(keyword);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage add(AccountVO accountVO){
        try{
            return account.add(accountVO);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage delete(int ID){
        try{
            return account.delete(ID);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage update(AccountVO accountVO){
        try{
            return account.update(accountVO);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

}
