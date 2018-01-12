package ui.managerui.businessSalesDetail;

import blService.businessblservice.SalesDetailblService;
import businesslogic.promotionbl.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.PopOver;
import ui.common.FilterableListPane;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import util.ReceiptSearchCondition;
import vo.ListGoodsItemVO;
import vo.receiptVO.ReceiptVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BusinessSalesListPane extends FilterableListPane<ListGoodsItemVO> {
    @FXML
    private JFXButton filter;
    @FXML
    private JFXTextField keywordField;

    private BusinessSalesTreeTable salesDetailTable;

    private Set<ListGoodsItemVO> chosenItems = new HashSet<>();
    private SalesDetailblService salesDetailblService;
    private ReceiptSearchCondition receiptSearchCondition;
    
    public BusinessSalesListPane() {
    }

    @Override
    protected String getURL() {
        return "/managerui/businessSalesListPane.fxml";
    }

    @Override
    protected void initiateService() throws RemoteException, NotBoundException, MalformedURLException {
        salesDetailblService = MyblServiceFactory.getService(SalesDetailblService.class);
    }

    @Override
    protected MyTreeTableBorderPane<ListGoodsItemVO> getInitialTreeTable() {
        return new BusinessSalesTreeTable(keywordField.textProperty());
    }

    @Override
    protected void initiateFields() {
        super.initiateFields();
        receiptSearchCondition = new ReceiptSearchCondition();
    }

    @Override
    protected ArrayList<ListGoodsItemVO> getNewListData() throws RemoteException {
        return salesDetailblService.searchSalesDetail(receiptSearchCondition);
    }

    @Override
    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
        return null;
    }
}
