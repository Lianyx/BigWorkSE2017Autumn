package businesslogic.inventorybl;

import businesslogic.checkbl.Receiptbl;
import blService.goodsblService.GoodsUpdateInfo;
import po.receiptPO.InventoryDamageReceiptPO;
import util.ResultMessage;
import vo.inventoryVO.InventoryDamageReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class InventoryDamageReceiptbl extends Receiptbl<InventoryDamageReceiptVO,InventoryDamageReceiptPO> {
    GoodsUpdateInfo info;

    public InventoryDamageReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryDamageReceiptVO.class, InventoryDamageReceiptPO.class);
    }


    @Override
    public ResultMessage approve(InventoryDamageReceiptVO receiptVO) throws RemoteException {
      /*  receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        InventoryReceiptGoodsItemPO[] goodsList = receiptPO.getGoodsList();

        List<GoodsPO> poList = new ArrayList<>();

        for (InventoryReceiptGoodsItemPO item: goodsList) {
            GoodsPO po = new GoodsPO(item.getId(),null,null,null,item.getFactNumber(),
                    -1,-1,-1,-1,-1);
            poList.add(po);
        }

        info.goodsUpdate(poList);

        return ResultMessage.SUCCESS;*/
      return null;

    }
}
