package data.inventorydata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.InventoryDamageReceiptPOMapper;
import mapper.InventoryGiftReceiptPOMapper;
import mapper.generic.ReceiptPOMapper;
import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryGiftReceiptPO;

import java.rmi.RemoteException;

@RMIRemote
public class InventoryGiftReceiptData extends ReceiptData<InventoryGiftReceiptPO> {
    public InventoryGiftReceiptData() throws RemoteException {
        super(InventoryGiftReceiptPOMapper.class, InventoryGiftReceiptPO.class);
    }
}
