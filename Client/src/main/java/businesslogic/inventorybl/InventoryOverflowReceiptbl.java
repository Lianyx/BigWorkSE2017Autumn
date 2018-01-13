package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import blService.goodsblService.GoodsSalesUpdateInfo;
import po.receiptPO.InventoryOverflowReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryOverflowReceiptbl extends Receiptbl<InventoryOverflowReceiptVO,InventoryOverflowReceiptPO> implements InventoryOverflowReceiptblService {
    GoodsInventoryUpdateInfo info;

    public InventoryOverflowReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryOverflowReceiptVO.class,InventoryOverflowReceiptPO.class);
        info = new GoodsInventoryUpdate();
    }




    @Override
    public ResultMessage approve(InventoryOverflowReceiptVO receiptVO) throws RemoteException {
        receiptVO.setReceiptState(ReceiptState.APPROVED);

        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();

        info.goodsDamageUpdate(list);

        return ResultMessage.SUCCESS;
    }
}
