package businesslogic.inventorybl.inventoryInfo;

import businesslogic.goodsbl.Goodsbl;
import businesslogic.inventorybl.InventoryWarningReceiptbl;
import po.GoodsPO;
import vo.ListGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class WarningReceiptInfoImpl implements InventoryWarningReceiptInfo{
    InventoryWarningReceiptbl receiptbl;
    Goodsbl goodsbl;

    /*
    构造inventoryWarningReceiptbl的时候有问题
     */
    public WarningReceiptInfoImpl() throws RemoteException, NotBoundException, MalformedURLException {
        receiptbl = new InventoryWarningReceiptbl();
    }

    @Override
    public boolean checkSaleRet(List<ListGoodsItemVO> list) throws RemoteException {
        return false;
    }

    @Override
    public boolean checkSaleSel(List<ListGoodsItemVO> list) throws RemoteException {
        return false;
    }

    @Override
    public boolean checkStockPur(List<ListGoodsItemVO> list) throws RemoteException {
        return false;
    }

    @Override
    public boolean checkStorckRet(List<ListGoodsItemVO> list) throws RemoteException {
        return false;
    }
}
