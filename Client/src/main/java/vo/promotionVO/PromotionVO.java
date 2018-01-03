package vo.promotionVO;

import blService.promotionblService.PromotionblService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import po.generic.ReceipishPO;
import po.promotionPO.PromotionPO;
import ui.managerui.promotionui.PromotionDetailPane;
import vo.abstractVO.ReceipishVO;
import vo.abstractVO.SelectableVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class PromotionVO extends ReceipishVO<PromotionVO> {
    protected LocalDateTime beginTime, endTime;
    protected String comment;

    public PromotionVO() {
    }

    protected PromotionVO(PromotionPO promotionPO) {
        super(promotionPO);
        this.beginTime = promotionPO.getBeginTime();
        this.endTime = promotionPO.getEndTime();
        this.comment = promotionPO.getComment();
    }

//    public abstract <T extends PromotionPO> T toPO(); // 这个就不用了


    protected <T extends PromotionPO> T toPromotionPO(Class<T> promotionClass) {
        T result = toReceipishPO(promotionClass);
        result.setBeginTime(beginTime);
        result.setEndTime(endTime);
        result.setComment(comment);
        return result;
    }

    public abstract PromotionblService getService() throws RemoteException, NotBoundException, MalformedURLException;

    public abstract PromotionDetailPane getDetailPane();


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
