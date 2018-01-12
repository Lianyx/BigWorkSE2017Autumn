package ui.common;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public abstract class ListPane<TR extends RecursiveTreeObject<TR>> extends GatePane {
    @FXML
    protected JFXTextField keywordField;

    protected MyTreeTableBorderPane<TR> receiptListTreeTable; // 不应该叫这个名字的，但是为了向下兼容…


    private ArrayList<TR> tempList; // 给updateDataFromBl用的

    public ListPane() {
        initiateTreeTable();
        receiptListTreeTable.setLayoutX(20);
        receiptListTreeTable.setLayoutY(80);
        this.getChildren().add(receiptListTreeTable);
    }

    /**
     * abstract methods
     */
    protected abstract void initiateService() throws RemoteException, NotBoundException, MalformedURLException;

    protected abstract void initiateTreeTable();

    protected abstract ArrayList<TR> getNewListData() throws RemoteException;

    /**
     * implement methods
     */

    @Override
    protected void refreshAfterMath() {
        receiptListTreeTable.refresh(tempList);
    }

    @Override
    protected void updateDataFromBl() throws RemoteException {
        ArrayList<TR> newData = null;
        if ((newData = getNewListData()) == null) {
            throw new RemoteException("return null from bl");
        }
        tempList.clear();
        tempList.addAll(newData);
    }

    /**
     * override methods
     * */
    @Override
    protected void initiateFields() {
        super.initiateFields();
        tempList = new ArrayList<>();
    }
}
