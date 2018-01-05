package blServiceStub.usermanagerblservice_Stub;

import blService.userblService.UserManagerblService;
import javafx.scene.image.Image;
import util.ResultMessage;
import util.UserCategory;
import vo.UserListVO;
import vo.UserSearchVO;
import vo.UserVO;

import java.util.HashSet;
import java.util.Set;

public class UserManagerblService_Stub implements UserManagerblService{

    HashSet<UserListVO> set=new HashSet<>();

    int i=11;

    public UserManagerblService_Stub(){
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
        set.add(new UserListVO(UserVO.getId(),UserVO.getImage(),UserVO.getUsername(),UserVO.getUsertype(),UserVO.getEmail(),UserVO.getPhone()));
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(int id) {
        UserListVO s = null;
        for(UserListVO stockReceiptListVO:set){
            if(stockReceiptListVO.getUserid()==id){
                s = stockReceiptListVO;
            }
        }
        if(s!=null){
            set.remove(s);
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(UserVO UserVO) {
        delete(UserVO.getId());
        add(UserVO);
        return null;
    }

    @Override
    public Set<UserListVO> search(UserSearchVO userSearchVO) {
        HashSet<UserListVO> temp = new HashSet<>();
        for(UserListVO s:set){
                for(UserCategory receiptState:userSearchVO.getUserCategories()){
                    if(s.getUserCategory()==receiptState)
                        temp.add(s);
                }

        }
        return temp;
    }

    @Override
    public UserVO showDetail(int id) {
        return new UserVO(id,new Image("/default/timg.jpg"),"123123123", UserCategory.SalesManager,"","","","gzyz12306@163.com","11111111110","He ie stupid","2017-1-1","admin");
    }

    @Override
    public Set<UserListVO> getAll() {
        return set;
    }

    @Override
    public int getId() {
        i++;
        return i;
    }

}
