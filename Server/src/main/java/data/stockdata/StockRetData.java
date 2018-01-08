package data.stockdata;

import data.checkdata.ReceiptData;
import mapper.StockRetReceiptPOMapper;
import po.receiptPO.StockRetReceiptPO;

import java.rmi.RemoteException;

public class StockRetData extends ReceiptData<StockRetReceiptPO> {
    public StockRetData() throws RemoteException{
        super(StockRetReceiptPOMapper.class, StockRetReceiptPO.class);

    }
}
