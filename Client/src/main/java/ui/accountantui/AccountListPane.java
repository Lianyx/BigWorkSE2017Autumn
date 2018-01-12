package ui.accountantui;

import blService.accountblService.AccountblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import ui.managerui.common.MyBoardController;
import ui.userui.usermanagerui.*;
import ui.util.*;
import vo.AccountListVO;
import vo.AccountVO;
import vo.MemberListVO;
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.PaymentReceiptListVO;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountListPane extends Refreshable{

    AccountTreeTable receiptTreeTable;

    @FXML
    BorderPane borderPane;

    MyBoardController myBoardController;

    Set<AccountListVO> set;

    Pagination pagination;

    StackPane mainpane;

    public boolean historyAdd = false;

    PopOver filterPopOver = new PopOver();

    @FXML
    JFXButton filter;

    @FXML
    JFXRippler search;

    @FXML
    JFXTextField searchField;

    AccountblService accountblService;

    SimpleStringProperty match = new SimpleStringProperty("");

    List<AccountListVO> list;

    public AccountListPane() throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/accountantui/accountListPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.myBoardController = MyBoardController.getMyBoardController();
        this.mainpane = PaneFactory.getMainPane();
        pagination = new Pagination();
        pagination.currentPageIndexProperty().addListener((b,o,n)->{
            receiptTreeTable.createPage(n.intValue());
        });
        borderPane.setBottom(pagination);

        this.accountblService = new Accountbl();
        receiptTreeTable = new AccountTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        receiptTreeTable.keywordProperty().bind(match);
        borderPane.setTop(new BorderPane(receiptTreeTable));

    }
    @FXML
    public void deleteList(){
        if(receiptTreeTable.chosenItem.getSet().size()==0){
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane,"","请选择账户","继续");
            oneButtonDialog.setButtonOne(()->{});
            oneButtonDialog.show();
        }else {
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "", "请确定是否删除", "是", "否");
            doubleButtonDialog.setButtonOne(() -> {
                receiptTreeTable.delete(pagination);
            });
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.show();
        }
    }

    @FXML
    public void search(){
        if (searchField.getText() != ""&&searchField.getText() != null) {
            match.setValue(searchField.getText().toLowerCase());
            Set<AccountListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getName().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderPane.setBottom(pagination);
            myBoardController.switchTo(this);
        }
    }

    @FXML
    public void add(){
        AccountDetailPane accountDetailPane =new AccountDetailPane(true);
    }



    public void refresh(boolean toSwitch) {
        myBoardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> myBoardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));
            Predicate<Integer> p = (s) -> {
                try{
                    if((set=accountblService.getAll())!=null) {
                        System.out.print(set.size());
                        return true;
                    }
                }catch (RemoteException e){
                    e.printStackTrace();
                }
                return false;
            };
            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderPane.setBottom(pagination);
                        myBoardController.switchTo(this);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }

}
