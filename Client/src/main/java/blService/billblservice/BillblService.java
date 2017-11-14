package blService.billblservice;

import po.BillPO;
import util.ResultMessage;
import vo.BillVO;

import java.util.Set;

public interface BillblService {


    public Set<BillPO> find(String keyword);

    public ResultMessage add(BillVO billVO);



}
