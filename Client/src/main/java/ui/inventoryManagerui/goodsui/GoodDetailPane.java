package ui.inventoryManagerui.goodsui;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import ui.userui.usermanagerui.BoardController;
import ui.util.Refreshable;
import vo.inventoryVO.GoodsVO;


import java.io.File;

public class GoodDetailPane extends Refreshable {

    GoodsVO goodsVO;

    final FileChooser fileChooser = new FileChooser();

    BoardController boardController;

    @FXML
    TextField goodName;
    @FXML
    TextField goodType;
    @FXML
    TextField goodId;
    @FXML
    TextField classifyId;
    @FXML
    TextField inventoryNum;
    @FXML
    TextField purPrice;
    @FXML
    TextField salePrice;
    @FXML
    TextField recentPurPrice;
    @FXML
    TextField recentSalePrice;
    @FXML
    TextField alarmNum;
    @FXML
    ImageView imageview;
    @FXML
    JFXButton sure;
    @FXML
    JFXButton modify;


    SimpleBooleanProperty modifyState = new SimpleBooleanProperty(false);


    public GoodDetailPane(GoodsVO goodsVO,BoardController boardController){
        super();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/inventoryui/goodui/goodsdetail.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        this.boardController = boardController;
        this.goodsVO = goodsVO;

        goodName.setText(goodsVO.getGoodName());
        goodId.setText(goodsVO.getId());
        goodType.setText(goodsVO.getGoodType());
        inventoryNum.setText(Integer.toString(goodsVO.getInventoryNum()));
        classifyId.setText(goodsVO.getClassifyId());
        purPrice.setText(Double.toString(goodsVO.getPurPrice()));
        salePrice.setText(Double.toString(goodsVO.getSalePrice()));
        recentPurPrice.setText(Double.toString(goodsVO.getRecentPurPrice()));
        recentSalePrice.setText(Double.toString(goodsVO.getRecentSalePrice()));
        alarmNum.setText(Integer.toString(goodsVO.getAlarmNumber()));

        //imageview.setImage(userListVO.getImage());
    }

    @FXML
    public void choosefile(){
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(this.getScene().getWindow());
        //  Image image;
        System.out.println(file.getPath());
  /*      if(image.getWidth()!=image.getHeight()){
            JFXDialog dialog=new JFXDialog();
            dialog.setTransitionType(JFXDialog.DialogTransition.CENTER);
            StackPane stackPane=new StackPane();
            stackPane.getChildren().add(new Label("you are stupid."));
            dialog.show(stackPane);
        }*/

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

    //现在才知道fxml的方法也可以直接@FXML
    @FXML
    public void modify(){
        modifyState.setValue(!modifyState.getValue());
        if(modifyState.getValue()==true){
            modify.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.valueOf("#DA4CEE"), modify.getBackground() == null ? null : modify.getBackground().getFills().get(0).getRadii(), null)));
        }else{
            modify.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, modify.getBackground().getFills().get(0).getRadii(), null)));

        }
    }


    @Override
    public void refresh(boolean toSwitch) {

    }
}
