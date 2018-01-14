package businesslogic.userbl;

import blService.userblService.LoginblService;
import dataService.userdataService.LoginDataService;
import dataService.userdataService.UserDataService;
import po.UserPO;
import util.ResultMessage;
import util.UserCategory;
import vo.UserVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import static ui.util.UserInfomation.url;

public class Loginbl implements LoginblService{

    private LoginDataService loginDataService;

    public Loginbl() throws RemoteException, NotBoundException, MalformedURLException {
        loginDataService = (LoginDataService) Naming.lookup( url+"LoginData");
    }

    @Override
    public ResultMessage login(String id, String password) throws RemoteException{
        return loginDataService.login(id,password);
    }

    @Override
    public UserVO getCategory(String username)throws RemoteException {
        return new UserVO(loginDataService.getCategory(username));
    }


}
