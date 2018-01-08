package blService.billblservice;

import blService.checkblService.ReceiptblService;
import util.ResultMessage;
import vo.billReceiptVO.*;

import java.util.Set;

public interface ChargeBillReceiptblService {

    public int getDayId();
    public ResultMessage add(ChargeReceiptVO chargeReceiptVO);
    public ResultMessage delete(String id);
    public ResultMessage update(ChargeReceiptVO chargeReceiptVO);
    public Set<ChargeReceiptListVO> search(BillReceiptSearchVO billReceiptSearchVO);
    public ChargeReceiptVO showDetail(String id);
    public Set<ChargeReceiptListVO> getALL();

}












