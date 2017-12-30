package blServiceStub.inventoryblService_Stub;

import blService.inventoryblService.InventoryShowblService;
import blService.inventoryblService.InventoryblService;
import po.receiptPO.InventoryDamageReceiptPO;
import po.receiptPO.InventoryGiftReceiptPO;
import po.receiptPO.InventoryOverflowReceiptPO;
import po.receiptPO.InventoryWarningReceiptPO;
import util.ReceiptSearchCondition;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.inventoryVO.InventoryCheckVO;
import vo.inventoryVO.InventoryReceiptVO;
import vo.inventoryVO.InventoryViewItemVO;
import vo.inventoryVO.InventoryViewVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class InventoryblService_Stub2 implements InventoryShowblService {
    TreeSet<InventoryViewItemVO> set=new TreeSet<>();

    public InventoryblService_Stub2(){
        set.add(new InventoryViewItemVO("123","111",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("124","112",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("125","113",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("126","114",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("127","115",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("128","116",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("129","117",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("130","118",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("131","119",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("132","120",10,20.2,30,30.3,40,40.4,50,50.5));
        set.add(new InventoryViewItemVO("133","121",10,20.2,30,30.3,40,40.4,50,50.5));


    }

    @Override
    public InventoryViewVO inventoryView(String beginDate, String endDate) {
        InventoryViewVO inventoryViewVO = new InventoryViewVO();
        inventoryViewVO.setViewList(set);
        return inventoryViewVO;
    }

    @Override
    public InventoryCheckVO inventoryCheck() {
        return null;
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
