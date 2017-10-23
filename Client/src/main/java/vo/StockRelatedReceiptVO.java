package vo;

import po.GoodsItemPO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public abstract class StockRelatedReceiptVO { // 和PO一样呃
    private String id; // 格式为：JHD-yyyyMMdd-xxxxx，后五位每天从1开始编号，所以一天最多可以生成99999条单子
    private String supplierID; // 客户（供应商）id
    private String warehouse;
    private String operator; // 操作员id
    private ArrayList<GoodsItemPO> goodsList;
    private String remark; // 备注
    private int total;

    public StockRelatedReceiptVO(String id, String supplierID, String warehouse, String operator, ArrayList<GoodsItemPO> goodsList, String remark, int total) {
        this.id = id;
        this.supplierID = supplierID;
        this.warehouse = warehouse;
        this.operator = operator;
        this.goodsList = goodsList;
        this.remark = remark;
        this.total = total;
    }
}
