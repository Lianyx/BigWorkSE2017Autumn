package blService.accountblService;

import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface AccountblService {
    public List<AccountListVO> showAllAccounts()throws RemoteException;

    public List<AccountListVO> search(String keyword)throws RemoteException;

    public ResultMessage add(AccountListVO vo)throws RemoteException;

    public ResultMessage delete(int ID)throws RemoteException;

    public ResultMessage update(AccountVO accountVO)throws RemoteException;

}
