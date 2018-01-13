package ui.userui.usermanagerui;

import blService.userblService.UserManagerblService;
import businesslogic.userbl.Userbl;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import exceptions.ItemNotFoundException;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import ui.common.BoardController;
import ui.common.dialog.MyOneButtonDialog;
import ui.common.dialog.MyTwoButtonDialog;
import ui.util.*;
import util.UserCategory;
import vo.UserVO;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;

import static ui.util.ValidatorDecorator.RequireValid;

public class UserDetailPane extends RefreshablePane {
    @FXML
    private ModifyButton modify;
    @FXML
    private JFXButton reset, save, delete;

    final FileChooser fileChooser = new FileChooser();

    @FXML
    JFXRippler github;
    @FXML
    JFXRippler facebook;
    @FXML
    JFXRippler twitter;




    @FXML
    MaterialDesignIconView facebookIcon;
    @FXML
    MaterialDesignIconView githubIcon;
    @FXML
    MaterialDesignIconView twitterIcon;


    @FXML
    JFXTextField username;
    @FXML
    JFXComboBox<Label> usertype;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;
    @FXML
    TextArea comment;
    @FXML
    Label date;
    @FXML
    Label password;


    private UserManagerblService userManagerblService;
    private UserVO userVO;
    private BooleanProperty modifyState = new SimpleBooleanProperty(true);
    private StackPane mainpane;
    /**
     * Constructors related
     */
    public UserDetailPane() {
        initialize();
    }

    public UserDetailPane(UserVO userVO) {
        this.userVO = userVO;
        initialize();
    }

