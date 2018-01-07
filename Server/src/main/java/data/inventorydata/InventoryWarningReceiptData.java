package data.inventorydata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.InventoryWarningReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.InventoryWarningReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryWarningReceiptData extends ReceiptData<InventoryWarningReceiptPO> {
    public InventoryWarningReceiptData() throws RemoteException {
        super(InventoryWarningReceiptPOMapper.class, InventoryWarningReceiptPO.class);
    }
}
