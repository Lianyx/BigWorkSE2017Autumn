package blService.businessblService;

//import po.ReceiptPO;
import util.BusinessProgressInfo;
import vo.ReceiptVO;

import java.util.List;

public interface BusinessProgressblService {
    public List<ReceiptVO> select(BusinessProgressInfo businessProgressInfo);
}
