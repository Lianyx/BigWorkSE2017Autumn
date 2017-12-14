package businesslogic.salesbl;

import blService.checkblService.CheckInfo;
import blService.salesblService.SalesSellblService;
import dataService.checkdataService.ReceiptDataService;
import po.ReceiptPO;
import po.SalesSellReceiptPO;
import util.ReceiptState;
import util.ResultMessage;
import vo.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class SalesSellbl implements SalesSellblService, CheckInfo {
    private ReceiptDataService<SalesSellReceiptPO> receiptDataService;
    private Class<? extends ReceiptPO> receiptPOClass;

    public SalesSellbl(Class<? extends ReceiptPO> receiptPOClass) throws RemoteException, NotBoundException, MalformedURLException {
        this.receiptPOClass = receiptPOClass;

        String registry = "localhost";
        int port = 1099;
        String registrationpre = "rmi://" + registry + ":" + port;

        receiptDataService = (ReceiptDataService<SalesSellReceiptPO>) Naming.lookup(registrationpre + "/SalesSellReceiptData");
    }

    @Override
    public <T extends ReceiptPO> ResultMessage update(T receiptPO) throws RemoteException {
        if (!receiptPOClass.isInstance(receiptPO)) {
            // TODO exception还是？（还有下面的approve）
            return ResultMessage.FAIL;
        }
        return null;
    }

    @Override
    public <T extends ReceiptPO> ResultMessage approve(T receiptPO) throws RemoteException {
        if (!receiptPOClass.isInstance(receiptPO)) {
            return ResultMessage.FAIL;
        }
        receiptPO.setReceiptState(ReceiptState.APPROVED);
        receiptDataService.update(castBack(receiptPO));

        // TODO 更新其他数据

        return ResultMessage.SUCCESS;
    }

    @Override
    public ArrayList<? extends ReceiptPO> selectPending() throws RemoteException {
        return receiptDataService.selectByState(ReceiptState.PENDING);
    }


    @Override
    public int getDayId() throws RemoteException {
        return receiptDataService.getDayId();
    }

    @Override
    public ResultMessage insert(SalesSellReceiptVO receiptVO) {
        return null;
    }

    @Override
    public ResultMessage update(SalesSellReceiptVO receiptVO) {
        return null;
    }

    @Override
    public ResultMessage delete(SalesSellReceiptVO receiptVO) {
        return null;
    }


    // TODO
    public static <T> T castBack(Object o) {
        try {
            T rv = (T) o;
            return rv;
        } catch (java.lang.ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        CheckInfo salesSellblService = new SalesSellbl(ReceiptPO.class);
        salesSellblService.selectPending();
        System.out.println("fdas");
    }
}