    private void initialize() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(getURL()));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.mainpane = PaneFactory.getMainPane();

        this.modifyState.bind(modify.modifyProperty());


        save.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);


        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        date.setText(LocalDate.now().toString());
        RequireValid(username);


        github.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setGithub(textFieldPane.getText());
                setColor(githubIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Github");
            textFieldPane.setText(userVO.getGithub());
            dialog.show();
        });

        facebook.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setFacebook(textFieldPane.getText());
                setColor(facebookIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Facebook");
            textFieldPane.setText(userVO.getFacebook());
            dialog.show();
        });

        twitter.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                userVO.setTwitter(textFieldPane.getText());
                setColor(twitterIcon, textFieldPane.getText());
            });
            textFieldPane.setPrompt("Twitter");
            textFieldPane.setText(userVO.getTwitter());
            dialog.show();
        });


    }


    private String getURL() {
        return "/userui/userdetail.fxml";
    }

    /**
     * to be overriden
     */

    private boolean validate() {
        if(username.getText().equals("")&&password.getText().equals(""))
        return false;
        return true;
    }

    private void updateUserVO() {
        username.setText(userVO.getUsername());
        usertype.getSelectionModel().select(userVO.getUsertype().ordinal());
        email.setText(userVO.getEmail());
        phone.setText(userVO.getPhone());
        comment.setText(userVO.getComment());
        password.setText(userVO.getPassword());
        imageview.setImage(userVO.getImage());
        System.out.println("xxxxxx");
        setColor(facebookIcon,userVO.getFacebook()==null?"":userVO.getFacebook());
        setColor(githubIcon,userVO.getGithub()==null?"":userVO.getGithub());
        setColor(twitterIcon,userVO.getTwitter()==null?"":userVO.getTwitter());
    }

    @FXML
    private void reset() {
        TextFieldPane textFieldPane = new TextFieldPane();
        JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
        textFieldPane.cencel(() -> {
            dialog.close();
        });
        textFieldPane.save(() -> {
            dialog.close();
            password.setText(textFieldPane.getText());
        });
        textFieldPane.setText(password.getText());
        textFieldPane.setPrompt("Password");
        dialog.show();
    }

    private void setVO(){
        userVO.setComment(comment.getText());
        userVO.setEmail(email.getText());
        userVO.setImage(imageview.getImage());
        userVO.setPassword(password.getText());
        userVO.setPhone(phone.getText());
        userVO.setUsername(username.getText());
        userVO.setUsertype(UserCategory.map.get(usertype.getValue().getId()));
    }


    /**
     * FXML
     */

    @FXML
    private void save() { // 这里的save相当于提交，但是不想改名了…
        if (validate()) {
            saveTask();
        }
    }

    private void saveTask() {
        BoardController boardController = BoardController.getBoardController();
        boardController.Loading();

        setVO();

        StringProperty prompt = new SimpleStringProperty();
        new Thread(new GetTask(() -> {
            new MyTwoButtonDialog("保存成功", boardController::goBack).show();
        }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
            try {
                userManagerblService.update(userVO);
                return true;
            } catch (ItemNotFoundException e) {
                e.printStackTrace();
                prompt.set("sabi");
                return false;
            } catch (RemoteException e) {
                e.printStackTrace();
                prompt.set("jiushisabi");
                return false;
            }
        })).start();
    }

    @FXML
    private void delete() {
        new MyTwoButtonDialog("请确认删除", () -> {
            BoardController boardController = BoardController.getBoardController();
            boardController.Loading();

            StringProperty prompt = new SimpleStringProperty(); // 为了避免lambda的final限制。
            new Thread(new GetTask(() -> {
                new MyOneButtonDialog("删除成功", boardController::goBack).show();
            }, new MyTwoButtonDialog(prompt.get(), boardController::goBack), woid -> {
                try {
                    userManagerblService.delete(userVO.getId());
                    return true;
                } catch (ItemNotFoundException e) {
                    e.printStackTrace();
                    prompt.set("用户不存在，是否返回用户列表");
                    return false;
                } catch (RemoteException e) {
                    e.printStackTrace();
                    prompt.set("连接错误");
                    return false;
                }
            })).start();
        }).show();
    }

    @FXML
    private void modify() {
        modifyState.set(!modifyState.get());
    }

    /**
     * refresh
     */
    @Override
    public void refresh(boolean toSwitch) {
        BoardController myBoardController = BoardController.getBoardController();
        myBoardController.Loading();

        MyTwoButtonDialog dialog = new MyTwoButtonDialog("连接错误", () -> refresh(false), myBoardController::Ret);

        GetTask task = new GetTask(() -> {
            myBoardController.switchTo(this); // 感觉这个分两次来明显就是为了可以有等待…
            updateUserVO();
        }, dialog, woid -> {
            try {
                if (userManagerblService == null) { // 这说明肯定是第一次
                    userManagerblService = new Userbl();
                    System.out.println(userVO);
                    if (userVO == null) {
                        userVO = userManagerblService.getNew();
                        delete.setVisible(false);
                        modify.setModify(true);
                        modify.setVisible(false);
                    }else{
                        userVO = userManagerblService.showDetail(userVO.getId());
                    }
                } else {
                    userVO = userManagerblService.showDetail(userVO.getId());
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
        new Thread(task).start();
    }

    public void setColor(MaterialDesignIconView materialDesignIconView, String s) {
        if (s.equals("")) {
            materialDesignIconView.setFill(Paint.valueOf("#000000"));
        } else {
            materialDesignIconView.setFill(Paint.valueOf("#006AF4"));
        }
    }

    @FXML
    public void choosefile() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        Image image = null;
        try {
            image = new Image(file.toURL().toString(),100,100,true,true,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.imageview.setImage(image);

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Pictures Choose");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }



}

/*
    int userId = -1;

    final FileChooser fileChooser = new FileChooser();

    UserManagerblService userManagerblService;

    private static String g = "";
    private static String f = "";
    private static String tw = "";

    @FXML
    MaterialDesignIconView facebookIcon;
    @FXML
    MaterialDesignIconView githubIcon;
    @FXML
    MaterialDesignIconView twitterIcon;

    protected T vo;

    protected BoardController boardController;

    protected SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);

    protected StackPane mainpane;

    @FXML
    protected ModifyButton modify;
    @FXML
    protected MaterialDesignIconView pen;
    @FXML
    protected JFXButton save;

    @FXML
    protected JFXButton reject;

    @FXML
    protected JFXButton delete;

    private boolean check;
    protected SimpleBooleanProperty updateState = new SimpleBooleanProperty();


    @FXML
    JFXRippler github;
    @FXML
    JFXRippler facebook;
    @FXML
    JFXRippler twitter;


    @FXML
    JFXTextField username;
    @FXML
    JFXComboBox<Label> usertype;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    JFXButton choose;
    @FXML
    ImageView imageview;
    @FXML
    JFXButton reset;
    @FXML
    TextArea comment;
    @FXML
    Label date;
    @FXML
    Label password;

    public UserDetailPane(int id) {
        this(false);
        this.userId = id;
        delete.setVisible(true);
        modify.setVisible(true);
        save.setText("Save");
        this.modifyState.bind(modify.modifyProperty());
        this.modifyState.addListener((b, o, n) -> {
            if (!n) {
                if (valid()) {
                    modify.modifyProperty().set(false);
                } else {
                    modify.modifyProperty().set(true);
                }
            }
        });
        username.disableProperty().bind(modifyState.not());
        usertype.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        save.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);
        reset.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
    }

    public UserDetailPane(boolean isAdd) {
        super("/userui/userdetail.fxml");
        userManagerblService = ServiceFactory_Stub.getService(UserManagerblService.class.getName());
        usertype.getItems().add(new Label(UserCategory.InventoryManager.name()));
        usertype.getItems().add(new Label(UserCategory.Salesman.name()));
        usertype.getItems().add(new Label(UserCategory.SalesManager.name()));
        usertype.getItems().add(new Label(UserCategory.Accountant.name()));
        usertype.getItems().add(new Label(UserCategory.GeneralManager.name()));
        usertype.getSelectionModel().select(0);


        delete.setVisible(false);
        date.setText(LocalDate.now().toString());
        password.setText("");
        RequireValid(username);
        RequireValid(email);
        RequireValid(phone);

        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
        }

        reset.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                password.setText(textFieldPane.getText());
            });
            textFieldPane.setText(password.getText());
            textFieldPane.setPrompt("Password");
            dialog.show();
        });

        github.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                g = textFieldPane.getText();
                setColor(githubIcon,g);
            });
            textFieldPane.setPrompt("Github");
            textFieldPane.setText(g);
            dialog.show();
        });

        facebook.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                f = textFieldPane.getText();
                setColor(facebookIcon,f);
            });
            textFieldPane.setPrompt("Facebook");
            textFieldPane.setText(f);
            dialog.show();
        });

        twitter.setOnMouseClicked(t -> {
            TextFieldPane textFieldPane = new TextFieldPane();
            JFXDialog dialog = new JFXDialog(mainpane, textFieldPane, JFXDialog.DialogTransition.CENTER);
            textFieldPane.cencel(() -> {
                dialog.close();
            });
            textFieldPane.save(() -> {
                dialog.close();
                tw = textFieldPane.getText();
                setColor(twitterIcon,tw);
            });
            textFieldPane.setPrompt("Twitter");
            textFieldPane.setText(tw);
            dialog.show();
        });


    }


    @FXML
    public void choosefile() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        Image image = null;
        try {
            image = new Image(file.toURL().toString(),100,100,true,true,true);
        }catch (Exception e){
            e.printStackTrace();
        }
        this.imageview.setImage(image);

    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("Pictures Choose");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }

    @Override
    public void delete() {
        DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Delete", "sabi", "Yes", "No");
        doubleButtonDialog.setButtonOne(() -> {
        });
        doubleButtonDialog.setButtonTwo(() -> {
            setBack();
        });
        doubleButtonDialog.show();
    }


    @Override
    public void refresh(boolean toSwitch) {
        boardController.Loading();
        try {
            if (!updateState.get()) {
                DoubleButtonDialog buttonDialog =
                        new DoubleButtonDialog(mainpane, "Wrong", "sabi", "Last", "Ret");
                buttonDialog.setButtonTwo(() -> boardController.Ret());
                buttonDialog.setButtonTwo(() -> refresh(false));
                Predicate<Integer> p = (i) -> {
                    try {
                        if ((vo = userManagerblService.showDetail(userId)) != null) return true;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            username.setText(vo.getUsername());
                            usertype.getSelectionModel().select(vo.getUsertype().ordinal());
                            email.setText(vo.getEmail());
                            phone.setText(vo.getPhone());
                            comment.setText(vo.getComment());
                            password.setText(vo.getPassword());
                            imageview.setImage(vo.getImage());
                            date.setText(vo.getDate());
                            g = vo.getGithub();
                            f = vo.getFacebook();
                            tw = vo.getTwitter();
                            setColor(facebookIcon,f);
                            setColor(githubIcon,g);
                            setColor(twitterIcon,tw);
                            switchPane(toSwitch);
                        }, buttonDialog, p);

                new Thread(task).start();
            } else {
                switchPane(toSwitch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {
                try {
                if (userId == -1) {

                        userId = userManagerblService.getId();
                        userManagerblService.insert(new UserVO(
                                userId,
                                imageview.getImage(),
                                username.getText(),
                                UserCategory.map.get(usertype.getSelectionModel().getSelectedItem().getText()),
                                LocalDateTime.now(),
                                f, g, tw,
                                email.getText(),
                                phone.getText(),
                                comment.getText(),
                                date.getText(),
                                password.getText()
                        ));
                    } else{
                        userManagerblService.update(new UserVO(
                                userId,
                                imageview.getImage(),
                                username.getText(),
                                UserCategory.map.get(usertype.getSelectionModel().getSelectedItem().getText()),
                                vo.getCreateTime(),
                                f, g, tw,
                                email.getText(),
                                phone.getText(),
                                comment.getText(),
                                date.getText(),
                                password.getText()
                        ));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                setBack();
            });
            doubleButtonDialog.show();

        } else {
            OneButtonDialog oneButtonDialog = new OneButtonDialog(mainpane, "???", "Stupid!", "Accept");
            oneButtonDialog.setButtonOne(() -> {
            });
            oneButtonDialog.show();
        }
    }

    @Override
    public void savePendingReceipt() {
    }

    @Override
    public void saveDraftReceipt() {
    }


    @Override
    public boolean valid() {
        if (!username.getText().equals("") && !password.getText().equals(""))
            return true;
        return false;
    }


    public void setColor(MaterialDesignIconView materialDesignIconView, String s) {
        if (s.equals("")) {
             materialDesignIconView.setFill(Paint.valueOf("#000000"));
        }else{
            materialDesignIconView.setFill(Paint.valueOf("#006AF4"));
        }
    }
*/
