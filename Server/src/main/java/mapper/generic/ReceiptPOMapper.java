package mapper.generic;

import org.apache.ibatis.annotations.Param;
import util.ReceiptState;
import util.ReceiptSearchCondition;
import util.RespectiveReceiptSearchCondition;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface ReceiptPOMapper<T> {
    int getDayId(@Param("today") LocalDateTime today);
    void insert(T receiptPO);
    void update(T receiptPO);
    void delete(T receiptPO);
    ArrayList<T> selectBetween(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
    ArrayList<T> selectByState(@Param("receiptState") ReceiptState receiptState);
    ArrayList<T> searchForBusiness(ReceiptSearchCondition receiptSearchCondition);
    ArrayList<T> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition);
}
