package blService.userblService;

import util.ResultMessage;
import vo.RegisterVO;

public interface LoginblService {

    public ResultMessage login(String id, String password);
}
