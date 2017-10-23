package blService;

import po.ReceiptPO;

public interface CheckblService {
    public void init();
    public void showDetail(ReceiptPO receipt);
    public void approve(ReceiptPO receipt);
    public void reject(ReceiptPO receipt);

}
