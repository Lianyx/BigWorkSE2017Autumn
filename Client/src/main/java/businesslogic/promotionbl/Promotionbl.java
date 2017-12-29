package businesslogic.promotionbl;

import blService.promotionblService.PromotionblService;
import dataService.promotiondataService.PromotionDataService;
import po.promotionPO.CombinePromotionPO;
import po.promotionPO.PromotionGoodsItemPO;
import po.promotionPO.PromotionPO;
import po.receiptPO.ReceiptPO;
import util.PromotionSearchCondition;
import util.ResultMessage;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.PromotionVO;
import vo.receiptVO.ReceiptVO;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Promotionbl<TV extends PromotionVO, TP extends PromotionPO> implements PromotionblService<TV> {
    private Class<? extends PromotionPO> promotionPOClass;
    private Class<? extends PromotionVO> promotionVOClass;

    private PromotionDataService<TP> promotionDataService;

    public Promotionbl(Class<? extends PromotionVO> promotionVOClass, Class<? extends PromotionPO> promotionPOClass, String className) throws RemoteException, NotBoundException, MalformedURLException {
        this.promotionVOClass = promotionVOClass;
        this.promotionPOClass = promotionPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        promotionDataService = (PromotionDataService<TP>) Naming.lookup(registrationpre + "/" + className);
    }

    /**
     * implements promotionService
     */
    @Override
    public int getDayId() throws RemoteException {
        return promotionDataService.getDayId();
    }

    @Override
    public ResultMessage insert(TV promotionVO) throws RemoteException {
        return promotionDataService.insert(promotionVO.toPO());
    }

    @Override
    public ResultMessage update(TV promotionVO) throws RemoteException {
        return promotionDataService.update(promotionVO.toPO());
    }

    @Override
    public ResultMessage delete(TV promotionVO) throws RemoteException {
        return promotionDataService.delete(promotionVO.toPO());
    }

    @Override
    public ArrayList<TV> selectInEffect() throws RemoteException {
        return promotionDataService.selectInEffect().stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<TV> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException {
        return promotionDataService.search(promotionSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * private methods
     */

    protected TV convertToVO(TP promotionPO) {
        Constructor<? extends PromotionVO> cstr = null;
        try {
            cstr = promotionVOClass.getConstructor(promotionPOClass);
            return (TV) (cstr.newInstance(promotionPO));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        CombinePromotionbl combinePromotionbl = new CombinePromotionbl();
        ArrayList<CombinePromotionVO> cvos = combinePromotionbl.selectInEffect();

        CombinePromotionPO cpo1 = new CombinePromotionPO();

        cpo1.setDayId(combinePromotionbl.getDayId());
        cpo1.setCreateTime(LocalDateTime.now());
        cpo1.setBeginTime(LocalDateTime.of(2017, 11, 1, 0, 0));
        cpo1.setEndTime(LocalDateTime.of(2017, 12, 30, 0, 0));
        cpo1.setDiscountAmount(40);
        cpo1.setGoodsCombination(new PromotionGoodsItemPO[]{new PromotionGoodsItemPO("0", 10), new PromotionGoodsItemPO("1", 2)});


        CombinePromotionVO cv1 = new CombinePromotionVO(cpo1);

        CombinePromotionVO cv2 = combinePromotionbl.convertToVO(cpo1);

        System.out.println("fads");
    }

}
