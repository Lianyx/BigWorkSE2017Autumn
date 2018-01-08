package data.businessdata;

import po.BusinessConditionPO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public class TestBusinessCondition {
    public static void main(String[] args) throws RemoteException {
        BusinessConditionData bdao = new BusinessConditionData();

        BusinessConditionPO bpo = new BusinessConditionPO();
        bpo.setDate(LocalDateTime.now());
        bpo.setGiftCost(5);

        bdao.insert(bpo);

        System.out.println("end main");

        System.out.println(bdao.select(LocalDateTime.now().minusDays(3), LocalDateTime.now()));
    }
}
