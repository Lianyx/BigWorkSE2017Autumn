package businesslogic.inventorybl;

import blService.goodsblService.GoodsInventoryUpdateInfo;
import blService.inventoryblService.InventoryOverflowReceiptblService;
import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.Goodsbl;
import businesslogic.goodsbl.goodsUpdate.GoodsInventoryUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsSalesUpdate;
import blService.goodsblService.GoodsSalesUpdateInfo;
import po.receiptPO.InventoryOverflowReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.GoodsVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryOverflowReceiptbl extends Receiptbl<InventoryOverflowReceiptVO,InventoryOverflowReceiptPO> implements InventoryOverflowReceiptblService {
    GoodsInventoryUpdateInfo info;
    Goodsbl goodsbl;

    public InventoryOverflowReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryOverflowReceiptVO.class,InventoryOverflowReceiptPO.class);
        info = new GoodsInventoryUpdate();
        goodsbl = new Goodsbl();
    }




    @Override
    public ResultMessage approve(InventoryOverflowReceiptVO receiptVO) throws RemoteException {
        receiptVO.setReceiptState(ReceiptState.APPROVED);

        List<ReceiptGoodsItemVO> list =  receiptVO.getItems();

        info.goodsDamageUpdate(list);

        return ResultMessage.SUCCESS;
    }

    public double getDamageCost(List<ReceiptGoodsItemVO> list) throws RemoteException {
        double sum = 0;

        for (ReceiptGoodsItemVO vo:list) {
            GoodsVO goodsVO = goodsbl.showDetail(vo.getGoodsId());
            int damageSum = vo.getInventoryNum()-vo.getFactNum();
            sum += (goodsVO.getPurPrice()*damageSum);
        }

        return sum;
    }
}
