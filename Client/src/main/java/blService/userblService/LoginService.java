package blService.userblService;

import util.ResultMessage;
import vo.RegisterVO;

public interface LoginService {

    public ResultMessage login(String id, String password);
    public ResultMessage register(RegisterVO registerVO);
    public ResultMessage findPassword(String username);
    public ResultMessage checkAnswer(String answer1,String answer2,String answer3);
    public ResultMessage setPassword(String password1,String password2);

}
