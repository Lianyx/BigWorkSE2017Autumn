package businesslogic.inventorybl;

import blService.inventoryblService.InventoryblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.ReceiptPO;

import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public abstract class Inventorybl<TV extends ReceiptVO,TP extends ReceiptPO> extends Receiptbl<TV,TP> {

    public Inventorybl(Class<TV> receiptVOClass, Class<TP> receiptPOClass) throws RemoteException, NotBoundException, MalformedURLException {
        super(receiptVOClass, receiptPOClass);
    }
}
