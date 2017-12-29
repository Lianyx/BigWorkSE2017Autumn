package blServiceStub.usermanagerblService_Stub;

import blService.userblService.UserManagerblService;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import ui.userui.usermanagerui.UserListPane;
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

        for(UserListVO u:set){
            if(u.getUserid()==id){
                set.remove(u);
            }
        }
        return ResultMessage.SUCCESS;
    }


    public void delete(UserListVO userListVO) {
         set.remove(userListVO);
    }
    @Override
    public ResultMessage delete(ArrayList<UserListVO> list){
        for(UserListVO u:list){
            set.remove(u);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(UserVO userVO) {
        for(UserListVO u:set){
            if(u.getUserid()==userVO.getId()){
                set.remove(u);
            }
        }
        set.add(new UserListVO(userVO.getId(),userVO.getUsername(),userVO.getUsertype(),userVO.getEmail(),userVO.getPhone()));
        return ResultMessage.SUCCESS;

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
        return new UserVO(id,new Image("/default/timg.jpg"),"LimKruscal", UserCategory.SalesManager,"","","","gzyz12306@163.com","11111111110","He ie stupid","2017-1-1","admin");
    }

    @Override
    public Set<UserListVO> getAll() {
        return set;
    }
}
