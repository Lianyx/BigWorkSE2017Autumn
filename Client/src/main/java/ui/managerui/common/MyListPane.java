package ui.managerui.common;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.fxml.FXML;
import ui.common.FXMLRefreshableAnchorPane;
import ui.managerui.common.treeTableRelated.MyTreeTableBorderPane;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.PaneFactory;

import java.util.ArrayList;

public abstract class MyListPane<TR extends RecursiveTreeObject<TR>> extends FXMLRefreshableAnchorPane {
    @FXML
    protected JFXTextField keywordField;

    protected MyTreeTableBorderPane<TR> receiptListTreeTable; // 不应该叫这个名字的，但是为了向下兼容…


    private boolean firstLoad = true;

    public MyListPane() {
    }

    @Override
    public void refresh(boolean toSwitch) {
        MyBoardController myBoardController = MyBoardController.getMyBoardController();
        myBoardController.Loading();
        ArrayList<TR> tempList = new ArrayList<>();

        DoubleButtonDialog buttonDialog = new DoubleButtonDialog(PaneFactory.getMainPane(), "Wrong", "连接失败", "重试", "返回");
        buttonDialog.setButtonOne(() -> refresh(false));
        buttonDialog.setButtonTwo(myBoardController::Ret);

        new Thread(new GetTask(() -> {
            receiptListTreeTable.refresh(tempList);
            myBoardController.switchTo(this);
        }, buttonDialog, woid -> {
            if (firstLoad) {
                initiateService();
                firstLoad = false;
            }

            ArrayList<TR> newData = null;
            if ((newData = getNewData()) == null) {
                return false;
            }
            tempList.clear();
            return false;
        })).start();
    }

    protected abstract void initiateService();
    protected abstract ArrayList<TR> getNewData();

}
