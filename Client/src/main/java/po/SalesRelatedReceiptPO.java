package po;

import po.GoodsItemPO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/20.
 */
public abstract class SalesRelatedReceiptPO {
    private String id; // ��ʽΪ��JHD-yyyyMMdd-xxxxx������λÿ���1��ʼ��ţ�����һ������������99999������
    private String buyerID; // �ͻ��������̣�id
    private String warehouse;
    private String operatorID; // ����Աid
    private ArrayList<GoodsItemPO> goodsList;
    private String remark; // ��ע
    private int total; // ������ܶ�

    private int totalBeforeDiscount;
    private int discount; // ���ü۸�
    private int tokenAmount; // ʹ�ô��������
    private String agentID; // ҵ��Աid

    public SalesRelatedReceiptPO(String id, String buyerID, String warehouse) {
        this.id = id;
        this.buyerID = buyerID;
        this.warehouse = warehouse;
    }
}
