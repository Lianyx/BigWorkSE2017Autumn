package businesslogic.promotionbl;

import blService.promotionblService.PromotionblService;
import dataService.promotiondataService.PromotionDataService;
import po.promotionPO.PromotionPO;
import util.ResultMessage;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Promotionbl<TV extends PromotionVO, TP extends PromotionPO> implements PromotionblService<TV> {
    private PromotionDataService<TP> promotionDataService;

    public Promotionbl(String className) throws RemoteException, NotBoundException, MalformedURLException {
        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        promotionDataService = (PromotionDataService<TP>) Naming.lookup(registrationpre + "/" + className);
    }

    /** implements promotionService */
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
        return null;
    }

    public static void main(String[] args) {
        System.out.println("fads");
    }

}
