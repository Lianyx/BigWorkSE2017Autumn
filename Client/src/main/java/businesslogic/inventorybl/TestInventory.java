package businesslogic.inventorybl;

import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import vo.inventoryVO.inventoryReceiptVO.InventoryGiftReceiptVO;
import vo.inventoryVO.inventoryReceiptVO.ReceiptGoodsItemVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TestInventory {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
       /* InventoryGiftReceiptbl giftReceiptbl = new InventoryGiftReceiptbl();


        List<ReceiptGoodsItemVO> list = new ArrayList<>();
        list.add(new ReceiptGoodsItemVO("121","花灯1","中国灯",12,23,12,23));
        list.add(new ReceiptGoodsItemVO("122","花灯2","中国灯",12,23,12,23));
        list.add(new ReceiptGoodsItemVO("123","花灯3","中国灯",12,23,12,23));
        list.add(new ReceiptGoodsItemVO("124","花灯4","中国灯",12,23,12,23));
        InventoryGiftReceiptVO vo = giftReceiptbl.getNew();
        vo.setOperatorId(123123);
        vo.setLastModifiedTime(LocalDateTime.now());
        vo.setReceiptState(ReceiptState.PENDING);
        //vo.setClerkName("李明");
        vo.setItems(list);
        vo.setComment("无jfukfk");
        giftReceiptbl.update(vo);*/
       InventoryDamageReceiptbl damageReceiptbl = new InventoryDamageReceiptbl();
        System.out.println(damageReceiptbl.search(new RespectiveReceiptSearchCondition()).toString());
    }
}
