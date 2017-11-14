package dataService.userdataService;

import po.UserPO;
import util.ResultMessage;
import vo.UserListVO;
import vo.UserSearchVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.Set;

public interface UserdataService {
    public ResultMessage insert(UserPO UserPO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);

    public ResultMessage update(UserPO UserPO);
    public ArrayList<UserPO> select(UserSearchVO userSearchVO);
    public ArrayList<UserPO> select(String keyword);
    public UserPO showDetail(int id);
    public ArrayList<UserPO> getAll();


}