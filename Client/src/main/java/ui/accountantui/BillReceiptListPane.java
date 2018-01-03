package ui.accountantui;

import blService.billblService.PaymentBillReceiptblService;
import blService.checkblService.ReceiptblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.userui.usermanagerui.AddPane;
import ui.userui.usermanagerui.BoardController;
import ui.userui.usermanagerui.FilterPane;
import ui.util.Refreshable;
import util.ReceiptSearchCondition;
import vo.receiptVO.ReceiptVO;

import java.rmi.RemoteException;

public class BillReceiptListPane extends Refreshable{


    BillReceiptTreeTable ulv;

    @FXML
    BorderPane borderpane;


    BoardController boardController;

    ReceiptblService receiptblService;

    Pagination pagination;

    StackPane mainpane;

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
    public void add(){
        //JFXDialog dialog = new JFXDialog(mainpane,new AddPane(),JFXDialog.DialogTransition.CENTER);
        //dialog.show();
    }


    @Override
    public void refresh(boolean toSwitch){
        try {
            setListView();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
