package blService.accountblservice;

import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;
import java.util.Set;

public interface AccountblService {
    public Set<AccountVO> showAllAcounts();

    public Set<AccountVO> search(String keyword);

    public ResultMessage add(AccountVO vo);

    public ResultMessage update(String id);

}
