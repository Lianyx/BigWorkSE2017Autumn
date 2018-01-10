package ui.managerui.businessProgressui;

import blService.businessblservice.BusinessProgressblService;
import blService.checkblService.CheckblService;
import businesslogic.promotionbl.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import org.controlsfx.control.PopOver;
import ui.common.FXMLRefreshableAnchorPane;
import ui.managerui.checkui.CheckTable;
import ui.managerui.common.MyBoardController;
import ui.managerui.promotionui.PromotionFilterPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.Refreshable;
import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BusinessProgressPane extends FXMLRefreshableAnchorPane {
    @FXML
    private JFXButton filter;
    @FXML
    private JFXTextField keywordField;

    private CheckTable checkTable;

    private Set<ReceiptVO> chosenItems = new HashSet<>();
    private BusinessProgressblService businessProgressblService;

    private ReceiptSearchCondition searchCondition = new ReceiptSearchCondition();

    public BusinessProgressPane() {
        checkTable = new CheckTable(chosenItems, keywordField.textProperty(), new ArrayList<>());
        checkTable.setLayoutX(20);
        checkTable.setLayoutY(80);
        this.getChildren().add(checkTable);

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        BusinessProgressFilterPane businessProgressFilterPane = new BusinessProgressFilterPane(this, searchCondition);
        filterPopOver.setContentNode(businessProgressFilterPane);
        filter.setOnAction(e -> filterPopOver.show(filter));
    }

    @Override
    protected String getURL() {
        return "/managerui/businessProgressPane.fxml";
    }

    @Override
    public void refresh(boolean toSwitch) {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();
        ArrayList<ReceiptVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            checkTable.refresh(tempList);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
//            System.out.println("start businessProgressPane refresh");
            try {
                if (businessProgressblService == null) {
                    businessProgressblService = MyblServiceFactory.getService(BusinessProgressblService.class);
                }

//                System.out.println("get businessblService");
                ArrayList<ReceiptVO> receipts;
                if ((receipts = businessProgressblService.search(searchCondition)) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(receipts);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        })).start();
    }
}