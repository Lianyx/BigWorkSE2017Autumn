package blServiceStub;

import blService.SalesblService;
import utilitybl.ResultMessage;
import vo.PromotionVO;
import vo.SalesRelatedReceiptVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public class SalesblService_Stub implements SalesblService {
    static int saleReceiptID = 1;
    static int saleReturnReceiptID = 1;
    @Override
    public String getSalesReceiptID() {
        return "XSD-20171011-" + String.format("%05d", saleReceiptID++);
    }

    @Override
    public String getSalesReturnReceiptID() {
        return "XSTHD-20171011-" + String.format("%05d", saleReturnReceiptID++);
    }

    @Override
    public ResultMessage submit(SalesRelatedReceiptVO salesReceiptVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(SalesRelatedReceiptVO salesReceiptVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public SalesRelatedReceiptVO find(String id) {
        return new SalesRelatedReceiptVO("00001", "00002", "�ֿ���");
    }

    @Override
    public ArrayList<String> getGoodsNames() {
        ArrayList<String> goods = new ArrayList<>();
        goods.add("��Ʒ��");
        goods.add("��Ʒ��");
        return goods;
    }

    @Override
    public int getPrice(String goodsName, String goodsModel) {
        return 100;
    }

    @Override
    public ArrayList<PromotionVO> getPromotions() {
        ArrayList<PromotionVO> promotions = new ArrayList<>();
        promotions.add(new PromotionVO("����"));
        return promotions;
    }
}
