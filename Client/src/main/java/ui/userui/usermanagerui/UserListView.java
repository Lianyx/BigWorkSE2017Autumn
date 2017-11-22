package ui.userui.usermanagerui;


import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.skins.JFXListViewSkin;
import com.sun.javafx.scene.control.skin.ListViewSkin;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import vo.UserListVO;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UserListView extends JFXListView {
    private Set<UserListVO> set;
    ObservableList observableList= FXCollections.observableArrayList();
    Scene scene;


    //…Ë÷√cellπ§≥ß
    public UserListView(){
        super();
        set=new TreeSet<UserListVO>();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ListView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.setCellFactory(new Callback<JFXListView<UserListVO>, JFXListCell<UserListVO>>()
        {
            public JFXListCell<UserListVO> call(JFXListView<UserListVO> param) {
                return new UserListCell();
            }
        });
        observableList.setAll(set);
        this.setItems(observableList);


    }


    public void addUser(UserListVO userListVO){
         set.add(userListVO);
         observableList.setAll(set);
    }


    public void setUser(Set<UserListVO> users){
        set = users;
        observableList.setAll(set);

    }

    public void removeUser(UserListVO userListVO){
        set.remove(userListVO);
        observableList.setAll(set);
    }


}
