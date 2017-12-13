package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import blServiceStub.usermanagerblService_Stub.Usermanagerblservice_Stub;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import exception.ProblemException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Pagination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import vo.UserListVO;

import javax.annotation.PostConstruct;
import java.beans.EventHandler;
import java.util.TreeSet;

public class UserListPane extends AnchorPane{

    UserTreeTable ulv=new UserTreeTable();

    @FXML
    BorderPane borderpane;

    @FXML
    JFXRippler postsearch;

    BoardController boardController;

    UserManagerblService userManagerblService;

    Pagination pagination;

    public UserListPane(UserManagerblService userManagerblService,BoardController boardController) throws Exception{
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userlistpane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
        this.userManagerblService=userManagerblService;
        setListView();

        this.boardController=boardController;
        ulv.setBoardController(boardController);

        pagination = new Pagination((ulv.getObservableList().size() /ulv.getRowsPerPage()+1 ), 0);
        pagination.setPageFactory(ulv::createPage);
        pagination.setPrefSize(600,450);
        borderpane.setCenter(pagination);



    }

    public void setUserManagerblService(UserManagerblService userManagerblService){
        this.userManagerblService=userManagerblService;
    }

    public void setListView()
    {
        ulv.setUser(userManagerblService.getAll());
    }

    @FXML
    public void deleteList(){
        for(UserListVO userListVO:ChosenItem.getSet()) {
            ulv.removeUser(userListVO);
            ((Usermanagerblservice_Stub)userManagerblService).delete(userListVO);
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
        ChosenItem.getSet().clear();
    }

    @FXML
    public void conditionsearch(){
        JFXPopup popup = new JFXPopup(new UserSearchBox());
        popup.show(postsearch, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);


    }



}
