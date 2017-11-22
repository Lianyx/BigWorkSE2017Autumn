package ui.userui.usermanagerui;

import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import util.ResultMessage;
import utilComponent.CircleImageView;
import utilComponent.CustomHbox;
import vo.UserListVO;
import vo.UserVO;

import java.io.IOException;

public class UserHbox extends CustomHbox<UserListVO> {
    @FXML
    private JFXCheckBox cb;
    @FXML
    private Label username;
    @FXML
    private Label usertype;
    @FXML
    private CircleImageView civ;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private JFXRippler rippler;

    private UserListVO userListVO;


    public UserHbox()
    {


        //加载fxml文件
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/userui/userlistcell.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
            setPopup();



            cb.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(cb.isSelected())
                        ChosenItem.addItem(userListVO);
                    else
                        ChosenItem.removeItem(userListVO);
                }
            });

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    //设置右端的标签button
    public void setPopup(){

        JFXListView<Label> list = new JFXListView<Label>();

        Label viewInfo =new Label("View");
        viewInfo.setOnMouseClicked(e->view(userListVO));

        Label modifyInfo = new Label("Modify");
        modifyInfo.setOnMouseClicked(e->modify(userListVO));

        Label deleteUser = new Label("Delete");
        deleteUser.setOnMouseClicked(e->delete());

        list.getItems().addAll(viewInfo,modifyInfo,deleteUser);
        list.setPrefWidth(80);

        AnchorPane container = new AnchorPane();
        container.getChildren().add(rippler);
        this.getChildren().add(container);

        JFXPopup popup = new JFXPopup(list);
        rippler.setOnMouseClicked(e -> popup.show(rippler, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT));
    }

    public void view(UserListVO userListVO){
         //跳转
    }

    public void modify(UserListVO userListVO){
         //跳转
    }

    public ResultMessage delete(){
        return ResultMessage.SUCCESS;
    }


    //为hbox设置信息
    @Override
    public void setInfo(UserListVO userListVO){
        this.userListVO = userListVO;

        username.setText(userListVO.getUsername());
        usertype.setText(userListVO.getUserCategory().name());
        civ.setImage(userListVO.getImage());
        email.setText(userListVO.getEmail());
        phone.setText(userListVO.getPhone());

    }

    public JFXCheckBox getCb() {
        return cb;
    }

    public UserListVO getUserListVO() {
        return userListVO;
    }
}
