package blService.businessblService;

import java.util.Date;
import vo.BusinessConditionVO;
import vo.BusinessProgressVO;
import vo.SalesDetailVO;

import java.util.Set;

public interface BusinessblService {
    public Set<BusinessConditionVO> search(Date start, Date end);

    public Set<BusinessProgressVO> search(BusinessProgressInfo businessProgressInfo);

    public Set<SalesDetailVO> search(SalesDetailInfo salesDetailInfo);
}
