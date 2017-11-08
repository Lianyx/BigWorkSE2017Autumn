package blService.logblService;
import po.LogPO;
import vo.LogSearchVO;
import util.ResultMessage;
import vo.LogListVO;

import java.util.*;
public interface LogblService {
    public ResultMessage insert(LogPO log);
    public Set<LogListVO> search(String keyword);
    public Set<LogListVO> search(LogSearchVO logSearchVO);
    public Set<LogListVO> getAll();
    public ResultMessage delete(ArrayList<Integer> list);
    public ResultMessage delete(int id);

}
