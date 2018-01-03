package businesslogic.inventorybl;

import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdateInfo;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class InventoryWarningReceiptbl extends Inventorybl {
    GoodsUpdateInfo info;

    public InventoryWarningReceiptbl(Class<? extends ReceiptVO> receiptVOClass, Class<? extends ReceiptPO> receiptPOClass,
                                     String className) throws RemoteException, NotBoundException, MalformedURLException {
        super(receiptVOClass, receiptPOClass, className);
        info = new GoodsUpdate();
    }

    @Override
    public ResultMessage approve(InventoryReceiptPO receiptPO) throws RemoteException {
        receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        return ResultMessage.SUCCESS;
    }

    @Override
    public Set<InventoryGiftuiVO> getAll() {
        return null;
    }
}
