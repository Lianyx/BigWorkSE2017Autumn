package businesslogic.userbl;

import blService.userblService.LoginblService;
import dataService.userdataService.LoginDataService;
import dataService.userdataService.UserDataService;
import util.ResultMessage;

import java.rmi.Naming;

import static ui.util.UserInfomation.url;

public class Loginbl implements LoginblService{

    private LoginDataService loginDataService;

    public Loginbl() throws Exception{
        loginDataService = (LoginDataService) Naming.lookup( url+"LoginData");
    }

    @Override
    public ResultMessage login(String id, String password) {
        return login(id,password);
    }
}
