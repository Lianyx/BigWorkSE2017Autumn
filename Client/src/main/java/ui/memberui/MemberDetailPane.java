package ui.memberui;

import blService.blServiceFactory.ServiceFactory_Stub;
import blService.memberblService.MemberblService;
import blService.userblService.UserManagerblService;
import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import org.controlsfx.control.Rating;
import ui.util.*;
import util.MemberCategory;
import util.UserCategory;
import vo.MemberVO;
import vo.UserVO;

import java.io.File;
import java.util.function.Predicate;

import static ui.util.ValidatorDecorator.DoubleValid;
import static ui.util.ValidatorDecorator.RequireValid;

public class MemberDetailPane extends ReceiptDetailPane<MemberVO>{

    int id = -1;

    final FileChooser fileChooser = new FileChooser();

    MemberblService memberblService;

    @FXML
    Rating rating;
    @FXML
    TextField address;
    @FXML
    TextField clerkName;
    @FXML
    JFXTextField receiableAmount;
    @FXML
    JFXTextField give;
    @FXML
    JFXTextField get;
    @FXML
    JFXTextField memberName;
    @FXML
    JFXComboBox<Label> memberCategory;
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
    TextField zipcode;
    @FXML
    Label memberId;




    public MemberDetailPane(int id) {
        this(false);
        this.id = id;
        memberId.setText(id+"");
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



        memberName.disableProperty().bind(modifyState.not());
        memberCategory.disableProperty().bind(modifyState.not());
        email.disableProperty().bind(modifyState.not());
        phone.disableProperty().bind(modifyState.not());
        address.disableProperty().bind(modifyState.not());
        clerkName.disableProperty().bind(modifyState.not());
        receiableAmount.disableProperty().bind(modifyState.not());
        comment.disableProperty().bind(modifyState.not());
        zipcode.disableProperty().bind(modifyState.not());
        save.visibleProperty().bind(modifyState);
        choose.visibleProperty().bind(modifyState);
        save.visibleProperty().bind(modifyState);
    }

    public MemberDetailPane(boolean isAdd) {
        super("/memberui/memberdetailpane.fxml");
        memberblService = ServiceFactory_Stub.getService(MemberblService.class.getName());

        memberCategory.getItems().add(new Label(MemberCategory.SELLER.name()));
        memberCategory.getItems().add(new Label(MemberCategory.SUPPLIER.name()));
        memberCategory.getSelectionModel().select(0);

        give.setText("0");
        get.setText("0");
        receiableAmount.setText("0");

        delete.setVisible(false);
        RequireValid(memberName);
        RequireValid(clerkName);
        DoubleValid(receiableAmount);


        updateState.set(false);
        if (isAdd) {
            updateState.set(true);
            switchPane(true);
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
                    if ((vo = memberblService.showDetail(id)) != null) return true;
                    return false;
                };
                GetTask task =
                        new GetTask(() -> {
                            memberName.setText(vo.getName());
                            memberCategory.getSelectionModel().select(vo.getMemberCategory().ordinal());
                            email.setText(vo.getEmail());
                            phone.setText(vo.getPhone());
                            comment.setText(vo.getComment());
                            address.setText(vo.getAddress());
                            imageview.setImage(vo.getImage());
                            receiableAmount.setText(vo.getReceiableAmount()+"");
                            get.setText(vo.getGet()+"");
                            give.setText(vo.getGive()+"");
                            rating.setRating(vo.getDegree());
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

    /**
    *    int memberId;
     MemberCategory memberCategory;
     Image image;
     int degree;
     String name;
     String phone;
     String address;
     String email;
     double receiableAmount;
     double give;
     double get;
     String clerkName;
     String comment;
     */


    @Override
    public void save() {
        if (valid()) {
            modify.modifyProperty().set(false);
            DoubleButtonDialog doubleButtonDialog = new DoubleButtonDialog(mainpane, "Pending?", "sabi", "Yes", "No");
            doubleButtonDialog.setButtonTwo(() -> {
            });
            doubleButtonDialog.setButtonOne(() -> {
                if (id == -1) {
                    id = memberblService.getId();
                    memberblService.add(new MemberVO(
                            id,
                            MemberCategory.map.get(memberCategory.getSelectionModel().getSelectedItem().getText()),
                            imageview.getImage(),
                            (int)rating.getRating(),
                            memberName.getText(),
                            phone.getText(),
                            address.getText(),
                            email.getText(),
                            zipcode.getText(),
                            Double.valueOf(receiableAmount.getText()),
                            Double.valueOf(give.getText()),
                            Double.valueOf(get.getText()),
                            clerkName.getText(),
                            comment.getText()
                    ));
                } else {
                    memberblService.update(new MemberVO(
                            id,
                            MemberCategory.map.get(memberCategory.getSelectionModel().getSelectedItem().getText()),
                            imageview.getImage(),
                            (int)rating.getRating(),
                            memberName.getText(),
                            phone.getText(),
                            address.getText(),
                            email.getText(),
                            zipcode.getText(),
                            Double.valueOf(receiableAmount.getText()),
                            Double.valueOf(give.getText()),
                            Double.valueOf(get.getText()),
                            clerkName.getText(),
                            comment.getText()
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
        if (!memberName.getText().equals("") && !receiableAmount.getText().equals("")&&!clerkName.getText().equals(""))
            return true;
        return false;
    }

}
