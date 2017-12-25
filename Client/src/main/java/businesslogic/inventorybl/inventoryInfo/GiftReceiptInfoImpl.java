package businesslogic.inventorybl.inventoryInfo;

import businesslogic.inventorybl.InventoryGiftReceiptbl;
import po.GoodsPO;
import util.ReceiptState;
import vo.inventoryVO.InventoryGiftReceiptVO;
import vo.inventoryVO.InventroyGiftGoodsItemVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GiftReceiptInfoImpl implements InventoryGiftReceiptInfo {
    InventoryGiftReceiptbl giftReceiptbl;

    /*
    这里有问题，等salesellreceipt再看，少的那个PO的构造方法也许能用
     */
    public GiftReceiptInfoImpl() {

    }

    /*
    构造赠送单的时候那些数据还需要再考虑考虑,赠送数量的接口还需要商量
     */
    @Override
    public void addInventoryGiftReceipt(List<GoodsPO> goodsList) throws RemoteException {
        List<InventroyGiftGoodsItemVO> list = new ArrayList<>(goodsList.size());

        for (GoodsPO po:goodsList) {
            list.add(new InventroyGiftGoodsItemVO(po.getId(),po.getGoodName(),po.getGoodType(),po.getInventoryNum(),0));
        }

        InventoryGiftReceiptVO receiptVO = new InventoryGiftReceiptVO(null,0,null,null,
                ReceiptState.PENDING,list);

        giftReceiptbl.insert(receiptVO);
    }
}
