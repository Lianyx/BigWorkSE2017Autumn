package ui.inventoryui.goodsclassificationui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import businesslogic.goodsClassificationbl.GoodsClassificationbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import ui.common.MyBoardController;
import ui.util.BoardController;
import ui.util.Refreshable;
import vo.inventoryVO.GoodsClassificationVO;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.util.List;

public class GoodsClassificationPane extends Refreshable{
    @FXML
    BorderPane borderpane;

    GoodsClassificationTreeView treeView;

    List<GoodsClassificationVO> list;

    BoardController boardController;

    public static GoodsClassificationblService goodsClassficationblService;

    StackPane mainpane;


    public boolean historyAdd = false;

    public GoodsClassificationPane() {
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodui/goodsClassificationPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public GoodsClassificationPane(BoardController boardController, StackPane mainpane) throws IOException, NotBoundException {
        this();
        this.boardController = boardController;
        this.mainpane = mainpane;

     //  goodsClassficationblService = ServiceFactory_Stub.getService(GoodsClassificationblService.class.getName());
        goodsClassficationblService = new GoodsClassificationbl();

        treeView = new GoodsClassificationTreeView();
        treeView.setBoardController(boardController);
        treeView.setMainpane(mainpane);
        treeView.setGoodsClassificationblService(goodsClassficationblService);
        treeView.setGoodsClassificationVO(goodsClassficationblService.show());

        StackPane stackPane = (StackPane) treeView.getPane();
        borderpane.setCenter(stackPane);
    }

    public void setGoodsClassficationblService(GoodsClassificationblService goodsClassficationblService) {
        this.goodsClassficationblService = goodsClassficationblService;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    /**
     * 要将fxml所有的命名空间表示出来
     */

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            LoadingTask task = new LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            treeView.setGoodsClassificationVO(goodsClassficationblService.show());
                            borderpane.setCenter((StackPane)treeView.getPane());
//                            switchPane(toSwitch);
                            MyBoardController.getMyBoardController().switchTo(GoodsClassificationPane.this);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (task.getIntegerProperty() == 0) {
                        try {
                            JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
                            jfxDialogLayout.setHeading(new Label("Wrong"));
                            jfxDialogLayout.setBody(new Label("sabi"));
                            JFXButton button = new JFXButton("Last");
                            JFXButton re = new JFXButton("Re");
                            JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);
                            button.setOnAction(e -> {
                                dialog.close();
                                boardController.Ret();
                            });
                            re.setOnAction(e -> {
                                dialog.close();
                                refresh(false);
                            });
                            jfxDialogLayout.setActions(button, re);
                            dialog.show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            new Thread(task).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class LoadingTask extends Task<Boolean> {

        private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);


        public int getIntegerProperty() {
            return integerProperty.get();
        }

        public SimpleIntegerProperty integerPropertyProperty() {
            return integerProperty;
        }

        @Override
        protected Boolean call() throws Exception {
            list = goodsClassficationblService.show();
            if (list != null) {
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            } else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }
}
