package dataService.logdataService;

import po.LogPO;
import po.MessagePO;
import util.ResultMessage;
import vo.LogListVO;
import vo.LogSearchVO;

import java.util.ArrayList;
import java.util.Set;

public interface LogdataService {
    public ResultMessage insert(LogPO log);
    public ArrayList<LogPO> select(String keyword);
    public ArrayList<LogPO> select(LogSearchVO logSearchVO);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);



}
