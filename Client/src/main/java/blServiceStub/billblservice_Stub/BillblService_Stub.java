package blServiceStub.billblservice_Stub;

import blService.billblService.BillblService;
import po.BillPO;
import util.ResultMessage;
import vo.BillVO;

import java.util.HashSet;
import java.util.Set;

public class BillblService_Stub implements BillblService{
    public Set<BillPO> find(String keyword){
        Set<BillPO> set = new HashSet();
        return set;
    }

    public ResultMessage add(BillVO billVO){
        return ResultMessage.SUCCESS;
    }

}
