package data.businessdata;

import po.ReceiptPO;
import util.BusinessProgressInfo;

import java.util.List;
import java.util.Map;

public interface BusinessProgressMapper {

    public List<ReceiptPO> select(Map<String,Object> map);
}
