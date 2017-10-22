package blService;
import po.UserPO;

import java.util.*;
public interface UserManagerblService {
    public void init();
    public void initList();
    public void showUserDetail(UserPO user);
    public List search(String keyword);
    public boolean add(UserPO user);
    public boolean delete(UserPO user);
    public boolean modify(UserPO user);
}
