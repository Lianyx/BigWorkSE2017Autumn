package ui.inventoryui.inventoryReceiptui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.inventoryblService.InventoryblService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.PopOver;
import ui.managerui.common.MyBoardController;
import ui.util.*;
import util.ReceiptState;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptListVO;
import vo.inventoryVO.inventoryReceiptVO.InventoryReceiptType;
import vo.inventoryVO.inventoryReceiptVO.InventorySearchVO;
import vo.receiptVO.StockReceiptListVO;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InventoryListPane extends Refreshable/*extends ReceiptListPane<InventoryReceiptListVO>*/ {

    protected ReceiptTreeTable<InventoryReceiptListVO> receiptTreeTable;

    @FXML
    protected BorderPane borderpane;

    protected Set<InventoryReceiptListVO> set;

    protected BoardController boardController;

    protected Pagination pagination;

    protected StackPane mainpane;

    public boolean historyAdd = false;

    protected PopOver filterPopOver = new PopOver();

    @FXML
    protected JFXButton filter;

    @FXML
    protected JFXRippler search;

    @FXML
    protected JFXTextField searchField;

    InventoryblService inventoryblService;

    InventoryReceiptType receiptType;

    SimpleStringProperty match = new SimpleStringProperty("");

    static InventorySearchVO inventorySearchVO = new InventorySearchVO();

    public InventoryListPane(InventoryReceiptType receiptType) throws Exception {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/inventoryreceiptui/inventorylistpane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.boardController = MyBoardController.getMyBoardController();
        this.mainpane = PaneFactory.getMainPane();
        pagination = new Pagination();
        pagination.currentPageIndexProperty().addListener((b,o,n)->{
            receiptTreeTable.createPage(n.intValue());
        });
        borderpane.setBottom(pagination);
     /*   super("/inventoryui/inventoryreceiptui/inventorylistpane.fxml");*/
        this.inventoryblService = ServiceFactory_Stub.getService(InventoryblService.class.getName());

        this.receiptType = receiptType;

        receiptTreeTable = new InventoryTreeTable();
        receiptTreeTable.setPrefSize(600, 435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        for (ReceiptState receiptState : ReceiptState.values()) {
           inventorySearchVO.getReceiptStates().add(receiptState);
        }
    }

   // @Override
    public void deleteList() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
        /*
        差与bl的连接
         */

    }

  //  @Override
    public void search() {
        if (searchField.getText() != ""&&searchField.getText() != null) {
            match.setValue(searchField.getText().toLowerCase());
            Set<InventoryReceiptListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getReceiptState().name().toLowerCase().contains(match.get()) ||
                            s.getId().toLowerCase().contains(match.get())||
                            s.getMemberName().toLowerCase().contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            //switchPane(false);
            boardController.switchTo(this);
        }

    }

   // @Override
    public void add() {
        InventoryReceiptPane stockReceiptPane = new InventoryReceiptPane(receiptType);
        /*
        未实现
         */
    }

    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            DoubleButtonDialog buttonDialog =
                    new DoubleButtonDialog(mainpane, "Wrong", "你的数据未连上", "Last", "Ret");
            buttonDialog.setButtonTwo(() -> boardController.Ret());
            buttonDialog.setButtonTwo(() -> refresh(false));
            Predicate<Integer> p = (s) -> {
                if ((set = inventoryblService.search(inventorySearchVO, receiptType)) != null) {
                    System.out.println(set.size());
                    return true;
                }
                return false;
            };
            GetTask getTask =
                    new GetTask(() -> {
                        receiptTreeTable.setReceipts(set);
                        pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
                        receiptTreeTable.createPage(0);
                        borderpane.setBottom(pagination);
                        //switchPane(toSwitch);
                        boardController.switchTo(this);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
