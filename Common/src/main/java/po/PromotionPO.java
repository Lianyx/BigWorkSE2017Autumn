package po;

import java.time.LocalDateTime;

public abstract class PromotionPO {
    private int id;

    private LocalDateTime createTime;
    private LocalDateTime lastModifiedTime;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;

    public PromotionPO() {
    }

    public PromotionPO(int id, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime) {
        this.id = id;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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