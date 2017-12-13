package ui.util;

import com.jfoenix.controls.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import util.ResultMessage;


import java.io.IOException;

public class MessageHBox extends HBox{
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




    public MessageHBox()
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

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    //设置右端的标签button
    public void setPopup(){

       }


    public ResultMessage delete(){
        return ResultMessage.SUCCESS;
    }



}