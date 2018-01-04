package blService.accountblService;

import util.ResultMessage;
import vo.AccountListVO;
import vo.AccountVO;

import java.util.List;
import java.util.Set;

public interface AccountblService {
    public List<AccountListVO> showAllAccounts();

    public List<AccountListVO> search(String keyword);

    public ResultMessage add(AccountVO vo);

    public ResultMessage delete(int ID);

    public ResultMessage update(AccountVO accountVO);

}
