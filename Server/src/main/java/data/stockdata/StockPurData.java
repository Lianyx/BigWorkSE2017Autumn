package data.stockdata;

import data.checkdata.ReceiptData;
import mapper.StockPurReceiptPOMapper;
import po.receiptPO.StockPurReceiptPO;

import java.rmi.RemoteException;

public class StockPurData extends ReceiptData<StockPurReceiptPO> {
    public StockPurData() throws RemoteException{
        super(StockPurReceiptPOMapper.class, StockPurReceiptPO.class);
    }
}
