package ui.accountantui;

import blService.accountblService.AccountblService;
import blServiceStub.accountblservice_Stub.AccountblService_Stub;
import businesslogic.accountbl.Accountbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
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
import ui.userui.usermanagerui.*;
import ui.util.*;
import vo.AccountListVO;
import vo.MemberListVO;
import vo.billReceiptVO.CashReceiptListVO;
import vo.billReceiptVO.PaymentReceiptListVO;

import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountListPane extends ReceiptListPane<AccountListVO>{



    AccountblService accountblService;

    SimpleStringProperty match = new SimpleStringProperty("");

    List<AccountListVO> list;

    public AccountListPane() throws Exception{
        super("/accountantui/accountListPane.fxml");

        this.accountblService = new Accountbl();
        receiptTreeTable = new AccountTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));

        /*PopOver filterPopOver = new PopOver();
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));*/

    }

    @Override
    public void deleteList(){
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
    }




    @Override
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
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }

    @Override
    public void add(){
        AccountDetailPane accountDetailPane =new AccountDetailPane(true);
    }


    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
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
                        borderpane.setBottom(pagination);
                        switchPane(toSwitch);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }

}
