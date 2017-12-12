package data.checkdata;

import org.apache.ibatis.annotations.Param;
import util.ReceiptState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ReceiptPOMapper<T> {
    int getDayId(@Param("today") LocalDateTime today);
    void insert(T receiptPO);
    void update(T receiptPO);
    void delete(T receiptPO);
    List<T> selectBetween(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
    ArrayList<T> selectByState(@Param("receiptState") ReceiptState receiptState);
}
