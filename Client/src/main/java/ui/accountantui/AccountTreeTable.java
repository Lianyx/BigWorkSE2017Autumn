package ui.accountantui;

import blService.accountblService.AccountblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import javafx.util.Duration;
import ui.userui.usermanagerui.BoardController;
import ui.util.CircleImageView;
import vo.AccountListVO;
import java.util.List;

public class AccountTreeTable extends JFXTreeTableView<AccountListVO>{

    private ObservableList<AccountListVO> observableList = FXCollections.observableArrayList();
    private ObservableList<AccountListVO> observableListtemp;
    private int rowsPerPage = 7;

    private AccountblService accountblService;
    private BoardController boardController;
    private StackPane mainpane;


    public ObservableList<AccountListVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public AccountTreeTable(AccountblService accountblService,BoardController boardController,StackPane mainpane) {
        super();
        this.accountblService = accountblService;
        this.boardController = boardController;
        this.mainpane = mainpane;

        JFXTreeTableColumn<AccountListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        Callback<TreeTableColumn<AccountListVO,Boolean>, TreeTableCell<AccountListVO,Boolean>> chooseCellFactory = (TreeTableColumn<AccountListVO,Boolean> p) -> new ChooseBoxCell();
        choose.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, Boolean> param) -> {
            if (choose.validateValue(param)) {
                return param.getValue().getValue().selectedProperty();
            } else {
                return choose.getComputedValue(param);
            }
        });
        choose.setCellFactory(chooseCellFactory);

        JFXTreeTableColumn<AccountListVO, Integer> id = new JFXTreeTableColumn("ID");
        id.setPrefWidth(100);
        id.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, Integer> param) -> {
            if (id.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getID());
            } else {
                return id.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<AccountListVO, String> username = new JFXTreeTableColumn("Name");
        username.setPrefWidth(100);
        username.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, String> param) -> {
            if (username.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getName());
            } else {
                return username.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<AccountListVO, Double> balance = new JFXTreeTableColumn("Balance");
        balance.setPrefWidth(100);
        balance.setCellValueFactory((TreeTableColumn.CellDataFeatures<AccountListVO, Double> param) -> {
            if (balance.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getBalance());
            } else {
                return balance.getComputedValue(param);
            }
        });


        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount()==2){
                    try{
                        //boardController.switchTo(new UserDetailPane((UserListVO) row.getTreeItem().getValue(),boardController));

                        AccountDetailPane accountDetailPane = new AccountDetailPane((AccountListVO)row.getTreeItem().getValue(),accountblService,boardController,mainpane);
                        accountDetailPane.refresh(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            });
            row.selectedProperty().addListener(e->{
                if(row.isSelected()){
                    row.toFront();
                }else{
                    row.setEffect(null);
                }
            });
            return row;
        });

        setCurrentItemsCount(rowsPerPage);
        this.setShowRoot(false);

        this.getColumns().addAll(choose,id,username,balance);
    }

    public void setAccount(List<AccountListVO> list){
        observableList.setAll(list);
    }

    public void removeAccount(AccountListVO accountListVO){
        observableList.remove(accountListVO);
    }

    public Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        observableListtemp = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        final TreeItem<AccountListVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
        this.setRoot(root);
        this.setStyle("-fx-border-color: transparent; -fx-padding: 0; -fx-background-color: transparent");


        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(this.opacityProperty(), 0)
                )
        );

        for (int i = 1; i <= 10; i++) {
            timeline.getKeyFrames().add(
                    new KeyFrame(new Duration(i * 80),
                            new KeyValue(this.opacityProperty(), i / 10.0)
                    )
            );
        }
        timeline.play();
        return new BorderPane(this);
    }

    private class ChooseBoxCell extends JFXTreeTableCell<AccountListVO,Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell(){
            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(getTreeTableRow()!=null&&getTreeTableRow().getItem()!=null){
                        AccountListVO accountListVO=getTreeTableRow().getItem();
                        accountListVO.setSelected(!accountListVO.isSelected());
                        System.out.println(accountListVO);
                        if(accountListVO.isSelected()){
                            AccountChosenItem.addItem(accountListVO);
                        }else{
                            AccountChosenItem.removeItem(accountListVO);
                        }
                    }
                }
            });
        }

        @Override
        public void updateItem(Boolean item,boolean empty){
            super.updateItem(item,empty);

            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                cb.setSelected(item);
                setGraphic(cb);
            }

        }

    }

    private class ImageCell extends JFXTreeTableCell {
        private CircleImageView civ = new CircleImageView();

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                civ.setImage((Image)item);
                civ.setRadius(17);
                civ.setTranslateY(-8);
                setGraphic(civ);
            }
        }
    }

    private class ButtonCell extends JFXTreeTableCell {
        private JFXButton civ = new JFXButton("sabi");

        @Override
        public void updateItem(Object item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                civ.setStyle("-fx-background-color: rgba(182,0,107,0.19); -fx-text-fill: white;-fx-background-radius: 10;");
                setGraphic(civ);
            }
        }
    }

}
