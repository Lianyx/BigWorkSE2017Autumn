package businesslogic.inventorybl.inventoryInfo;

import businesslogic.inventorybl.InventoryWarningReceiptbl;
import po.GoodsPO;
import util.ReceiptState;
import vo.inventoryVO.InventoryWarningGoodsItemVO;
import vo.inventoryVO.InventoryWarningReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class WarningReceiptInfoImpl implements InventoryWarningReceiptInfo{
    InventoryWarningReceiptbl receiptbl;

    /*
    构造inventoryWarningReceiptbl的时候有问题
     */
    public WarningReceiptInfoImpl() {
        //receiptbl = new InventoryWarningReceiptbl();
    }

    @Override
    public boolean checkIsWarning(List<GoodsPO> goodsList) throws RemoteException {
        List<InventoryWarningGoodsItemVO>  list = new ArrayList<>(goodsList.size());

        for (GoodsPO po:goodsList) {
            if(po.getInventoryNum() < po.getAlarmNumber())
                list.add(new InventoryWarningGoodsItemVO(po.getId(),po.getGoodName(),po.getGoodType(),po.getInventoryNum(),
                po.getAlarmNumber()));
        }

        /*
        同样，构造参数有同样的问题需要考虑
         */
        InventoryWarningReceiptVO receiptVO = new InventoryWarningReceiptVO(null,0,null,null,
                ReceiptState.PENDING,list);

        receiptbl.insert(receiptVO);

        return !list.isEmpty();
    }
}
