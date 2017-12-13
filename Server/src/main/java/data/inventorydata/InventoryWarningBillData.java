package data.inventorydata;

import data.checkdata.ReceiptData;
import dataService.inventorydataService.InventoryDataService;
import po.InventoryWarningBillPO;
import po.ReceiptPO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryWarningBillData implements InventoryDataService {
    ReceiptData<InventoryWarningBillPO> rdao = new ReceiptData<>(InventoryWarningBillPOMapper.class);
    @Override
    public int getDayId() {
        return rdao.getDayId();
    }

    @Override
    public ResultMessage insert(ReceiptPO promotionPO) {
        InventoryWarningBillPO po = (InventoryWarningBillPO) promotionPO;
        rdao.insert(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ReceiptPO promotionPO) {
        InventoryWarningBillPO po = (InventoryWarningBillPO) promotionPO;
        rdao.update(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(ReceiptPO promotionPO) {
        InventoryWarningBillPO po = (InventoryWarningBillPO) promotionPO;
        rdao.delete(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List selectBetween(LocalDateTime begin, LocalDateTime end) {
        List<InventoryWarningBillPO> list = rdao.selectBetween(begin,end);
        return list;
    }

    @Override
    public List selectPending() {
        List<InventoryWarningBillPO> list = rdao.selectPending();
        return list;
    }
}
