package businesslogic.inventorybl;

import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdateInfo;
import po.GoodsPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.InventoryReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InventoryOverflowReceiptbl extends Receiptbl<InventoryOverflowReceiptVO,InventoryOverflowReceiptPO> {
    GoodsUpdateInfo info;

    public InventoryOverflowReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryOverflowReceiptVO.class,InventoryOverflowReceiptPO.class);
        info = new GoodsUpdate();
    }




 /*   @Override
    public ResultMessage approve(InventoryReceiptVO receiptVO) throws RemoteException {
        InventoryReceiptPO receiptPO = receiptVO.toPO();
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
        return null;
    }*/



    @Override
    public ResultMessage approve(InventoryOverflowReceiptVO receiptVO) throws RemoteException {
        return null;
    }
}
