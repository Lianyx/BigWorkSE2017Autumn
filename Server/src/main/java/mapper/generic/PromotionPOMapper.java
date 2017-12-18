package mapper.generic;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PromotionPOMapper<T> {
    int getDayId(@Param("today") LocalDateTime today);
    void insert(T promotionPO);
    void update(T promotionPO);
    void delete(T promotionPO);
    List<T> selectInEffect(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
}
