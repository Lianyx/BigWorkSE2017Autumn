package blService.memberblService;

import util.ResultMessage;
import vo.MemberChooseVO;

import java.util.Set;

public interface MemberInfo {

    public ResultMessage updateDebt(int memberId,double debt);
    public ResultMessage updateCredit(int memberId,double credit);
}
