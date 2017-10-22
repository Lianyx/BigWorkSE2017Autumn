package blServiceStub;

import utilitybl.ResultMessage;
import utilitybl.SearchConditions;
import blService.MemberblService;
import vo.MemberVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public class MemberblService_Stub implements MemberblService {
    @Override
    public ResultMessage insert(MemberVO memberVO) {
        if (memberVO.getId().equals("00001")) return ResultMessage.FAIL;
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(MemberVO memberVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        if (id.equals("00001"))
            return ResultMessage.SUCCESS;
        return ResultMessage.FAIL;
    }

    @Override
    public ArrayList<MemberVO> search(SearchConditions sc) {
        ArrayList<MemberVO> members = new ArrayList<>();
        members.add(new MemberVO("00002", "��С��"));
        return members;
    }
}
