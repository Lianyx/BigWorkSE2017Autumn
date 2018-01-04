package blServiceStub.loginblService_Stub;

import blService.userblService.LoginblService;
import javafx.scene.image.Image;
import ui.util.UserInfomation;
import util.ResultMessage;
import util.UserCategory;
import vo.RegisterVO;

public class LoginblService_Stub implements LoginblService {
    @Override
    public ResultMessage login(String id, String password) {
        if(id.equals("admin")&&password.equals("admin")){
            UserInfomation.username = "Lim";
            UserInfomation.usertype = UserCategory.UserManager;
            UserInfomation.userimage = new Image("/default/timg.jpg");
            UserInfomation.userid = 1;


            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAIL;
    }


}
