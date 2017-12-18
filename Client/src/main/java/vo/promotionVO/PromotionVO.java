package vo.promotionVO;

import po.promotionPO.PromotionPO;

import java.time.LocalDateTime;

public abstract class PromotionVO {
    private int dayId;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    public abstract <T extends PromotionPO> T toPO();
}
