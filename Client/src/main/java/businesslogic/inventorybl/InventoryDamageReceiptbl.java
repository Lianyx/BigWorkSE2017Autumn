package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryDamageReceiptblService;
import businesslogic.checkbl.Receiptbl;
import blService.goodsblService.GoodsSalesUpdateInfo;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import po.receiptPO.InventoryDamageReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.inventoryReceiptVO.InventoryDamageReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryDamageReceiptbl extends Receiptbl<InventoryDamageReceiptVO,InventoryDamageReceiptPO> implements InventoryDamageReceiptblService{
    GoodsInventoryUpdateInfo info;

    public InventoryDamageReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryDamageReceiptVO.class, InventoryDamageReceiptPO.class);
        info = new GoodsInventoryUpdate();
    }


    @Override
    public ResultMessage approve(InventoryDamageReceiptVO receiptVO) throws RemoteException {
        receiptVO.setReceiptState(ReceiptState.APPROVED);

        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();

        info.goodsDamageUpdate(list);

        return ResultMessage.SUCCESS;

    }
}
