package data.inventorydata;

import data.checkdata.ReceiptData;
import dataService.inventorydataService.InventoryDataService;
import po.InventoryBillPO;
import po.InventoryGiftBillPO;
import po.ReceiptPO;
import util.ReceiptState;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.List;

public class InventoryGiftBiiData implements InventoryDataService {
    ReceiptData<InventoryGiftBillPO> rdao = new ReceiptData<>(InventoryGiftBiiPOMapper.class);
    @Override
    public int getDayId() {
        return rdao.getDayId();
    }

    @Override
    public ResultMessage insert(ReceiptPO promotionPO) {
        InventoryGiftBillPO po = (InventoryGiftBillPO) promotionPO;
        rdao.insert(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(ReceiptPO promotionPO) {
        InventoryGiftBillPO po = (InventoryGiftBillPO) promotionPO;
        rdao.update(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(ReceiptPO promotionPO) {
        InventoryGiftBillPO po = (InventoryGiftBillPO) promotionPO;
        rdao.delete(po);
        return ResultMessage.SUCCESS;
    }

    @Override
    public List selectBetween(LocalDateTime begin, LocalDateTime end) {
        List<InventoryGiftBillPO> list = rdao.selectBetween(begin,end);
        return list;
    }

    @Override
    public List selectPending() {
        List<InventoryGiftBillPO> list = rdao.selectPending();
        return list;
    }
}
