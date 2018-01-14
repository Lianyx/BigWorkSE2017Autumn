package ui.inventoryui.goodsclassificationui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import businesslogic.goodsbl.Goodsbl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import exception.ExistException;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckTreeView;
import ui.inventoryui.goodsui.GoodDetailPane;
import ui.util.BoardController;
import ui.util.PaneFactory;
import vo.inventoryVO.GoodsClassificationVO;
import vo.inventoryVO.GoodsVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class GoodsClassificationTreeView extends TreeView<GoodsClassificationVO>{
    private BoardController boardController;
    private StackPane mainpane;
    private BorderPane borderPane;
    private GoodsClassificationblService goodsClassificationblService;
    private List<GoodsClassificationVO> classificationList;
    private GoodDetailPane goodDetailPane;
    private TreeItem<GoodsClassificationVO> root = new TreeItem<>();
    private Goodsbl goodsbl = new Goodsbl();

  //  private Node rootIcon = new ImageView(new Image("/default/timg.jpg",true));
    //private ImageView imageView = new ImageView(new Image("/default/light.jpg",true));

    public GoodsClassificationTreeView() throws RemoteException, NotBoundException, MalformedURLException {
        super();
        root.setExpanded(true);
    }

    public Node getPane() throws RemoteException {
        TreeItem<GoodsClassificationVO>

        root = buildTree();
        root.setExpanded(true);
        this.setRoot(root);
        this.setEditable(true);
        this.setCellFactory((TreeView<GoodsClassificationVO> p) ->
                new TextFieldTreeCellImpl());


        StackPane stackPane = new StackPane();
        stackPane.getChildren().setAll(this);

        return stackPane;
    }

    public TreeItem<GoodsClassificationVO> getTreeRoot(){
        return root;
    }

    public void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void setMainpane(StackPane mainpane) {
        this.mainpane = mainpane;
    }

    public void setGoodsClassificationblService(GoodsClassificationblService goodsClassificationblService) {
        this.goodsClassificationblService = goodsClassificationblService;
    }

    public void setGoodsClassificationVO(List<GoodsClassificationVO> list) {
        this.classificationList = list;
    }

    private TreeItem<GoodsClassificationVO> buildTree() throws RemoteException {
        Queue<GoodsClassificationVO> que = new LinkedList<>();

        GoodsClassificationVO first = classificationList.get(0);

        que.offer(first);

        root = new CheckBoxTreeItem<>(first);
        TreeItem<GoodsClassificationVO> item = root;

        int index = 1;
        int size = classificationList.size();
        while(!que.isEmpty()){
            GoodsClassificationVO father = que.poll();
            String fatherId = father.getID();
            String fatherName = father.getName();


            for (;index < size; index++){
                GoodsClassificationVO son = classificationList.get(index);
                if (son.getFatherID().equals(fatherId)){
                    TreeItem<GoodsClassificationVO> sonTreeItem = new TreeItem<>(son);

                    item.getChildren().add(sonTreeItem);

                    if(son.getGoodsID() != null)
                        sonTreeItem = buildLeaf(sonTreeItem);
                    else
                        que.offer(son);
                }else {
                    break;
                }
            }
        }
        return root;
    }

    private TreeItem<GoodsClassificationVO> buildLeaf(TreeItem<GoodsClassificationVO> sonTreeItem) throws RemoteException {
        GoodsClassificationVO vo = sonTreeItem.getValue();
        List<String> goodsId = vo.getGoodsID();
        for (String id: goodsId) {
            GoodsVO goodsVO = goodsbl.showDetail(id);
            GoodsClassificationVO goodsClassificationVO = new GoodsClassificationVO();

            //商品节点不设图标
            goodsClassificationVO.setName(goodsVO.getGoodName());
            TreeItem<GoodsClassificationVO> goodTreeItem = new TreeItem<>(goodsClassificationVO);
            sonTreeItem.getChildren().add(goodTreeItem);
        }
        return sonTreeItem;
    }

    private final class TextFieldTreeCellImpl extends TreeCell<GoodsClassificationVO> {

        private TextField textField;
        private final ContextMenu addMenu = new ContextMenu();

        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("增加新分类");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction((ActionEvent t) -> {
                TreeItem newEmployee =
                        new TreeItem<>("New Employee");
                getTreeItem().getChildren().add(newEmployee);
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
          //  setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(((GoodsClassificationVO) getItem()).toString());
           // setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(GoodsClassificationVO item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                //    setGraphic(textField);
                } else {
                    setText(getString());
                  //  setGraphic(getTreeItem().getGraphic());
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(getItem());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }


    //递归调用获取fatherId对应的节点
    private CheckBoxTreeItem<String> getCheckItem(CheckBoxTreeItem<String> currentRoot,String fatherName){
        for(Iterator<TreeItem<String>> iterator = currentRoot.getChildren().iterator(); iterator.hasNext();){

            CheckBoxTreeItem tmp = (CheckBoxTreeItem<String>) iterator.next();

            if(tmp.getValue().equals(fatherName))
                return tmp;
            else {
                tmp = getCheckItem(tmp, fatherName);
                if(tmp != null)
                    return tmp;
            }
        }
        return null;
    }


}
