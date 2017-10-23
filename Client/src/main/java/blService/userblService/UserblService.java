package blService.userblService;

import bl.util.ResultMessage;

public interface UserblService {



    public ResultMessage login(long id, String password);
    public ResultMessage register(long id,String password1,String password2);
    public ResultMessage find(long id);
    public ResultMessage checkAnswer(String answer1,String answer2,String answer3);
    public ResultMessage setPassword(String password1,String password2);

}
