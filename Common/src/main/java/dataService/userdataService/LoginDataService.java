package dataService.userdataService;

import util.ResultMessage;

public interface LoginDataService {
    public ResultMessage login(String username,String password);
}
