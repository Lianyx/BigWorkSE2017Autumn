package businesslogic.inventorybl;

import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdateInfo;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.InventoryWarningReceiptPO;
import po.receiptPO.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.inventoryVO.InventoryOverflowReceiptVO;
import vo.inventoryVO.InventoryReceiptVO;
import vo.inventoryVO.InventoryWarningReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;
import vo.inventoryVO.uiReceipt.InventoryGiftuiVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Set;

public class InventoryWarningReceiptbl extends Receiptbl<InventoryWarningReceiptVO,InventoryWarningReceiptPO> {
    GoodsUpdateInfo info;

    public InventoryWarningReceiptbl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryWarningReceiptVO.class,InventoryWarningReceiptPO.class);
        info = new GoodsUpdate();
    }


    @Override
    public ResultMessage approve(InventoryWarningReceiptVO receiptVO) throws RemoteException {
        /*InventoryWarningReceiptPO receiptPO = receiptVO.toPO();
        receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        return ResultMessage.SUCCESS;*/
        return null;

    }
}
