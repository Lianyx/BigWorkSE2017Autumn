package dataService;

import po.MemberPO;
import util.ResultMessage;
import util.SearchConditions;
import vo.MemberVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/22.
 */
public interface MemberDataService {
    public ResultMessage insert(MemberPO memberPO);
    public ResultMessage update(MemberPO memberPO);
    public ResultMessage delete(String id);
    public ArrayList<MemberPO> search(SearchConditions sc);
}
