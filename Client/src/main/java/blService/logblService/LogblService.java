package blService.logblService;
import po.LogPO;

import java.util.*;
public interface LogblService {
    public void record(LogPO log);

    public List search(String keyword);
}
