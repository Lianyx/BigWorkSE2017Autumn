package businesslogic.inventorybl.inventoryInfo;

import businesslogic.inventorybl.InventoryGiftReceiptbl;
import po.GoodsPO;
import po.ReceiptGoodsItemPO;
import ui.util.UserInfomation;
import vo.ListGoodsItemVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class GiftReceiptInfoImpl implements InventoryGiftReceiptInfo {
    InventoryGiftReceiptbl giftReceiptbl;

    /*
    这里有问题，等salesellreceipt再看，少的那个PO的构造方法也许能用
     */
    public GiftReceiptInfoImpl() throws RemoteException, NotBoundException, MalformedURLException {
        giftReceiptbl = new InventoryGiftReceiptbl();

    }

    @Override
    public void addInventoryGiftReceipt(List<ListGoodsItemVO> goodsList) throws RemoteException {
        List<ReceiptGoodsItemVO> list = new ArrayList<>();
        for (ListGoodsItemVO vo: goodsList) {
            ReceiptGoodsItemVO receiptVO = new ReceiptGoodsItemVO();
            receiptVO.setGoodsId(vo.getGoodsId());
            receiptVO.setGoodsName(vo.getGoodsName());
            receiptVO.setSendNum(vo.getGoodsNum());

            list.add(receiptVO);
        }

        InventoryGiftReceiptVO receiptVO = giftReceiptbl.getNew();
        receiptVO.setItems(list);
        receiptVO.setOperatorId(UserInfomation.userid);
        giftReceiptbl.update(receiptVO);
    }

    /*
    构造赠送单的时候那些数据还需要再考虑考虑,赠送数量的接口还需要商量
     */
   /* @Override
    public void addInventoryGiftReceipt(List<GoodsPO> goodsList) throws RemoteException {
        List<InventroyGiftGoodsItemVO> list = new ArrayList<>(goodsList.size());

        for (GoodsPO po:goodsList) {
            list.add(new InventroyGiftGoodsItemVO(po.getId(),po.getGoodName(),po.getGoodType(),po.getInventoryNum(),0));
        }

       *//* InventoryGiftReceiptVO receiptVO = new InventoryGiftReceiptVO(null,0,null,null,
                ReceiptState.PENDING,list);
*//*
      //  giftReceiptbl.insert(receiptVO);
    }*/
}
