package businesslogic.accountbl;

import blService.accountblService.AccountblService;
import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.List;

public class AccountController implements AccountblService{

    private Account account;

    public AccountController(){
        account = new Account();
    }

    @Override
    public List<AccountListVO> showAllAccounts(){
        try{
            return account.showAllAccounts();
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AccountListVO> search(String keyword){
        try{
            return account.search(keyword);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage add(AccountListVO accountListVO){
        try{
            return account.add(accountListVO);
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
