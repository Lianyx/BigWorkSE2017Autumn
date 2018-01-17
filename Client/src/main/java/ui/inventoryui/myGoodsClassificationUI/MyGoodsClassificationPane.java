package ui.inventoryui.myGoodsClassificationUI;

import blService.goodsClassificationblService.MyGoodsClassificationblService;
import businesslogic.blServiceFactory.MessageObjectFactory;
import businesslogic.blServiceFactory.MyblServiceFactory;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import network.ServerInterface;
import ui.common.bigPane.GatePane;
import vo.inventoryVO.MyGoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyGoodsClassificationPane extends GatePane {
    @FXML
    private JFXTextField keywordField;

    private MyGoodsClasssificationTreeTableView myGoodsClasssificationTreeTableView;
    private MyGoodsClassificationVO root;

    private MyGoodsClassificationblService myGoodsClassificationblService;

    public MyGoodsClassificationPane() {
        myGoodsClasssificationTreeTableView = new MyGoodsClasssificationTreeTableView();
        myGoodsClasssificationTreeTableView.setLayoutX(50);
        myGoodsClasssificationTreeTableView.setLayoutY(60);
        this.getChildren().add(myGoodsClasssificationTreeTableView);



        // TODO 下面这几行仅供测试
        Button testButton = new Button("测试消息功能用的");
        testButton.setOnAction(e -> {
            try {
                ServerInterface serverInterface = MessageObjectFactory.getServerInterface();
                serverInterface.send("测试一下而己");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        testButton.setLayoutX(100);
        testButton.setLayoutY(40);
        this.getChildren().add(testButton);
    }

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


    @Override
    protected void initiateFields() {
        super.initiateFields();
    }
}
