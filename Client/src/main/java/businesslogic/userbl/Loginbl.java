package businesslogic.userbl;

import blService.userblService.LoginblService;
import dataService.userdataService.UserDataService;
import util.ResultMessage;

import java.rmi.Naming;

import static ui.util.UserInfomation.url;

public class Loginbl implements LoginblService{

    private UserDataService userDataService;

    public Loginbl() throws Exception{
        userDataService = (UserDataService) Naming.lookup( url+"UserData");
    }

    @Override
    public ResultMessage login(String id, String password) {
        return null;
    }
}
