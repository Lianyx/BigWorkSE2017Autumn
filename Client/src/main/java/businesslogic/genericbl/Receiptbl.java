package businesslogic.genericbl;

import blService.businessblservice.BusinessSearchInfo;
import blService.checkblService.CheckInfo;
import blService.genericblService.ReceiptblService;
import businesslogic.genericbl.Receipishbl;
import businesslogic.salesbl.SalesSellbl;
import dataService.checkdataService.ReceiptDataService;
import dataService.generic.ReceipishDataService;
import po.receiptPO.ReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Receiptbl<TV extends ReceiptVO, TP extends ReceiptPO> extends Receipishbl<TV, TP> implements ReceiptblService<TV>, CheckInfo<TV>, BusinessSearchInfo<TV> {
    private Class<TV> receiptVOClass;
    private Class<TP> receiptPOClass;

    protected ReceiptDataService<TP> receiptDataService; // 如果需要的话，这里可以再传一个Service的范型

    public Receiptbl(Class<TV> receiptVOClass, Class<TP> receiptPOClass) throws RemoteException, NotBoundException, MalformedURLException {
        this.receiptVOClass = receiptVOClass;
        this.receiptPOClass = receiptPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        String poName = receiptPOClass.getName();
        String receiptDataName = poName.substring(poName.lastIndexOf(".") + 1, poName.length() - 2) + "Data";
//        System.out.println(receiptDataName);
        receiptDataService = (ReceiptDataService<TP>) Naming.lookup(registrationpre + "/" + receiptDataName);

    }

    @Override
    protected Class<TV> getVOClass() {
        return receiptVOClass;
    }

    @Override
    protected Class<TP> getPOClass() {
        return receiptPOClass;
    }

    @Override
    protected ReceipishDataService<TP> getDataService() {
        return receiptDataService;
    }

    /**
     * implement receiptblService
     */

    @Override
    public ArrayList<TV> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return receiptDataService.search(receiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<TV> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return receiptDataService.search(respectiveReceiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * implement checkInfo
     */

//    @Override
//    public ResultMessage approve(TP receiptPO) throws RemoteException {
//        receiptPO.setReceiptState(ReceiptState.APPROVED);
//        receiptDataService.update(receiptPO);
//
//         TODO 更新其他数据
//
//        return ResultMessage.SUCCESS;
//    }

    public ResultMessage reject(TV receiptVO) throws RemoteException {
        receiptVO.setReceiptState(ReceiptState.REJECTED);
        return update(receiptVO);
    }

    @Override
    public ArrayList<TV> selectPending() throws RemoteException {
        return receiptDataService.selectByState(ReceiptState.PENDING).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        try {
            new SalesSellbl();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
