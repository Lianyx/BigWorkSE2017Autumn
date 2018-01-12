package businesslogic.goodsbl.goodsUpdate;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.rmi.RemoteException;
import java.util.List;

public class GoodsInventoryUpdate implements GoodsInventoryUpdateInfo {
    @Override
    public void goodsUpdate(List<ReceiptGoodsItemVO> list) throws RemoteException {

    }
}
