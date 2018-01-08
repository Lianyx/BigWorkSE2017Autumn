package data.salesdata;

import data.checkdata.ReceiptData;
import mapper.SalesRetReceiptPOMapper;
import mapper.SalesSellReceiptPOMapper;
import po.receiptPO.SalesRetReceiptPO;
import po.receiptPO.SalesSellReceiptPO;

import java.rmi.RemoteException;

public class SalesSellData extends ReceiptData<SalesSellReceiptPO> {
    public SalesSellData() throws RemoteException{
        super(SalesSellReceiptPOMapper.class, SalesSellReceiptPO.class);
    }
}
