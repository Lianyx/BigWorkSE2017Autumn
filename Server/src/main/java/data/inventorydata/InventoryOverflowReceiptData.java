package data.inventorydata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.InventoryOverflowReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryOverflowReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryOverflowReceiptData extends ReceiptData<InventoryOverflowReceiptPO> {
    public InventoryOverflowReceiptData() throws RemoteException {
        super(InventoryOverflowReceiptPOMapper.class, InventoryOverflowReceiptPO.class);
    }
}
