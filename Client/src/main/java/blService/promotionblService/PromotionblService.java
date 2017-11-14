package blService.promotionblService;

import po.ReceiptPO;
import util.ResultMessage;
import vo.PromotionSearchVO;

import java.util.ArrayList;

public interface PromotionblService {
    public ArrayList<ReceiptPO> initCheck();
    public ResultMessage update(ReceiptPO receiptPO);
    public ResultMessage reject(int id);
    public ResultMessage approve(int id);
    public ResultMessage rejectBatch(ArrayList<Integer> ids);
    public ResultMessage approveBatch(ArrayList<Integer> ids);
    public ArrayList<ReceiptPO> search(PromotionSearchVO promotionSearchVO);
}
