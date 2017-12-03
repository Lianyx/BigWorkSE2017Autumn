package dataService.checkdataService;

import po.ReceiptPO;
import util.ResultMessage;

import java.time.LocalDateTime;
import java.util.List;

public interface ReceiptDataService<T extends ReceiptPO> {

    int getDayId();

    ResultMessage insert(T promotionPO);

    ResultMessage update(T promotionPO);

    ResultMessage delete(T promotionPO);

    List<T> selectBetween(LocalDateTime begin, LocalDateTime end);
    List<T> selectPending();
}
