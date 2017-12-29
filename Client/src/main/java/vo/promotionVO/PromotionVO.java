package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import dataService.promotiondataService.PromotionDataService;
import javafx.scene.layout.Pane;
import po.promotionPO.PromotionPO;
import ui.managerui.promotion.PromotionDetailPane;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

public abstract class PromotionVO extends RecursiveTreeObject<PromotionVO> {
    protected String id;

    protected LocalDateTime createTime, lastModifiedTime, beginTime, endTime;

    protected String comment;



    public PromotionVO() {
    }

    public PromotionVO(String id, LocalDateTime createTime, LocalDateTime lastModifiedTime, LocalDateTime beginTime, LocalDateTime endTime, String comment) {
        this.id = id;
        this.createTime = createTime;
        this.lastModifiedTime = lastModifiedTime;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.comment = comment;
    }

    protected PromotionVO(PromotionPO promotionPO) {
        this.createTime = promotionPO.getCreateTime();
        this.lastModifiedTime = promotionPO.getLastModifiedTime();
        this.beginTime = promotionPO.getBeginTime();
        this.endTime = promotionPO.getEndTime();
        this.comment = promotionPO.getComment();
    }

    public abstract <T extends PromotionPO> T toPO();
    public abstract PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException;
    public abstract PromotionDetailPane getDetailPane();

    // TODO 这个应该和receipt全并
    protected static String formatId(String codeName, PromotionPO promotionPO) {
        LocalDateTime createTime = promotionPO.getCreateTime();
        return String.format(codeName + "-%04d%02d%02d-%05d"
                , createTime.getYear()
                , createTime.getMonthValue()
                , createTime.getDayOfMonth()
                , promotionPO.getDayId());
    }

    protected int idToDayId() {
        return Integer.parseInt(id.substring(id.length() - 5));
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
