package ui.common;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import vo.abstractVO.ReceipishVO;
import vo.promotionVO.PromotionVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class ReceipishPane<TR extends ReceipishVO<TR>> extends FilterableListPane<TR> {
    protected Set<PromotionVO> chosenItems;

//    @Override
//    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
//        return null;
//    }



    /**
     * To be overriden
     * */
    @Override
    protected void initiateFields() {
        super.initiateFields();
        chosenItems = new HashSet<>();
    }


    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {

    }

    @Override
    protected void initiateTreeTable() {

    }

    @Override
    protected ArrayList<TR> getNewListData() throws RemoteException {
        return null;
    }

    @Override
    protected String getURL() {
        return null;
    }
}
