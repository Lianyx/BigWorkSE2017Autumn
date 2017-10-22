package vo;

import po.GoodsItemPO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public class StockRelatedReceiptVO { // ��POһ����
    private String id; // ��ʽΪ��JHD-yyyyMMdd-xxxxx������λÿ���1��ʼ��ţ�����һ������������99999������
    private String supplierID; // �ͻ�����Ӧ�̣�id
    private String warehouse;
    private String operator; // ����Աid
    private ArrayList<GoodsItemPO> goodsList;
    private String remark; // ��ע
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
