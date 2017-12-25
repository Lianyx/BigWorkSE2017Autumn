package businesslogic.inventorybl.inventoryInfo;

import po.GoodsPO;

import java.rmi.RemoteException;
import java.util.List;

public interface InventoryWarningReceiptInfo {
    /**
     * 该接口用作各单据的approve中，当对商品的库存数量造成改变时，要调用该方法，检查是否需要生成报警单
     */

    public boolean checkIsWarning(List<GoodsPO> list) throws RemoteException;
}
