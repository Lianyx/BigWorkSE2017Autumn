package blServiceDriver.establishblservice_Driver;

import blService.establishblService.EstablishblService;
import blServiceStub.establishblservice_Stub.EstablishblService_Stub;

public class EstablishblService_Driver {
    EstablishblService establishblService = new EstablishblService_Stub();
    public void drive(){
        establishblService.init();

        System.out.println("success");
    }


}
