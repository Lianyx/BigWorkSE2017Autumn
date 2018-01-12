package ui.memberui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.memberblService.MemberblService;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.PopOver;
import ui.util.DoubleButtonDialog;
import ui.util.GetTask;
import ui.util.ReceiptListPane;
import util.MemberCategory;
import vo.MemberListVO;
import vo.MemberSearchVO;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemberListPane {
/*
    MemberblService memberblService;

    private static MemberSearchVO memberSearchVO = new MemberSearchVO();

    private static MemberFilterPane filterPane ;

    SimpleStringProperty match = new SimpleStringProperty("");

    public MemberListPane() throws Exception{
        super("/userui/userlistpane.fxml");
        this.memberblService = ServiceFactory_Stub.getService(MemberblService.class.getName());
        receiptTreeTable = new MemberTreeTable();
        receiptTreeTable.setPrefSize(600,435);
        receiptTreeTable.keywordProperty().bind(match);
        borderpane.setTop(new BorderPane(receiptTreeTable));
        for (MemberCategory memberCategory : MemberCategory.values()) {
            memberSearchVO.getMemberCategories().add(memberCategory);
        }

        filterPane = new MemberFilterPane(filterPopOver, memberSearchVO);
        filterPopOver.setDetachable(false);
        filterPopOver.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        filterPopOver.setContentNode(filterPane);
        filter.setOnMouseClicked(e -> filterPopOver.show(filter));
    }


    @FXML
    public void deleteList(){
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane,"Delete","sabi","Yes","No");
        doubleButtonDialog.setButtonOne(()->{receiptTreeTable.delete(pagination); });
        doubleButtonDialog.setButtonTwo(()->{});
        doubleButtonDialog.show();
    }

    @Override
    public void search() {
        if (searchField.getText() != ""&&searchField.getText() != null) {
            match.setValue(searchField.getText().toLowerCase());
            Set<MemberListVO> hashSet;
            hashSet = set.stream().filter(
                    s -> s.getMemberCategory().name().contains(match.get()) ||
                            s.getClerkName().contains(match.get())||
                            s.getName().contains(match.get())||
                            String.format("%05d",s.getMemberId()).contains(match.get())
            ).collect(Collectors.toSet());
            receiptTreeTable.setReceipts(hashSet);
            pagination.setPageCount(receiptTreeTable.getObservableList().size() / receiptTreeTable.getRowsPerPage() + 1);
            receiptTreeTable.createPage(0);
            borderpane.setBottom(pagination);
            switchPane(false);
        }
    }
    @Override
    public void add()
    {
        MemberDetailPane memberDetailPane = new MemberDetailPane(true);
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
                if ((set = memberblService.search(memberSearchVO)) != null) {
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
                        switchPane(toSwitch);
                    }, buttonDialog, p);

            new Thread(getTask).start();
        } catch (Exception e) {
            System.exit(1);
            e.printStackTrace();

        }

    }*/
}
