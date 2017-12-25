package po.promotionPO;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class PromotionPO implements Serializable {
    private int dayId;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    private String comment;

    public PromotionPO() {
    }

    public PromotionPO(int dayId, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment) {
        this.dayId = dayId;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}