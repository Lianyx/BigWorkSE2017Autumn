package ui.userui.usermanagerui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import ui.mainui.Test;
import ui.util.CircleImageView;
import vo.UserListVO;
import java.util.Set;


public class UserTreeTable extends JFXTreeTableView<UserListVO> {
    private ObservableList<UserListVO> observableList = FXCollections.observableArrayList();
    private ObservableList<UserListVO> observableListtemp;
    private int rowsPerPage = 6;


    public ObservableList<UserListVO> getObservableList() {
        return observableList;
    }

    public int getRowsPerPage() {
        return rowsPerPage;
    }


    public UserTreeTable() {
        super();
        JFXTreeTableColumn<UserListVO,Boolean> choose = new JFXTreeTableColumn("  ");
        choose.setPrefWidth(50);
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
        username.setPrefWidth(100);
        username.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, String> param) -> {
            if (username.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUsername());
            } else {
                return username.getComputedValue(param);
            }
        });


        JFXTreeTableColumn<UserListVO, String> usertype = new JFXTreeTableColumn("Usertype");
        usertype.setPrefWidth(100);
        usertype.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, String> param) -> {
            if (username.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUserCategory().name());
            } else {
                return username.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<UserListVO, Long> userid = new JFXTreeTableColumn("Userid");
        userid.setPrefWidth(100);
        userid.setCellValueFactory((TreeTableColumn.CellDataFeatures<UserListVO, Long> param) -> {
            if (userid.validateValue(param)) {
                return new ReadOnlyObjectWrapper(param.getValue().getValue().getUserid());
            } else {
                return userid.getComputedValue(param);
            }
        });


        this.setShowRoot(false);


        this.getColumns().addAll(choose,image, username, usertype, userid);

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
                civ.setRadius(15);
                setGraphic(civ);
            }


        }



    }
}


