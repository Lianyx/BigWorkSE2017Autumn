package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import dataService.checkdataService.ReceiptDataService;
import po.receiptPO.ReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.RespectiveReceiptSearchCondition;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Receiptbl<TV extends ReceiptVO, TP extends ReceiptPO> implements ReceiptblService<TV>, CheckInfo<TP> {
    private Class<? extends ReceiptVO> receiptVOClass;
    private Class<? extends ReceiptPO> receiptPOClass;

    protected ReceiptDataService<TP> receiptDataService;

    public Receiptbl(Class<? extends ReceiptVO> receiptVOClass, Class<? extends ReceiptPO> receiptPOClass, String className) throws RemoteException, NotBoundException, MalformedURLException {
        this.receiptVOClass = receiptVOClass;
        this.receiptPOClass = receiptPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        receiptDataService = (ReceiptDataService<TP>) Naming.lookup(registrationpre + "/" + className);
    }


    /**
     * implement receiptblService
     */

    @Override
    public int getDayId() throws RemoteException {
        return receiptDataService.getDayId();
    }

    @Override
    public ResultMessage insert(TV receiptVO) throws RemoteException {
        return receiptDataService.insert(receiptVO.toPO());
    }

    @Override
    public ResultMessage update(TV receiptVO) throws RemoteException {
        return receiptDataService.update(receiptVO.toPO());
    }

    @Override
    public ResultMessage delete(TV receiptVO) throws RemoteException {
        return receiptDataService.delete(receiptVO.toPO());
    }

    @Override
    public ArrayList<TV> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
        return receiptDataService.search(receiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ArrayList<TV> search(RespectiveReceiptSearchCondition respectiveReceiptSearchCondition) throws RemoteException {
        return receiptDataService.search(respectiveReceiptSearchCondition).stream().map(this::convertToVO).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ResultMessage update(TP receiptPO) throws RemoteException {
        // TODO
        return receiptDataService.update(receiptPO);
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

    @Override
    public ResultMessage reject(TP receiptPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<TP> selectPending() throws RemoteException {
        return receiptDataService.selectByState(ReceiptState.PENDING);
    }


    /**
     * private methods
     */

    private TV convertToVO(TP receiptPO) {
        Constructor<? extends ReceiptVO> cstr = null;
        try {
            cstr = receiptVOClass.getConstructor(receiptPOClass);
            return (TV) (cstr.newInstance(receiptPO));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //    private static <TF> TF castBack(Object o) {
//        try {
//            TF rv = (TF) o;
//            return rv;
//        } catch (java.lang.ClassCastException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
