package blServiceStub.businessblservice_Stub;

import blService.billblservice.BillblService;
import blService.businessblservice.BusinessProgressInfo;
import blService.businessblservice.BusinessblService;
import blService.businessblservice.SalesDetailInfo;
import util.Date;
import vo.BusinessConditionVO;
import vo.BusinessProgressVO;
import vo.SalesDetailVO;

import java.util.Set;

public class BusinessblService_Stub implements BusinessblService{
    public Set<BusinessConditionVO> search(Date start, Date end){
        return new Set;
    }

    public Set<BusinessProgressVO> search(BusinessProgressInfo businessProgressInfo){
        return new Set;
    }

    public Set<SalesDetailVO> search(SalesDetailInfo salesDetailInfo){
        return new Set;
    }
}
