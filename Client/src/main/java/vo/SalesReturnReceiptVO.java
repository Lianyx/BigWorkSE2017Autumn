package vo;

import po.SalesRelatedReceiptPO;

/**
 * Created by tiberius on 2017/10/22.
 */
public class SalesReturnReceiptVO extends SalesRelatedReceiptPO {
    public SalesReturnReceiptVO(String id, String buyerID, String warehouse) {
        super(id, buyerID, warehouse);
    }
}
