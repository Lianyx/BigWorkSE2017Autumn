package blService.accountblService;

import util.ResultMessage;
import vo.AccountVO;

import java.util.List;
import java.util.Set;

public interface AccountblService {
    public List<AccountVO> showAllAccounts();

    public List<AccountVO> search(String keyword);

    public ResultMessage add(AccountVO vo);

    public ResultMessage delete(int ID);

    public ResultMessage update(AccountVO accountVO);

}
