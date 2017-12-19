package blServiceStub.loginblService_Stub;

import blService.userblService.LoginService;
import javafx.scene.image.Image;
import ui.util.UserInfomation;
import util.ResultMessage;
import util.UserCategory;
import vo.RegisterVO;

public class LoginblService_Stub implements LoginService {
    @Override
    public ResultMessage login(String id, String password) {
        if(id.equals("admin")&&password.equals("admin")){
            UserInfomation.username = "Lim";
            UserInfomation.usertype = UserCategory.UserManager;
            UserInfomation.userimage = new Image("/default/timg.jpg");



            return ResultMessage.SUCCESS;
        }
        return ResultMessage.FAIL;
    }

    @Override
    public ResultMessage register(RegisterVO registerVO) {
        return null;
    }

    @Override
    public ResultMessage findPassword(String username) {
        return null;
    }

    @Override
    public ResultMessage checkAnswer(String answer1, String answer2, String answer3) {
        return null;
    }

    @Override
    public ResultMessage setPassword(String password1, String password2) {
        return null;
    }
}
