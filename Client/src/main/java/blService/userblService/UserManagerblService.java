package blService.userblService;

import bl.util.ResultMessage;
import po.UserPO;
import java.util.ArrayList;

public interface UserManagerblService {
    public void init();
    public void initList();
    public void showUserDetail(UserPO user);
    public ArrayList<UserPO> search(String keyword);
    public ResultMessage add(UserPO user);
    public ResultMessage delete(UserPO user);
    public ResultMessage modify(UserPO user);
}
