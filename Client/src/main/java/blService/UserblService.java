package blService;

import utilitybl.ResultMessage;

public interface UserblService {
    public boolean login(long id,String password);
    public boolean register(long id,String password1,String password2);
    public boolean find(long id);
    public boolean checkAnswer(String answer1,String answer2,String answer3);
    public boolean setPassword(String password1,String password2);


}
