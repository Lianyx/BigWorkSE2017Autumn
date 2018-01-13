package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryGiftReceiptblService;
import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import blService.goodsblService.GoodsSalesUpdateInfo;
import po.GoodsPO;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryReceiptGoodsItemPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryGiftReceiptbl extends Receiptbl<InventoryGiftReceiptVO,InventoryGiftReceiptPO> implements InventoryGiftReceiptblService{
    GoodsInventoryUpdateInfo info;
    Goodsbl goodsbl;

    public InventoryGiftReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryGiftReceiptVO.class,InventoryGiftReceiptPO.class);
        info = new GoodsInventoryUpdate();
        goodsbl = new Goodsbl();
    }

    @Override
    public ResultMessage approve(InventoryGiftReceiptVO receiptVO) throws RemoteException {
        receiptVO.setReceiptState(ReceiptState.APPROVED);

        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();

        info.goodsGiftUpdate(list);

        return ResultMessage.SUCCESS;
    }

    public double getGiftCost(List<ReceiptGoodsItemVO> list) throws RemoteException {
        double sum = 0;

        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            sum += (goodsVO.getPurPrice()*vo.getSendNum());
        }

        return sum;
    }
}
