package po;

import vo.StockRelatedReceiptVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/22.
 */
public class StockReturnReceiptPO extends StockRelatedReceiptPO {
    public StockReturnReceiptPO(String id, String supplierID, String warehouse, String operator, ArrayList<GoodsItemPO> goodsList, String remark, int total) {
        super(id, supplierID, warehouse, operator, goodsList, remark, total);
    }
}
