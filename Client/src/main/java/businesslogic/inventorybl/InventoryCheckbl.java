package businesslogic.inventorybl;

import blService.businessblservice.BusinessSearchInfo;
import blService.inventoryblService.InventoryCheckblService;
import businesslogic.checkbl.MyServiceFactory;
import util.ReceiptSearchCondition;
import vo.inventoryVO.InventoryCheckVO;
import vo.receiptVO.*;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class InventoryCheckbl implements InventoryCheckblService{
    @Override
    public InventoryCheckVO inventoryCheck() throws RemoteException, NotBoundException, MalformedURLException {



        return null;
    }
}
