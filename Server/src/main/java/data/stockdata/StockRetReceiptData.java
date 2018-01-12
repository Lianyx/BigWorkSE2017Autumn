package data.stockdata;

import annotations.RMIRemote;
import data.checkdata.ReceiptData;
import mapper.StockRetReceiptPOMapper;
import po.receiptPO.StockRetReceiptPO;

import java.rmi.RemoteException;
@RMIRemote
public class StockRetReceiptData extends ReceiptData<StockRetReceiptPO> {
    public StockRetReceiptData() throws RemoteException{
        super(StockRetReceiptPOMapper.class, StockRetReceiptPO.class);

    }
}
