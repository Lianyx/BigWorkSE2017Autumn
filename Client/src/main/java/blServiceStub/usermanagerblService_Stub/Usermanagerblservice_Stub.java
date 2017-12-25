package blServiceStub.usermanagerblService_Stub;

import blService.userblService.UserManagerblService;
import javafx.collections.ObservableList;
import util.ResultMessage;
import util.UserCategory;
import vo.UserListVO;
import vo.UserSearchVO;
import vo.UserVO;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Usermanagerblservice_Stub implements UserManagerblService{

    TreeSet<UserListVO> set=new TreeSet<>();


    public Usermanagerblservice_Stub(){
        set.add(new UserListVO(10000,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10001,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10002,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10003,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10004,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10005,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10006,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10007,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10008,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10009,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
        set.add(new UserListVO(10010,"LimKruscal", UserCategory.SalesManager,"gzyz12306@163.com","11111111110"));
    }


    @Override
    public ResultMessage add(UserVO UserVO) {


        return ResultMessage.SUCCESS;
    }







    @Override
    public ResultMessage delete(int id) {

        return null;
    }

    @Override
    public ResultMessage delete(ArrayList<Integer> list) {
        return null;
    }

    public void delete(UserListVO userListVO) {
         set.remove(userListVO);
    }


    @Override
    public ResultMessage update(UserVO UserVO) {
        return null;
    }

    @Override
    public Set<UserListVO> search(UserSearchVO userSearchVO) {
        return null;
    }

    @Override
    public Set<UserListVO> search(String keyword) {
        return null;
    }

    @Override
    public UserVO showDetail(int id) {
        return null;
    }

    @Override
    public Set<UserListVO> getAll() {

        return set;
    }
}
