package businesslogic.promotionbl;

import blService.promotionblService.*;
import vo.promotionVO.CombinePromotionVO;
import vo.promotionVO.MemberPromotionVO;
import vo.promotionVO.TotalPromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class PromotionFactory {

    private static PromotionblService<CombinePromotionVO> combinePromotionblSerivce;
    private static PromotionblService<MemberPromotionVO> memberPromotionblService;
    private static PromotionblService<TotalPromotionVO> totalPromotionblService;
    private static PromotionListblService promotionListblService;

    public synchronized static PromotionblService<CombinePromotionVO> getCombinePromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (combinePromotionblSerivce == null) {
//            return combinePromotionblSerivce = new CombinePromotionbl();
//        }
//        return combinePromotionblSerivce;
        return getService(CombinePromotionblService.class);
    }

    public synchronized static PromotionblService<MemberPromotionVO> getMemberPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (memberPromotionblService == null) {
//            return memberPromotionblService = new MemberPromotionbl();
//        }
//        return memberPromotionblService;
        return getService(MemberPromotionblService.class);
    }

    public synchronized static PromotionblService<TotalPromotionVO> getTotalPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (totalPromotionblService == null) {
//            return totalPromotionblService = new TotalPromotionbl();
//        }
//        return totalPromotionblService;
        return getService(TotalPromotionblService.class);
    }

    public synchronized static PromotionListblService getPromotionListblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (promotionListblService == null) {
//            return promotionListblService = new PromotionListbl();
//        }
//        return promotionListblService;
        return getService(PromotionListblService.class);
    }


    private static Map<Class<?>, Object> serviceMap = new HashMap<>();

    // 本来想的是：传入某接口.class（即xxyyService（yy表示bl，如果是Data感觉可以写在bl实现类的所有公有类里（也可能要工厂）））
    // 但是有些可能只是范型没有class
    public synchronized static <TF> TF getService(Class<?> serviceName) {
        if (serviceMap.containsKey(serviceName)) {
            return (TF) serviceMap.get(serviceName);
        }

        String fullName = serviceName.getName();
        String[] nameSplit = serviceName.getName().split("\\.");
        String searchName = "businesslogic" + "."
                + nameSplit[1].substring(0, nameSplit[1].length() - "Service".length()) + "."
                + nameSplit[2].substring(0, nameSplit[2].length() - "Service".length());
        try {
            Class<?> implClass = Class.forName(searchName);
            Object result = implClass.newInstance();
            serviceMap.put(serviceName, result);
            return (TF) result;
        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getService(MemberPromotionblService.class);
    }
}

