package ui.managerui.businessSalesDetail;

import blService.businessblservice.SalesDetailblService;
import businesslogic.promotionbl.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import jxl.write.WriteException;
import org.controlsfx.control.PopOver;
import ui.common.FilterableListPane;
import ui.common.mixer.ExcelExportableMixer;
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

public class BusinessSalesListPane extends FilterableListPane<ListGoodsItemVO> implements ExcelExportableMixer {
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
        return new BusinessSalesDetailFilterPane(this, receiptSearchCondition);
    }

    /**
     * implement ExcelExportableMixer
     * */

    @Override
    public String getExcelName() {
        return "销售明细表";
    }

    @Override
    public void writeSheet() throws WriteException {
        myAddCell(0, 0, "时间");
        myAddCell(1, 0, "商品名");
        myAddCell(2, 0, "总价");
        myAddCell(3, 0, "数量");
        myAddCell(4, 0, "单价");
        myAddCell(5, 0, "总价");
    }
}
