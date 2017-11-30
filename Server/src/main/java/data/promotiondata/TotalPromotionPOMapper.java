package data.promotiondata;

import org.apache.ibatis.annotations.Param;
import po.MemberPromotionPO;
import po.TotalPromotionPO;

import java.time.LocalDateTime;
import java.util.List;

public interface TotalPromotionPOMapper {
    int getDayId(@Param("today") LocalDateTime today);
    void insert(TotalPromotionPO promotionPO);
    void update(TotalPromotionPO promotionPO);
    void delete(TotalPromotionPO promotionPO);
    List<TotalPromotionPO> selectInEffect(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);
}
