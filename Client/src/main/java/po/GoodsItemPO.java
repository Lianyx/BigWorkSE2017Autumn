package po;

/**
 * Created by tiberius on 2017/10/20.
 */
public class GoodsItemPO {
    private String goodID;
    private String good;
    private String version; // 型号
    private int number; // 数量
    private int unitPrice; // 单价
    private int total; // 金额
    private String remark; // 备注

    public GoodsItemPO(String goodID, String good, String version) {
        this.goodID = goodID;
        this.good = good;
        this.version = version;
    }
}
