package po;

import po.GoodsItemPO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/20.
 */
public abstract class StockRelatedReceiptPO {
    private String id; // ??????JHD-yyyyMMdd-xxxxx??????¦Ë????1????????????????????????99999??????
    private String supplierID; // ???????????id
    private String warehouse;
    private String operator; // ?????id
    private ArrayList<GoodsItemPO> goodsList;
    private String remark; // ???
    private int total;

    public StockRelatedReceiptPO(String id, String supplierID, String warehouse, String operator, ArrayList<GoodsItemPO> goodsList, String remark, int total) {
        this.id = id;
        this.supplierID = supplierID;
        this.warehouse = warehouse;
        this.operator = operator;
        this.goodsList = goodsList;
        this.remark = remark;
        this.total = total;
    }
}
