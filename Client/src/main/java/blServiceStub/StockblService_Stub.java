package blServiceStub;

import blService.StockblService;
import utilitybl.ResultMessage;
import vo.StockRelatedReceiptVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public class StockblService_Stub implements StockblService {
    static int stockReceiptID = 1;
    static int stockReturnReceiptID = 1;
    @Override
    public String getStockReceiptID() {
        return "JHD-20171011-" + String.format("%05d", stockReceiptID++);
    }

    @Override
    public String getStockReturnReceiptID() {
        return "JHTHD-20171011-" + String.format("%05d", stockReturnReceiptID++);
    }

    @Override
    public ResultMessage submit(StockRelatedReceiptVO stockReceiptVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(StockRelatedReceiptVO stockReceiptVO) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(String id) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public StockRelatedReceiptVO find(String id) {
        return new StockRelatedReceiptVO(id, "��Ӧ�̼�", "�ֿ��",  "����Ա��", null, "no remark", 1000);
    }

    @Override
    public ArrayList<String> getGoodsNames() {
        ArrayList<String> goods = new ArrayList<>();
        goods.add("��Ʒ��1");
        goods.add("��Ʒ��1");
        return goods;
    }

    @Override
    public int getPrice(String goodsName, String goodsModel) {
        return 0;
    }
}
