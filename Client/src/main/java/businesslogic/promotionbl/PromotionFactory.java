package businesslogic.promotionbl;

import blService.promotionblService.PromotionListblService;
import blService.promotionblService.PromotionblService;
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

    public synchronized static PromotionblService<CombinePromotionVO> getCombinePromotionblSerivce() throws RemoteException, NotBoundException, MalformedURLException {
//        if (combinePromotionblSerivce == null) {
//            return combinePromotionblSerivce = new CombinePromotionbl();
//        }
//        return combinePromotionblSerivce;
        return getService("CombinePromotionblService");
    }

    public synchronized static PromotionblService<MemberPromotionVO> getMemberPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (memberPromotionblService == null) {
//            return memberPromotionblService = new MemberPromotionbl();
//        }
//        return memberPromotionblService;
        return getService("MemberPromotionblService");
    }

    public synchronized static PromotionblService<TotalPromotionVO> getTotalPromotionblService() throws RemoteException, NotBoundException, MalformedURLException {
//        if (totalPromotionblService == null) {
//            return totalPromotionblService = new TotalPromotionbl();
//        }
//        return totalPromotionblService;
        return getService("TotalPromotionblService");
    }

    public synchronized static PromotionListblService getPromotionListblService() throws RemoteException, NotBoundException, MalformedURLException {
        if (promotionListblService == null) {
            return promotionListblService = new PromotionListbl();
        }
        return promotionListblService;
    }


    private static Map<String, Object> serviceMap = new HashMap<>();

    // 本来想的是：传入某接口.class（即xxyyService（yy表示bl，如果是Data感觉可以写在bl实现类的所有公有类里（也可能要工厂）））
    // 但是有些可能只是范型没有class
    public synchronized static <TF> TF getService(String serviceName) {
        if (serviceMap.containsKey(serviceName)) {
            return (TF) serviceMap.get(serviceName);
        }

        String implName = serviceName.substring(0, serviceName.length() - "Service".length());
        String searchName = "businesslogic" + "."
//                + Character.toLowerCase(impliName.charAt(0)) + impliName.substring(1) + "." // 所以这里是命名的难点
                + "promotionbl" + "."
                + Character.toUpperCase(implName.charAt(0)) + implName.substring(1);
//        System.out.println(searchName);
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
        getService("MemberPromotionblService");
    }
}

