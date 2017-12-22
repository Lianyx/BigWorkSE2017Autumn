package dataService.logdataService;

import po.LogPO;
import po.MessagePO;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Set;

public interface LogdataService {
    public ResultMessage insert(LogPO log);
    public ArrayList<LogPO> show();
    public ArrayList<LogPO> select(String keyword);
    public ResultMessage delete(int id);
    public ResultMessage delete(ArrayList<Integer> list);



}
