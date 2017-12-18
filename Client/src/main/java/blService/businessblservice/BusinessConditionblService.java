package blService.businessblservice;

import vo.BusinessConditionVO;

import java.time.LocalDateTime;
import java.util.List;

public interface BusinessConditionblService {
    public List<BusinessConditionVO> select(LocalDateTime begin, LocalDateTime end);
}
