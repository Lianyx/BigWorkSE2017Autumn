package dataServiceDriver;

import dataService.MemberDataService;
import po.MemberPO;
import bl.util.ResultMessage;
import bl.util.SearchConditions;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/22.
 */
public class MemberDataService_Stub implements MemberDataService {
    @Override
    public ResultMessage insert(MemberPO memberPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(MemberPO memberPO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        return ResultMessage.FAIL;
    }

    @Override
    public ArrayList<MemberPO> search(SearchConditions sc) {
        ArrayList<MemberPO> members = new ArrayList<>();
        members.add(new MemberPO("00001", "王小明"));
        return members;
    }
}
