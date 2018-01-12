package ui.managerui.promotionui;

import blService.promotionblService.PromotionListblService;
import businesslogic.promotionbl.MyblServiceFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.PopOver;
import ui.common.FilterableListPane;
import ui.common.ListPane;
import ui.managerui.common.MyBoardController;
import ui.managerui.common.MyTwoButtonDialog;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.managerui.promotionui.addPopUpRelated.CombineLabel;
import ui.managerui.promotionui.addPopUpRelated.MemberLabel;
import ui.managerui.promotionui.addPopUpRelated.TotalLabel;
import ui.managerui.promotionui.promotionDetailPane.PromotionTreeTableBorderPane;
import ui.util.*;
import vo.PromotionSearchVO;
import vo.promotionVO.PromotionVO;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PromotionListPane extends FilterableListPane<PromotionVO> { // TODO Refreshable改成接口吧？
    @FXML
    private JFXButton add;
    @FXML
    private JFXTextField keywordField;

    private PromotionListblService promotionListblService;

    private Set<PromotionVO> chosenItems;
    private PromotionSearchVO promotionSearchVO;

//    private ArrayList<PromotionVO> keywordFilterList = new ArrayList<>();

    public PromotionListPane() {
        VBox addList = new VBox();
        addList.setPrefHeight(130);
        addList.setPrefWidth(100);
        addList.getChildren().addAll(new TotalLabel(), new MemberLabel(), new CombineLabel());
        JFXPopup addPopup = new JFXPopup(addList);
        add.setOnAction(event -> addPopup.show(add));
    }


    /**
     * implement methods
     * */

    @Override
    protected String getURL() {
        return "/managerui/promotionListPane.fxml";
    }

    @Override
    protected AnchorPane getInitialFilterPane(PopOver filterPopOver) {
        return new PromotionFilterPane(this, promotionSearchVO);
    }

    @Override
    protected MyTreeTableBorderPane<PromotionVO> getInitialTreeTable() {
        return new PromotionTreeTableBorderPane(chosenItems, keywordField.textProperty());
    }

    @Override
    protected void initiateService() {
        promotionListblService = MyblServiceFactory.getService(PromotionListblService.class);
    }

    @Override
    protected ArrayList<PromotionVO> getNewListData() throws RemoteException {
        return promotionListblService.search(promotionSearchVO.toSC());
    }

    /**
     * override methods
     * */

    @Override
    protected void initiateFields() {
        super.initiateFields();
        promotionSearchVO = new PromotionSearchVO();
    }

    /**
     * FXML related
     * */

    @FXML
    private void SearchKeyword() {
    }

    @FXML
    public void deleteList() {
        new MyTwoButtonDialog("请确认删除", this::deleteTask).show();
    }

    private void deleteTask() {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();
        ArrayList<PromotionVO> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(this::deleteList);
        buttonDialog.setButtonTwo(myBoardController::Ret);

        GetTask getTask = new GetTask(() -> {
            receiptListTreeTable.refresh(tempList);
            myBoardController.switchTo(this);

        }, buttonDialog, woid -> {
            try {
                for (PromotionVO chosenItem : new ArrayList<>(chosenItems)) {
                    chosenItem.getService().delete(chosenItem);
                    chosenItems.remove(chosenItem);
                }

                ArrayList<PromotionVO> promotions;
                if ((promotions = promotionListblService.search(promotionSearchVO.toSC())) == null) {
                    return false;
                }
                tempList.clear();
                tempList.addAll(promotions);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(getTask).start();
    }


}
