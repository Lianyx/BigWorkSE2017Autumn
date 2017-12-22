package businesslogic.businessbl;

import blService.businessblService.BusinessProgressblService;
import util.BusinessProgressInfo;
import vo.ReceiptVO;

import java.util.List;

public class BusinessProgressController implements BusinessProgressblService{

    private BusinessProgress businessProgress;

    public BusinessProgressController(){
        businessProgress = new BusinessProgress();
    }

    @Override
    public List<ReceiptVO> select(BusinessProgressInfo businessProgressInfo){

        return null;
    }

}
