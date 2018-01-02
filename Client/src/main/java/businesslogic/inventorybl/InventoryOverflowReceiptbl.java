package businesslogic.inventorybl;

import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdateInfo;
import po.GoodsPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InventoryOverflowReceiptbl extends Inventorybl {
    GoodsUpdateInfo info;

    public InventoryOverflowReceiptbl(Class<? extends ReceiptVO> receiptVOClass, Class<? extends ReceiptPO> receiptPOClass,
                                      String className) throws RemoteException, NotBoundException, MalformedURLException {
        super();
        info = new GoodsUpdate();
    }

    @Override
    public ResultMessage approve(InventoryReceiptPO receiptPO) throws RemoteException {
        receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        InventoryReceiptGoodsItemPO[] goodsList = receiptPO.getGoodsList();

        List<GoodsPO> poList = new ArrayList<>();

        for (InventoryReceiptGoodsItemPO item: goodsList) {
            GoodsPO po = new GoodsPO(item.getId(),null,null,null,item.getFactNumber(),
                    -1,-1,-1,-1,-1);
            poList.add(po);
        }

        info.goodsUpdate(poList);

        return ResultMessage.SUCCESS;
    }
}
