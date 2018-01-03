package businesslogic.inventorybl;

import blService.inventoryblService.InventoryblService;
import businesslogic.checkbl.Receiptbl;
import po.receiptPO.InventoryReceiptPO;
import po.receiptPO.ReceiptPO;
import vo.inventoryVO.InventoryReceiptVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public abstract class Inventorybl extends Receiptbl<InventoryReceiptVO,InventoryReceiptPO> implements InventoryblService{

    public Inventorybl() throws RemoteException, NotBoundException, MalformedURLException {
        super(InventoryReceiptVO.class, InventoryReceiptPO.class);
    }
}