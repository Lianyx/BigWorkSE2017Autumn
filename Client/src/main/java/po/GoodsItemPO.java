package po;

/**
 * Created by tiberius on 2017/10/20.
 */
public class GoodsItemPO {
    private String goodID;
    private String good;
    private String version; // �ͺ�
    private int number; // ����
    private int unitPrice; // ����
    private int total; // ���
    private String remark; // ��ע

    public GoodsItemPO(String goodID, String good, String version) {
        this.goodID = goodID;
        this.good = good;
        this.version = version;
    }
}
