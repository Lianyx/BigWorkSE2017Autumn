package blServiceDriver.accountblservice_Driver;

import blService.accountblService.AccountblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import util.ResultMessage;
import vo.AccountVO;

public class AccountblService_Driver {
    AccountblService accountblService = new AccountblService_Stub();
    public void drive(){
        ResultMessage resultMessage = accountblService.add(new AccountVO());
        if(resultMessage==ResultMessage.SUCCESS){
            System.out.println("success");
        }
    }
}
