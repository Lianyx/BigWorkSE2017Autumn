package blService.userblService;

import util.RespectiveReceiptSearchCondition;
import util.UserSearchCondition;
import vo.UserSearchVO;
import util.ResultMessage;
import vo.UserListVO;
import vo.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;

public interface UserManagerblService {
    public ResultMessage insert(UserVO UserVO) throws RemoteException;
    public ResultMessage delete(int id) throws RemoteException;
    public ResultMessage update(UserVO UserVO) throws RemoteException;
    public Set<UserListVO> search(UserSearchCondition userSearchCondition) throws RemoteException;
    public UserVO showDetail(int id) throws RemoteException;
    public int getId() throws RemoteException;

}
