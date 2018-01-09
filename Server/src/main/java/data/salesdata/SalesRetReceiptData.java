package data.salesdata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.InventoryWarningReceiptPOMapper;
import mapper.SalesRetReceiptPOMapper;
import mapper.SalesSellReceiptPOMapper;
import po.receiptPO.SalesRetReceiptPO;

import java.rmi.RemoteException;
@RMIRemote
public class SalesRetReceiptData extends ReceiptData<SalesRetReceiptPO> {
    public SalesRetReceiptData() throws RemoteException {
        super(SalesRetReceiptPOMapper.class, SalesRetReceiptPO.class);
    }
}
