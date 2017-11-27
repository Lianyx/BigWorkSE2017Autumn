package dataService.userdataService;

import po.UserPO;
import util.ResultMessage;

public interface LogindataService {
    public ResultMessage insert(UserPO UserPO);
    public ResultMessage update(UserPO UserPO);
}
