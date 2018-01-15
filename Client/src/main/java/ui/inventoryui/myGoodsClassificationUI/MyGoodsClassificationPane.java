package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.MyblServiceFactory;
import ui.common.bigPane.GatePane;
import vo.inventoryVO.MyGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyGoodsClassificationPane extends GatePane {
    private MyGoodsClasssificationTreeTableView myGoodsClasssificationTreeTableView;
    private MyGoodsClassificationVO root;

    private MyGoodsClassificationblService myGoodsClassificationblService;

    @Override
    protected void refreshAfterMath() {
        myGoodsClasssificationTreeTableView.refresh(root);
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        myGoodsClassificationblService = MyblServiceFactory.getService(MyGoodsClassificationblService.class);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        root = myGoodsClassificationblService.selectRoot();
    }

    @Override
    protected String getURL() {
        return "/inventoryui/goodui/myGoodsClassificationPane.fxml";
    }
}
