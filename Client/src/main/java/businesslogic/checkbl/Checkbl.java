package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.CheckblService;
import businesslogic.salesbl.SalesSellbl;
import po.receiptPO.ReceiptPO;
import po.receiptPO.SalesSellReceiptPO;
import util.ResultMessage;
import vo.receiptVO.ReceiptVO;
import vo.receiptVO.SalesSellReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Checkbl implements CheckblService {
    CheckInfo<SalesSellReceiptVO> salesSellReceiptCheck;

    public Checkbl() throws RemoteException, NotBoundException, MalformedURLException {
        // TODO factory?
        salesSellReceiptCheck = new SalesSellbl();
    }

    @Override
    public ArrayList<ReceiptVO> initCheck() throws RemoteException {
        ArrayList<ReceiptPO> resultList = new ArrayList<>();
//        resultList.addAll(salesSellReceiptCheck.selectPending());
        return null;
    }

    @Override
    public ResultMessage approve(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().approve(receiptVO);
    }

    @Override
    public ResultMessage reject(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().reject(receiptVO);
    }

    @Override
    public ResultMessage update(ReceiptVO receiptVO) throws RemoteException, MalformedURLException, NotBoundException {
        return receiptVO.getService().update(receiptVO);
    }

    public static void main(String[] args) throws Exception{
        Checkbl checkbl = new Checkbl();
        checkbl.initCheck();

    }
}
