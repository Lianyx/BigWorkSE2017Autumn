package businesslogic.inventorybl;

import businesslogic.checkbl.Receiptbl;
import businesslogic.goodsbl.goodsUpdate.GoodsUpdate;
import blService.goodsblService.GoodsUpdateInfo;
import po.receiptPO.InventoryWarningReceiptPO;
import util.ResultMessage;
import vo.inventoryVO.InventoryWarningReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

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
