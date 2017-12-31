package vo;

import ui.userui.usermanagerui.UserListPane;
import util.UserCategory;

public class UserSearchVO {
    UserCategory userCategory;

    public UserSearchVO(){}

    public UserSearchVO(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }
}
