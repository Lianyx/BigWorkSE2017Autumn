package blService;

import util.ResultMessage;
import vo.PromotionVO;
import vo.SalesRelatedReceiptVO;

import java.util.ArrayList;

/**
 * Created by tiberius on 2017/10/21.
 */
public interface SalesblService {
    String getSalesReceiptID();
    String getSalesReturnReceiptID();
    ResultMessage submit(SalesRelatedReceiptVO salesReceiptVO);
    ResultMessage update(SalesRelatedReceiptVO salesReceiptVO);
    ResultMessage delete(String id);
    SalesRelatedReceiptVO find(String id);
    ArrayList<String> getGoodsNames();
    int getPrice(String goodsName, String version);
    ArrayList<PromotionVO> getPromotions();
}
