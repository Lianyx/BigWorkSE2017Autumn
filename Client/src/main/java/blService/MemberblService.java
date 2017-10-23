package blService;

import bl.util.ResultMessage;
import bl.util.SearchConditions;
import vo.MemberVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public interface MemberblService {
    ResultMessage insert(MemberVO memberVO);
    ResultMessage update(MemberVO memberVO);
    ResultMessage delete(String id);
    public ArrayList<MemberVO> search(SearchConditions sc);
}
