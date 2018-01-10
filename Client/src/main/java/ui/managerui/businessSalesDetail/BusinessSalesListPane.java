package ui.managerui.businessSalesDetail;

import blService.businessblservice.SalesDetailblService;
import businesslogic.businessbl.SalesDetailbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import ui.managerui.checkui.CheckTable;
import ui.managerui.common.MyBoardController;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;
import ui.util.Refreshable;
import vo.receiptVO.ReceiptVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BusinessSalesListPane extends Refreshable {
    @FXML
    private JFXButton filter;
    @FXML
    private JFXTextField keywordField;

    private BusinessTreeTable salesDetailTable;

    private Set<ReceiptVO> chosenItems = new HashSet<>();
    private SalesDetailblService salesDetailblService;
    
    public BusinessSalesListPane() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/managerui/businessSalesListPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        salesDetailTable.setLayoutX(20);
        salesDetailTable.setLayoutY(80);
        this.getChildren().add(salesDetailTable);
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

        }, buttonDialog, woid -> {
            return true;
        })).start();
    }
}
