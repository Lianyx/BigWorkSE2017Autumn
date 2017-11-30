package data.promotiondata;

import org.apache.ibatis.annotations.Param;
import po.MemberPromotionPO;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberPromotionPOMapper {
    void insert(MemberPromotionPO promotionPO);
    void update(MemberPromotionPO promotionPO);
    void delete(MemberPromotionPO promotionPO);
    List<MemberPromotionPO> selectInEffect(@Param("begin") LocalDateTime begin, @Param("end") LocalDateTime end);

    // for test
    List<MemberPromotionPO> getAll();
}
