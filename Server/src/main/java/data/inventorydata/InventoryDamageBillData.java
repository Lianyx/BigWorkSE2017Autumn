package data.inventorydata;

import data.checkdata.ReceiptData;
import dataService.inventorydataService.InventoryDataService;
import po.InventoryDamageBillPO;
import po.ReceiptPO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryDamageBillData implements InventoryDataService {
    ReceiptData<InventoryDamageBillPO> rdao = new ReceiptData<>(InventoryDamageBillPOMapper.class);
    @Override
    public int getDayId() {
        return rdao.getDayId();
    }

    @Override
    public ResultMessage insert(ReceiptPO promotionPO) {
        InventoryDamageBillPO po = (InventoryDamageBillPO) promotionPO;
        rdao.insert(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ReceiptPO promotionPO) {
        InventoryDamageBillPO po = (InventoryDamageBillPO) promotionPO;
        rdao.update(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(ReceiptPO promotionPO) {
        InventoryDamageBillPO po = (InventoryDamageBillPO) promotionPO;
        rdao.delete(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List selectBetween(LocalDateTime begin, LocalDateTime end) {
        List<InventoryDamageBillPO> list = rdao.selectBetween(begin,end);
        return list;
    }

    @Override
    public List selectPending() {
        List<InventoryDamageBillPO> list = rdao.selectPending();
        return list;
    }
}
