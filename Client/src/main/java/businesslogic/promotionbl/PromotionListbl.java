package businesslogic.promotionbl;

import blService.promotionblService.*;
import util.PromotionSearchCondition;
import util.PromotionType;
import util.ResultMessage;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.PromotionVO;
import vo.promotionVO.TotalPromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PromotionListbl implements PromotionListblService {
    private PromotionblService<CombinePromotionVO> combinePromotionblService;
    private PromotionblService<MemberPromotionVO> memberPromotionblService;
    private PromotionblService<TotalPromotionVO> totalPromotionblService;


    // 和直接从工厂里拿没有区别。
    public PromotionListbl() throws RemoteException, NotBoundException, MalformedURLException {
        this.combinePromotionblService = PromotionFactory.getService(CombinePromotionblService.class);
        this.memberPromotionblService = PromotionFactory.getService(MemberPromotionblService.class);
        this.totalPromotionblService = PromotionFactory.getService(TotalPromotionblService.class);
    }

    @Override
    public ArrayList<PromotionVO> search(PromotionSearchCondition promotionSearchCondition) throws RemoteException {
        ArrayList<PromotionVO> resultList = new ArrayList<>();

        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.COMBINE)) {
            ArrayList<CombinePromotionVO> cps = combinePromotionblService.search(promotionSearchCondition);
            resultList.addAll(cps);
        }
        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.MEMBER)) {
            ArrayList<MemberPromotionVO> mps = memberPromotionblService.search(promotionSearchCondition);
            resultList.addAll(mps);
        }
        if (promotionSearchCondition.getPromotionTypes().contains(PromotionType.TOTAL)) {
            ArrayList<TotalPromotionVO> tps = totalPromotionblService.search(promotionSearchCondition);
            resultList.addAll(tps);
        }

        return resultList;
    }

    @Override
    public ResultMessage delete(PromotionVO promotionVO) throws RemoteException, MalformedURLException, NotBoundException {
        return promotionVO.getService().delete(promotionVO);
    }

}
