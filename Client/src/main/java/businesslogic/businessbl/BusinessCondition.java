package businesslogic.businessbl;

import dataService.accountDataService.AccountDataService;
import dataService.businessdataService.BusinessConditionDataService;
import po.BusinessConditionPO;
import vo.BusinessConditionVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BusinessCondition {

    private BusinessConditionDataService businessConditionDataService;


    public BusinessCondition(){
        try {
            businessConditionDataService = (BusinessConditionDataService) Naming.lookup("rmi://localhost:1099/AccountDataService");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public List<BusinessConditionVO> select(LocalDateTime begin, LocalDateTime end)throws RemoteException{
        List<BusinessConditionPO> POlist = businessConditionDataService.select(begin,end);
        List<BusinessConditionVO> VOlist = new ArrayList<>();
        for(BusinessConditionPO po:POlist){
//            BusinessConditionVO vo = new BusinessConditionVO(po.getIncome(),po.getExpense());
//            VOlist.add(vo);
        }
        return VOlist;
    }
}
