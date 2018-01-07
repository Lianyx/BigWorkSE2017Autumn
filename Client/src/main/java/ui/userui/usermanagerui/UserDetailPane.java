package ui.userui.usermanagerui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import com.jfoenix.validation.NumberValidator;
import com.sun.org.apache.regexp.internal.RE;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import ui.util.*;
import util.UserCategory;
import vo.UserVO;

import java.io.File;
import java.time.LocalDate;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.RequireValid;

public class UserDetailPane extends ReceiptDetailPane<UserVO> {

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
                    if ((vo = userManagerblService.showDetail(userId)) != null) return true;
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
                if (userId == -1) {
                    userId = userManagerblService.getId();
                    userManagerblService.add(new UserVO(
                            userId,
                            imageview.getImage(),
                            username.getText(),
                            UserCategory.map.get(usertype.getSelectionModel().getSelectedItem().getText()),
                            f, g, tw,
                            email.getText(),
                            phone.getText(),
                            comment.getText(),
                            date.getText(),
                            password.getText()
                    ));
                } else {
                    userManagerblService.update(new UserVO(
                            userId,
                            imageview.getImage(),
                            username.getText(),
                            UserCategory.map.get(usertype.getSelectionModel().getSelectedItem().getText()),
                            f, g, tw,
                            email.getText(),
                            phone.getText(),
                            comment.getText(),
                            date.getText(),
                            password.getText()
                    ));
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

}
