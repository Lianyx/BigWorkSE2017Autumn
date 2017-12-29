package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.util.Callback;

import javafx.util.Duration;
import ui.util.CircleImageView;
import util.ResultMessage;
import vo.UserListVO;
import vo.UserVO;

import java.security.spec.ECField;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class UserTreeTable extends JFXTreeTableView<UserListVO> {
    private ObservableList<UserListVO> observableList = FXCollections.observableArrayList();
    private ObservableList<UserListVO> observableListtemp;
    private int rowsPerPage = 7;
    private BoardController boardController;
    private UserManagerblService userManagerblService;
    private StackPane mainpane;

    public ObservableList<UserListVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public UserTreeTable(UserManagerblService userManagerblService,BoardController boardController,StackPane mainpane) {
        super();
        this.userManagerblService=userManagerblService;
        this.boardController=boardController;
        this.mainpane=mainpane;


        JFXTreeTableColumn<UserListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(40);
        Callback<TreeTableColumn<UserListVO,Boolean>, TreeTableCell<UserListVO,Boolean>> chooseCellFactory = (TreeTableColumn<UserListVO,Boolean> p) -> new ChooseBoxCell();
        choose.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, Boolean> param) -> {
            if (choose.validateValue(param)) {
                return param.getValue().getValue().selectedProperty();
            } else {
                return choose.getComputedValue(param);
            }
        });
        choose.setCellFactory(chooseCellFactory);


        JFXTreeTableColumn image = new JFXTreeTableColumn("  ");
        image.setPrefWidth(50);
        Callback<TreeTableColumn, TreeTableCell> imageCellFactory = (TreeTableColumn p) -> new ImageCell();
        image.setCellValueFactory(new TreeItemPropertyValueFactory<>("image"));
        image.setCellFactory(imageCellFactory);



        JFXTreeTableColumn<UserListVO, String> username = new JFXTreeTableColumn("Username");
        username.setPrefWidth(80);
        username.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, String> param) -> {
            if (username.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUsername());
            } else {
                return username.getComputedValue(param);
            }
        });




        JFXTreeTableColumn<UserListVO, Long> userid = new JFXTreeTableColumn("Userid");
        userid.setPrefWidth(120);
        userid.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, Long> param) -> {
            if (userid.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUserid());
            } else {
                return userid.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<UserListVO, String> usertype = new JFXTreeTableColumn("Usertype");
        usertype.setPrefWidth(125);
        usertype.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, String> param) -> {
            if (usertype.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUserCategory().name());
            } else {
                return usertype.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<UserListVO, String>, TreeTableCell<UserListVO, String>> typeCellFactory = (TreeTableColumn<UserListVO, String> p) -> new ButtonCell();
        usertype.setCellFactory(typeCellFactory);


        JFXTreeTableColumn<UserListVO, String> phone = new JFXTreeTableColumn("Phone");
        phone.setPrefWidth(150);
        phone.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, String> param) -> {
            if (phone.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getPhone());
            } else {
                return phone.getComputedValue(param);
            }
        });
        Callback<TreeTableColumn<UserListVO, String>, TreeTableCell<UserListVO, String>> tokenCellFactory = (TreeTableColumn<UserListVO, String> p) -> new TokenCell();
        phone.setCellFactory(tokenCellFactory);


        JFXTreeTableColumn<UserListVO, Boolean> more = new JFXTreeTableColumn("");
        more.setPrefWidth(20);
        Callback<TreeTableColumn<UserListVO, Boolean>, TreeTableCell<UserListVO, Boolean>> moreCellFactory = (TreeTableColumn<UserListVO, Boolean> p) -> new MoreCell();
        more.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, Boolean> param) -> {
            if (more.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().isMultiple());
            } else {
                return more.getComputedValue(param);
            }
        });
        more.setCellFactory(moreCellFactory);




        this.setRowFactory(tableView->{
            JFXTreeTableRow row=new JFXTreeTableRow();
            row.setPrefHeight(55);
            row.setStyle("-fx-border-color: rgb(233,237,239); -fx-border-width: 0.3;");
            row.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount()==2){
                     UserDetailPane userDetailPane = new UserDetailPane(((UserListVO)row.getTreeItem().getValue()).getUserid(),userManagerblService,boardController,mainpane);
                     userDetailPane.refresh(true);
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


        this.getColumns().addAll(choose,image, username,  userid,usertype,phone,more);

    }

    public void setUser(Set<UserListVO> users){
        observableList.setAll(users);
    }

    public void removeUser(UserListVO userListVO){
        observableList.remove(userListVO);
    }

    public Node createPage(int pageIndex) {

        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, observableList.size());
        observableListtemp = FXCollections.observableList(observableList.subList(fromIndex, toIndex));
        final TreeItem<UserListVO> root = new RecursiveTreeItem<>(observableListtemp, RecursiveTreeObject::getChildren);
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

        return new BorderPane(this);
    }






    private class ChooseBoxCell extends JFXTreeTableCell<UserListVO,Boolean> {
        private JFXCheckBox cb = new JFXCheckBox("");

        public ChooseBoxCell(){
            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(getTreeTableRow()!=null&&getTreeTableRow().getItem()!=null){
                        UserListVO userListVO=getTreeTableRow().getItem();
                        System.out.println(userListVO);
                        userListVO.setSelected(!userListVO.isSelected());
                        if(userListVO.isSelected()){
                            ChosenItem.addItem(userListVO);
                        }else{
                            ChosenItem.removeItem(userListVO);
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
    private class ButtonCell extends JFXTreeTableCell<UserListVO,String> {
        private JFXButton civ = new JFXButton("");

        @Override
        public void updateItem(String item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                civ.setText(item);
                setGraphic(civ);
            }
        }
    }


    private class TokenCell extends JFXTreeTableCell<UserListVO,String> {
        private TokenLabel tl = new TokenLabel("");

        @Override
        public void updateItem(String item,boolean empty){
            super.updateItem(item,empty);
            if(empty){
                setGraphic(null);
            }else{
                tl.setColor(Paint.valueOf("#AA11F2"));
                tl.setText(item);
                setGraphic(tl);
            }
        }
    }
    private class MoreCell extends JFXTreeTableCell<UserListVO, Boolean> {
        private IconButton iconButton = new IconButton(MaterialDesignIcon.DOTS_HORIZONTAL);

        @Override
        public void updateItem(Boolean item,boolean empty){
            super.updateItem(item,empty);
            if(item!=null){
                ListPopup list = new ListPopup();
                iconButton.setTranslateY(-8);
                JFXPopup popup = new JFXPopup(list);
                iconButton.setOnMouseClicked(e -> popup.show(iconButton, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT));
                setGraphic(iconButton);
            }
            }
        }

}


