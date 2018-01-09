package dataService.logdataService;

import po.LogPO;
import po.MessagePO;
import util.ResultMessage;

import java.util.ArrayList;
import java.util.Set;

public interface LogdataService {
    public int getId();
    public ResultMessage insert(LogPO log);
    public ArrayList<LogPO> getAll();

}
