package dataService.memberdataService;

import po.MemberPO;
import util.ResultMessage;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/22.
 */
public interface MemberDataService {
    public ResultMessage insert(MemberPO memberPO);
    public ResultMessage update(MemberPO memberPO);
    public ResultMessage delete(String id);
    public ArrayList<MemberPO> search(String keyword);
}
