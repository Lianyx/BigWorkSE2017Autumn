package blService.userblService;

import vo.UserSearchVO;
import util.ResultMessage;
import vo.UserListVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.Set;

public interface UserManagerblService {
    public ResultMessage add(UserVO UserVO);
    public ResultMessage delete(int id);

    public ResultMessage delete(ArrayList<UserListVO> list);

    public ResultMessage update(UserVO UserVO);
    public Set<UserListVO> search(UserSearchVO userSearchVO);
    public Set<UserListVO> search(String keyword);
    public UserVO showDetail(int id);
    public Set<UserListVO> getAll();
}
