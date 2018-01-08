package businesslogic.inventorybl;

import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdateInfo;
import po.receiptPO.InventoryGiftReceiptPO;
import util.ResultMessage;
import vo.inventoryVO.InventoryGiftReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class InventoryGiftReceiptbl extends Receiptbl<InventoryGiftReceiptVO,InventoryGiftReceiptPO> {
    GoodsUpdateInfo info;

    public InventoryGiftReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryGiftReceiptVO.class,InventoryGiftReceiptPO.class);
        info = new GoodsUpdate();
    }





    @Override
    public ResultMessage approve(InventoryGiftReceiptVO receiptVO) throws RemoteException {
       /* receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        InventoryReceiptGoodsItemPO[] goodsList = receiptPO.getGoodsList();

        List<GoodsPO> poList = new ArrayList<>();

        for (InventoryReceiptGoodsItemPO item: goodsList) {
            GoodsPO po = new GoodsPO(item.getId(),null,null,null,item.getInventoryNum()-item.getsendNumber(),
                    -1,-1,-1,-1,-1);
            poList.add(po);
        }

        info.goodsUpdate(poList);

        return ResultMessage.SUCCESS;*/
        return null;
    }


    //还有转VO
}
