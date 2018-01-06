package ui.inventoryManagerui.goodsui;

import blService.goodsblService.GoodsblService;
import blServiceStub.goodsblService_Stub.GoodsblService_Stub;
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
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;

import ui.userui.usermanagerui.FilterPane;
import ui.util.BoardController;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import vo.inventoryVO.GoodsVO;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class GoodsListPane extends Refreshable{
    GoodsTreeTable glv = new GoodsTreeTable();

    @FXML
    BorderPane borderpane;

    Set<GoodsVO> set;

    BoardController boardController;

    GoodsblService goodsblService;

    Pagination pagination;

    StackPane mainpane;

   /* private static UserSearchVO userSearchVO = new UserSearchVO();*/

    FilterPane filterPane ;

    public boolean historyAdd = false;

    @FXML
    JFXButton filter;

    public GoodsListPane(GoodsblService goodsblService, BoardController boardController, StackPane mainpane) throws IOException {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodui/goodsListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();

        this.goodsblService = goodsblService;
        setListView();
        this.mainpane = mainpane;
        this.boardController = boardController;
        glv.setBoardController(boardController);


        //这是点击filter按钮后弹出的筛选小窗口，弹出小窗口均可这样做，借鉴！
       /* PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(new FilterPane());
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));*/

        pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(glv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);


    }

    public void setGoodsblService(GoodsblService goodsblService){this.goodsblService = goodsblService;}

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setListView()
    {
        set = new TreeSet<>(goodsblService.show());
        glv.setGoods(set);
    }

    @FXML
    public void deleteList(){
        for(GoodsVO GoodsVO: GoodsChosenItem.getSet()) {
            glv.removeGood(GoodsVO);
            ((GoodsblService_Stub)goodsblService).deleteGoods(GoodsVO);
        }
     /*   int current=pagination.getCurrentPageIndex();
        pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(glv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if(current-1>=0)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);*/
        refreshGlv();
        GoodsChosenItem.getSet().clear();
    }

    public void refreshGlv(){
        int current=pagination.getCurrentPageIndex();
        int max = glv.getObservableList().size() /glv.getRowsPerPage()+1;
        pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
        System.out.println((glv.getObservableList().size() /glv.getRowsPerPage()+1 ));
        pagination.setPageFactory(glv::createPage);
        pagination.setPrefWidth(600);
        borderpane.setCenter(pagination);
        if(current-1>=0&&current-1<=max)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);
    }

    //就是方法也直接上@FXML
    @FXML
    public void add(){
        /*
        弹出的话也可以采用这种方式，dialog的方式
         */
        GoodAddPane goodAddPane = new GoodAddPane(goodsblService);
        JFXDialog dialog = new JFXDialog(mainpane,goodAddPane,JFXDialog.DialogTransition.CENTER);
        dialog.show();
        GoodsVO goodsVO = goodAddPane.getGoodsVO();
        set.add(goodsVO);
        refreshGlv();
        GoodsChosenItem.getSet().clear();
    }

    public void switchPane(boolean toSwtich){
        if(toSwtich==true){
            System.out.println("??/**/");
            boardController.switchTo(this);
        }else{
            if(historyAdd){
                HistoricalRecord.addPane(this);
                historyAdd=false;
            }
            boardController.setAll(this);
        }
    }

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
                            glv.setGoods(set);
                            pagination = new Pagination((glv.getObservableList().size() /glv.getRowsPerPage()+1 ), 0);
                            pagination.setPageFactory(glv::createPage);
                            pagination.setPrefWidth(600);
                            borderpane.setCenter(pagination);
                            switchPane(toSwitch);
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
        }catch (Exception e){

            e.printStackTrace();
            /*  JFXDialogLayout scare = new JFXDialogLayout();
            scare.setHeading(new Label("Wrong"));
            scare.setBody(new Label("Runtimerro"));
            JFXDialog d = new JFXDialog(mainpane,scare, JFXDialog.DialogTransition.CENTER);
            d.show();
            new Thread(() -> {
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(1000);

                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                });}).start();
            System.exit(1);
            */
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
        protected Boolean call() throws Exception{
            set = goodsblService.show();
            if(set!=null){
                Thread.sleep(2000);
                integerProperty.setValue(1);
                return true;
            }else {
                Thread.sleep(2000);
                integerProperty.set(0);
                return false;
            }
        }
    }
}
