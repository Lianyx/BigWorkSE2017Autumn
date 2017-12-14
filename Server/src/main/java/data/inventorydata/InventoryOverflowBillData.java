package data.inventorydata;

import data.checkdata.ReceiptData;
import dataService.inventorydataService.InventoryDataService;
import po.InventoryOverflowBillPO;
import po.ReceiptPO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryOverflowBillData implements InventoryDataService {
    ReceiptData<InventoryOverflowBillPO> rdao = new ReceiptData<>(InventoryOverflowBillPOMapper.class);
    @Override
    public int getDayId() {
        return rdao.getDayId();
    }

    @Override
    public ResultMessage insert(ReceiptPO promotionPO) {
        InventoryOverflowBillPO po = (InventoryOverflowBillPO) promotionPO;
        rdao.insert(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ReceiptPO promotionPO) {
        InventoryOverflowBillPO po = (InventoryOverflowBillPO) promotionPO;
        rdao.update(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(ReceiptPO promotionPO) {
        InventoryOverflowBillPO po = (InventoryOverflowBillPO) promotionPO;
        rdao.delete(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List selectBetween(LocalDateTime begin, LocalDateTime end) {
        List<InventoryOverflowBillPO> list = rdao.selectBetween(begin,end);
        return list;
    }

    @Override
    public List selectPending() {
        List<InventoryOverflowBillPO> list = rdao.selectPending();
        return list;
    }
}
