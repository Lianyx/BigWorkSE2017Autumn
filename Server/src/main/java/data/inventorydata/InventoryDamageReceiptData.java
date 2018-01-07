package data.inventorydata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.InventoryDamageReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.InventoryDamageReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryDamageReceiptData extends ReceiptData<InventoryDamageReceiptPO> {
    public InventoryDamageReceiptData() throws RemoteException {
        super(InventoryDamageReceiptPOMapper.class, InventoryDamageReceiptPO.class);
    }
}
