package ui.userui.usermanagerui;

import vo.UserListVO;

import javax.jws.soap.SOAPBinding;
import java.util.Set;
import java.util.TreeSet;

public class ChosenItem {
    private static TreeSet<UserListVO> chosenitems=new TreeSet();
    static public void addItem(UserListVO userListVO){
        chosenitems.add(userListVO);
    }
    static public void removeItem(UserListVO userListVO){
        chosenitems.remove(userListVO);
    }
    static public Set<UserListVO> getSet(){return chosenitems;}
}
