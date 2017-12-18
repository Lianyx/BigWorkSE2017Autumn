package businesslogic.checkbl;

import blService.checkblService.CheckInfo;
import blService.checkblService.CheckblService;
import businesslogic.salesbl.SalesSellbl;
import po.SalesSellReceiptPO;
import util.ResultMessage;
import vo.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Checkbl implements CheckblService {
    CheckInfo salesSellReceiptCheck;

    public Checkbl() throws RemoteException, NotBoundException, MalformedURLException {
        // TODO factory?
        salesSellReceiptCheck = new SalesSellbl();
    }

    @Override
    public ArrayList<ReceiptVO> initCheck() throws RemoteException {
        salesSellReceiptCheck.selectPending().forEach(System.out::println);
        return null;
    }

    @Override
    public ResultMessage approve(ReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage reject(ReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    @Override
    public ResultMessage update(ReceiptVO receiptVO) throws RemoteException {
        return null;
    }

    public static void main(String[] args) throws Exception{
        Checkbl checkbl = new Checkbl();
        checkbl.initCheck();

    }
}
