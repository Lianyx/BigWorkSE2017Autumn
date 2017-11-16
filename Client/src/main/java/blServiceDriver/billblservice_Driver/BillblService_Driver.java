package blServiceDriver.billblservice_Driver;

import blService.billblService.BillblService;
import blServiceStub.billblservice_Stub.BillblService_Stub;
import util.ResultMessage;
import vo.BillVO;

public class BillblService_Driver {
    BillblService billblService = new BillblService_Stub();
    public void drive(){
        ResultMessage resultMessage = billblService.add(new BillVO());
        if(resultMessage==ResultMessage.SUCCESS){
            System.out.println("success");
        }
    }

}
