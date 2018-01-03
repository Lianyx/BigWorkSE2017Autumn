package blServiceStub.inventoryblService_Stub;

import blService.inventoryblService.InventoryShowblService;
import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryWarningReceiptPO;
import vo.inventoryVO.InventoryCheckItemVO;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryViewVO;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InventoryblService_Stub3 implements InventoryShowblService {
    Set<InventoryCheckItemVO> set =  new TreeSet<>();

    public InventoryblService_Stub3() {
        set.add(new InventoryCheckItemVO("123","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("124","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("125","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("126","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("127","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("128","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("129","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("130","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("131","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("132","中国灯",99,56.8,"2017/1/1","2","3"));
        set.add(new InventoryCheckItemVO("133","中国灯",99,56.8,"2017/1/1","2","3"));
    }

    @Override
    public InventoryViewVO inventoryView(String beginDate, String endDate) {
        return null;
    }

    @Override
    public InventoryCheckVO inventoryCheck() {
        InventoryCheckVO vo = new InventoryCheckVO(set);
        return vo;
    }

    @Override
    public List<InventoryGiftReceiptPO> showGiftReceiptOfApproved() {
        return null;
    }

    @Override
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfApproved() {
        return null;
    }

    @Override
    public List<InventoryDamageReceiptPO> showDamageReceiptOfApproved() {
        return null;
    }

    @Override
    public List<InventoryWarningReceiptPO> showWarningReceiptOfApproved() {
        return null;
    }

    @Override
    public List<InventoryGiftReceiptPO> showGiftReceiptOfPendding() {
        return null;
    }

    @Override
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfPendding() {
        return null;
    }

    @Override
    public List<InventoryDamageReceiptPO> showDamageReceiptOfPendding() {
        return null;
    }

    @Override
    public List<InventoryWarningReceiptPO> showWarningReceiptOfPendding() {
        return null;
    }

    @Override
    public List<InventoryGiftReceiptPO> showGiftReceiptOfDarft() {
        return null;
    }

    @Override
    public List<InventoryOverflowReceiptPO> showOverflowReceiptOfDarft() {
        return null;
    }

    @Override
    public List<InventoryDamageReceiptPO> showDamageReceiptOfDarft() {
        return null;
    }

    @Override
    public List<InventoryWarningReceiptPO> showWarningReceiptOfDarft() {
        return null;
    }
}
