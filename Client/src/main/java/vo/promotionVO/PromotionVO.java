package vo.promotionVO;

import po.promotionPO.PromotionPO;

import java.time.LocalDateTime;

public abstract class PromotionVO {
//    private int dayId;
    protected String id;

    protected LocalDateTime createTime, lastModifiedTime, beginTime, endTime;

    public abstract <T extends PromotionPO> T toPO();

    public PromotionVO() {
    }

    protected PromotionVO(PromotionPO promotionPO) {
        this.createTime = promotionPO.getCreateTime();
        this.lastModifiedTime = promotionPO.getLastModifiedTime();
        this.beginTime = promotionPO.getBeginTime();
        this.endTime = promotionPO.getEndTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(LocalDateTime lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
