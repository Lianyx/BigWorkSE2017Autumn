package businesslogic.promotionbl;

import blService.promotionblService.PromotionListblService;
import blService.promotionblService.PromotionblService;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.TotalPromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PromotionFactory {
    private static PromotionblService<CombinePromotionVO> combinePromotionblSerivce;
    private static PromotionblService<MemberPromotionVO> memberPromotionblService;
    private static PromotionblService<TotalPromotionVO> totalPromotionblService;
    private static PromotionListblService promotionListblService;

    public synchronized static PromotionblService<CombinePromotionVO> getCombinePromotionblSerivce() throws RemoteException, NotBoundException, MalformedURLException {
        if (combinePromotionblSerivce == null) {
            return combinePromotionblSerivce = new CombinePromotionbl();
        }
        return combinePromotionblSerivce;
    }

    public synchronized static PromotionblService<MemberPromotionVO> getMemberPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
        if (memberPromotionblService == null) {
            return memberPromotionblService = new MemberPromotionbl();
        }
        return memberPromotionblService;
    }

    public synchronized static PromotionblService<TotalPromotionVO> getTotalPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
        if (totalPromotionblService == null) {
            return totalPromotionblService = new TotalPromotionbl();
        }
        return totalPromotionblService;
    }

    public synchronized static PromotionListblService getPromotionListblService() throws RemoteException, NotBoundException, MalformedURLException {
        if (promotionListblService == null) {
            return promotionListblService = new PromotionListbl();
        }
        return promotionListblService;
    }
}
