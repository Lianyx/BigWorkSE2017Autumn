package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.ReceiptblService;
import dataService.checkdataService.ReceiptDataService;
import po.receiptPO.ReceiptPO;
import util.ReceiptSearchCondition;
import util.ReceiptState;
import util.ResultMessage;
import vo.ReceiptVO.ReceiptVO;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

// TODO 一定要提取出来“增删改”给所有人用，另外查（分为模糊和非模糊）一定要尽快写，不能再往后拖了
public class Receiptbl<TV extends ReceiptVO, TP extends ReceiptPO> implements ReceiptblService<TV>, CheckInfo<TP> {
    private Class<? extends ReceiptPO> receiptPOClass;
    private Class<? extends ReceiptVO> receiptVOClass;

    private ReceiptDataService<TP> receiptDataService;

    public Receiptbl(Class<? extends ReceiptPO> receiptPOClass, Class<? extends ReceiptVO> receiptVOClass, String className) throws RemoteException, NotBoundException, MalformedURLException {
        this.receiptPOClass = receiptPOClass;
        this.receiptVOClass = receiptVOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        receiptDataService = (ReceiptDataService<TP>) Naming.lookup(registrationpre + "/" + className);
    }


    /** implement receiptblService */

    @Override
    public int getDayId() throws RemoteException {
        return receiptDataService.getDayId();
    }

    @Override
    public ResultMessage insert(TV receiptVO) throws RemoteException {
        return receiptDataService.insert(convertToPO(receiptVO));
    }

    @Override
    public ResultMessage update(TV receiptVO) throws RemoteException {
        return receiptDataService.update(convertToPO(receiptVO));
    }

    @Override
    public ResultMessage delete(TV receiptVO) throws RemoteException {
        return receiptDataService.delete(convertToPO(receiptVO));
    }

    @Override
    public ArrayList<TV> search(ReceiptSearchCondition receiptSearchCondition) throws RemoteException {
//        return receiptDataService.search(receiptSearchCondition).stream().map(this::convertToPO).collect(Collectors.toCollection(ArrayList::new));
        return null;
    }


    @Override
    public ResultMessage update(TP receiptPO) throws RemoteException {
        // TODO
        return receiptDataService.update(receiptPO);
    }

    /** implement checkInfo */

    @Override
    public ResultMessage approve(TP receiptPO) throws RemoteException {
        receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(receiptPO);

        // TODO 更新其他数据

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage reject(TP receiptPO) throws RemoteException {
        return null;
    }

    @Override
    public ArrayList<TP> selectPending() throws RemoteException {
        return receiptDataService.selectByState(ReceiptState.PENDING);
    }


    /** private methods */

    private TP convertToPO(TV receiptVO) {
        Constructor<? extends ReceiptPO> cstr = null;
        try {
            cstr = receiptPOClass.getConstructor(receiptVOClass);
            return (TP) (cstr.newInstance(receiptVO));
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
