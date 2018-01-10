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

public class MyblServiceFactory {
    private static Map<Class<?>, Object> serviceMap = new HashMap<>();

    public synchronized static <TF> TF getService(Class<?> serviceName) {
        if (serviceMap.containsKey(serviceName)) {
            return (TF) serviceMap.get(serviceName);
        }

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

}

