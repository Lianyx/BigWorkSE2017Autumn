package blService.memberblService;

import util.ResultMessage;
import vo.MemberChooseVO;
import vo.MemberVO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public interface MemberInfo {

    public ResultMessage updateDebt(int memberId,double debt);
    public ResultMessage updateCredit(int memberId,double credit);
    public ArrayList<MemberVO> getALL();
    public double getStockPur(LocalDateTime floor,LocalDateTime ceil);
    public double getStockRet(LocalDateTime floor,LocalDateTime ceil);
    public double getSalesSell(LocalDateTime floor,LocalDateTime ceil);
    public double getSalesRet(LocalDateTime floor,LocalDateTime ceil);

}
