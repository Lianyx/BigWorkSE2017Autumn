package ui.accountantui;

import blService.checkblService.ReceiptblService;
import blServiceStub.billblservice_Stub.CashblService_Stub;
import blServiceStub.billblservice_Stub.ChargeblService_Stub;
import blServiceStub.billblservice_Stub.PaymentblService_Stub;
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
import ui.util.BoardController;
import ui.userui.usermanagerui.FilterPane;
import ui.util.HistoricalRecord;
import ui.util.Refreshable;
import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class BillReceiptListPane extends Refreshable{


    BillReceiptTreeTable ulv;

    @FXML
    BorderPane borderpane;


    BoardController boardController;

    ReceiptblService receiptblService;

    Pagination pagination;

    StackPane mainpane;

    ArrayList<ReceiptVO> list = new ArrayList<>();

    public boolean historyAdd = false;

    @FXML
    JFXButton filter;

    public BillReceiptListPane(ReceiptblService receiptblService, BoardController boardController) throws Exception{
        super();
        ulv = new BillReceiptTreeTable(receiptblService,boardController,mainpane);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/billReceiptListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.receiptblService = receiptblService;
        setListView();

        this.boardController=boardController;
        //ulv.setBoardController(boardController);

        PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(new FilterPane());
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));


        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefSize(600,450);
        borderpane.setCenter(pagination);

    }

    public void setReceiptblService(ReceiptblService receiptblService) {
        this.receiptblService = receiptblService;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setListView()throws RemoteException
    {
       ulv.setReceipt(receiptblService.search(new ReceiptSearchCondition()));
    }

    @FXML
    public void deleteList(){

        for(ReceiptVO receiptVO: BillReceiptChosenItem.getList()) {
            ulv.removeReceipt(receiptVO);
            //((AccountblService_Stub)accountblService).delete(accountListVO.getID());
            //receiptblService.delete(receiptVO);
        }
        int current=pagination.getCurrentPageIndex();
        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefSize(600,450);
        borderpane.setCenter(pagination);
        if(current-1>=0)
            pagination.setCurrentPageIndex(current-1);
        else
            pagination.setCurrentPageIndex(0);
        AccountChosenItem.getList().clear();
    }

    @FXML
    public void add() {
        //JFXDialog dialog = new JFXDialog(mainpane,new AddPane(),JFXDialog.DialogTransition.CENTER);
        //dialog.show();

        if (receiptblService instanceof PaymentblService_Stub) {
            boardController.switchTo(new PaymentAddPane(receiptblService, boardController, mainpane));
        }
        else if(receiptblService instanceof ChargeblService_Stub){
            boardController.switchTo(new ChargeAddPane(receiptblService,boardController,mainpane));
        }
        else if(receiptblService instanceof CashblService_Stub){
            boardController.switchTo(new CashAddPane(receiptblService,boardController,mainpane));
        }
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
    public void refresh(boolean toSwitch){
        boardController.Loading();
        try {
            BillReceiptListPane.LoadingTask task = new BillReceiptListPane.LoadingTask();
            task.valueProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (task.getIntegerProperty() == 1) {
                        try {
                            ulv.setReceipt(list);
                            pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
                            pagination.setPageFactory(ulv::createPage);
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

            list = receiptblService.search(new ReceiptSearchCondition());
            if(list!=null){
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
