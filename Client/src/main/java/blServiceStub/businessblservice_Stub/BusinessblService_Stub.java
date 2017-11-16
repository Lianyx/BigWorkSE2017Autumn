package blServiceStub.businessblservice_Stub;

import blService.billblService.BillblService;
import blService.businessblService.BusinessProgressInfo;
import blService.businessblService.BusinessblService;
import blService.businessblService.SalesDetailInfo;
import java.util.Date;
import vo.BusinessConditionVO;
import vo.BusinessProgressVO;
import vo.SalesDetailVO;

import java.util.HashSet;
import java.util.Set;

public class BusinessblService_Stub implements BusinessblService{
    public Set<BusinessConditionVO> search(Date start, Date end){
        return new HashSet();
    }

    public Set<BusinessProgressVO> search(BusinessProgressInfo businessProgressInfo){
        return new HashSet();
    }

    public Set<SalesDetailVO> search(SalesDetailInfo salesDetailInfo){
        return new HashSet();
    }
}
