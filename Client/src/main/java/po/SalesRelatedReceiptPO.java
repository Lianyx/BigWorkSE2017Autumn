package po;

import po.GoodsItemPO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/20.
 */
public abstract class SalesRelatedReceiptPO {
    private String id; // 格式为：JHD-yyyyMMdd-xxxxx，后五位每天从1开始编号，所以一天最多可以生成99999条单子
    private String buyerID; // 客户（销售商）id
    private String warehouse;
    private String operatorID; // 操作员id
    private ArrayList<GoodsItemPO> goodsList;
    private String remark; // 备注
    private int total; // 结算后总额

    private int totalBeforeDiscount;
    private int discount; // 折让价格
    private int tokenAmount; // 使用代金劵金额
    private String agentID; // 业务员id

    public SalesRelatedReceiptPO(String id, String buyerID, String warehouse) {
        this.id = id;
        this.buyerID = buyerID;
        this.warehouse = warehouse;
    }
}
