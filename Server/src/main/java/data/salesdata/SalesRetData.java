package data.salesdata;

import data.checkdata.ReceiptData;
import mapper.InventoryWarningReceiptPOMapper;
import mapper.SalesRetReceiptPOMapper;
import mapper.SalesSellReceiptPOMapper;
import po.receiptPO.SalesRetReceiptPO;

import java.rmi.RemoteException;

public class SalesRetData extends ReceiptData<SalesRetReceiptPO> {
    public SalesRetData() throws RemoteException {
        super(SalesRetReceiptPOMapper.class, SalesRetReceiptPO.class);
    }
}
