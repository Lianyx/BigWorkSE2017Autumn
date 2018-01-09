package businesslogic.userbl;

import blService.userblService.UserManagerblService;
import dataService.userdataService.UserDataService;
import util.ResultMessage;
import util.UserSearchCondition;
import vo.UserListVO;
import vo.UserVO;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Set;
import java.util.stream.Collectors;

import static ui.util.UserInfomation.url;

public class Userbl implements UserManagerblService {
    private UserDataService userDataService;

    public Userbl() throws Exception{
        System.out.println(url+"UserData");
        userDataService = (UserDataService) Naming.lookup( url+"UserData");
    }

    @Override
    public ResultMessage insert(UserVO UserVO) throws RemoteException{
        return  userDataService.insert(UserVO.toPO());
    }

    @Override
    public ResultMessage delete(int id) throws RemoteException{
        return userDataService.delete(id);
    }

    @Override
    public ResultMessage update(UserVO UserVO) throws RemoteException{
        return userDataService.update(UserVO.toPO());
    }

    @Override
    public Set<UserListVO> search(UserSearchCondition userSearchCondition) throws RemoteException{
        return userDataService.search(userSearchCondition).stream().map(t->{return (new UserVO(t)).toListVO();}).collect(Collectors.toSet());
    }

    @Override
    public UserVO showDetail(int id) throws RemoteException{
        return new UserVO(userDataService.showDetail(id));
    }

    @Override
    public int getId() throws RemoteException{
        return userDataService.getId();
    }
}
