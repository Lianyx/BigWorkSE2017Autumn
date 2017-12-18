package businesslogic.businessbl;

import blService.businessblservice.BusinessConditionblService;
import vo.BusinessConditionVO;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class BusinessConditionController implements BusinessConditionblService{

    private BusinessCondition businessCondition;

    public BusinessConditionController(){
        businessCondition = new BusinessCondition();
    }

    @Override
    public List<BusinessConditionVO> select(LocalDateTime begin, LocalDateTime end){
        try {
            return businessCondition.select(begin,end);
        }catch (RemoteException e){
            e.printStackTrace();
            return null;
        }
    }
}
