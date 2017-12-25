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

    public Inventorybl(Class<? extends ReceiptVO> receiptVOClass, Class<? extends ReceiptPO> receiptPOClass, String className) throws RemoteException, NotBoundException, MalformedURLException {
        super(receiptVOClass, receiptPOClass, className);
    }
}