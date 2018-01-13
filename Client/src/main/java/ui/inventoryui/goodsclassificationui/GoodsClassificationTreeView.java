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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.CheckTreeView;
import ui.inventoryui.goodsui.GoodDetailPane;
import ui.util.BoardController;
import ui.util.PaneFactory;
import vo.inventoryVO.GoodsClassificationVO;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class GoodsClassificationTreeView extends CheckTreeView<String>{
    private BoardController boardController;
    private StackPane mainpane;
    private BorderPane borderPane;
    private GoodsClassificationblService goodsClassificationblService;
    private List<GoodsClassificationVO> classificationList;
    private GoodDetailPane goodDetailPane;
    private CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("root");
    private JFXButton addGood = new JFXButton("增加商品");
    private JFXButton add = new JFXButton("增加");
    private JFXButton delete = new JFXButton("删除");
    private JFXButton modify = new JFXButton("修改");

    public GoodsClassificationTreeView() {
        super();
        root.setExpanded(true);
        root.setSelected(true);

    }

    public Node getPane(){
        root = buildTree();
        root.setExpanded(true);
        this.setRoot(root);
        this.setEditable(true);
        /*this.setCellFactory((TreeView<String> p) ->
                new TextFieldTreeCellImpl());*/

        borderPane = new BorderPane();

        borderPane.setCenter(this);

        String selected1;
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> change) {
                //System.out.println("hehehe2");


                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            deleteItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                });

                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addItem(change.getList());
                    }
                });

                modify.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            modifyItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                    }
                });


                addGood.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       // goodDetailPane = new GoodDetailPane(true);
                        try {
                            addGoodItem(change.getList());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (NotBoundException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });

     }
        });


        HBox buttons = new HBox();
        buttons.getChildren().addAll(add,delete,modify,addGood);

        borderPane.setRight(buttons);

        return borderPane;
    }

    public CheckBoxTreeItem<String> getTreeRoot(){
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

    protected void deleteItem(ObservableList<? extends TreeItem<String>> list) throws RemoteException {
        CheckBoxTreeItem<String> son = (CheckBoxTreeItem<String>) list.get(0);

        CheckBoxTreeItem<String> parent =(CheckBoxTreeItem<String>)  son.getParent();



        //连同changeList中的数据也要删掉，否则会报错
        parent.getChildren().remove(son);
        list.remove(son);

        GoodsClassificationVO fatherVO = null;
        GoodsClassificationVO sonVO = null;

        String sonName = son.getValue();
        String fatherName = parent.getValue();

        for (GoodsClassificationVO vo: classificationList) {
            if(vo.getName().equals(fatherName)){
                fatherVO = vo;
                continue;
            }
            if(vo.getName().equals(sonName)){
                sonVO = vo;
            }
        }

        String sonId = sonVO.getID();
        fatherVO.getChildrenId().remove(sonId);
        goodsClassificationblService.updateGoodsClassification(fatherVO);
        goodsClassificationblService.deleteGoodsClassification(sonVO);

        //List<String> fatherVO.getChildrenId();
        //goodsClassificationblService.deleteGoodsClassification(son);
    }

    protected void addItem(ObservableList<? extends TreeItem<String>> list){
        CheckBoxTreeItem<String> item = (CheckBoxTreeItem<String>) list.get(0);

        String classifyname = item.getValue();




        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        jfxDialogLayout.setHeading(new Label("GoodsClassificationName"));
        JFXTextField name = new JFXTextField();
        jfxDialogLayout.setBody(name);
        JFXButton save = new JFXButton("保存");
        JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        save.setOnAction(e -> {
            String classifyName = name.getText();

            CheckBoxTreeItem<String> son = new CheckBoxTreeItem<>(classifyName);
            item.getChildren().add(son);

            try {
                System.out.println(classifyName+" "+item.getValue());
                addGoodsClassificationVO(classifyName,item.getValue());
            }  catch (RemoteException e1) {
                e1.printStackTrace();
            }

            dialog.close();
        });
        jfxDialogLayout.setActions(save);
        dialog.show();


    }

    protected void addGoodItem(ObservableList<? extends TreeItem<String>> list) throws RemoteException, MalformedURLException, NotBoundException {
        CheckBoxTreeItem<String> son = (CheckBoxTreeItem<String>) list.get(0);

        String classifyName = son.getValue();
        String clssifyId = null;

        for (GoodsClassificationVO vo:classificationList) {
            if(vo.getName().equals(classifyName)){
                goodDetailPane = new GoodDetailPane(true);

                System.out.println("set first");

                clssifyId = vo.getID();
                System.out.println(clssifyId);
                goodDetailPane.setClassifyId(clssifyId);


                List<String> goodsId = vo.getGoodsID();

                int order = goodsId.size()+1;
                goodDetailPane.setOrder(order);

                goodsId.add(new Goodsbl().getID(clssifyId,order));

                vo.setGoodsID(goodsId);

                goodsClassificationblService.updateGoodsClassification(vo);
                break;
            }
        }


    }

    /**
     * 传入子分类名和父分类名，更新数据库
     * @param sonName
     * @param fatherName
     * @throws ExistException
     */
    private void addGoodsClassificationVO(String sonName, String fatherName) throws RemoteException {
        for (GoodsClassificationVO vo:classificationList) {
          //  System.out.println(vo.getName());
            if (vo.getName().equals(fatherName)) {
                List<String> childernId = vo.getChildrenId();

                String classifyId = vo.getFatherID() + "/" + Integer.toString(childernId.size());
                GoodsClassificationVO goodsClassificationVO = new GoodsClassificationVO(classifyId,sonName,vo.getID());
              //  System.out.println(goodsClassificationVO);
                goodsClassificationblService.addGoodsClassification(goodsClassificationVO);

                childernId.add(classifyId);
                vo.setChildrenId(childernId);
                goodsClassificationblService.updateGoodsClassification(vo);

                break;
            }
        }
    }

    protected void modifyItem(ObservableList<? extends TreeItem<String>> list) throws RemoteException{
        CheckBoxTreeItem<String> item = (CheckBoxTreeItem<String>) list.get(0);

        JFXDialogLayout jfxDialogLayout = new JFXDialogLayout();
        jfxDialogLayout.setPrefWidth(220.0);
        jfxDialogLayout.setHeading(new Label("商品分类名"));
        JFXTextField name = new JFXTextField();
        name.setText(item.getValue());
        name.setPrefWidth(210);
        jfxDialogLayout.setBody(name);
        JFXButton save = new JFXButton("保存");
        JFXDialog dialog = new JFXDialog(mainpane, jfxDialogLayout, JFXDialog.DialogTransition.CENTER);

        String oldName = item.getValue();

        save.setOnAction(e -> {
            String classifyName = name.getText();
            item.setValue(classifyName);
            dialog.close();

            try {
                updateGoodsClassificationVO(classifyName,oldName);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
        });
        jfxDialogLayout.setActions(save);
        dialog.show();

    }


    /**
     * 修改分类信息时，更新数据库
     * @param newName
     * @param oldName
     */
    private void updateGoodsClassificationVO(String newName, String oldName) throws RemoteException{
        for (GoodsClassificationVO vo:classificationList) {
            if(vo.getName().equals(oldName)){
                vo.setName(newName);
                goodsClassificationblService.updateGoodsClassification(vo);
                break;
            }
        }

    }

    private CheckBoxTreeItem<String> buildTree(){
        Queue<GoodsClassificationVO> que = new LinkedList<>();
        que.offer(classificationList.get(0));

        root = new CheckBoxTreeItem<>("root");
        CheckBoxTreeItem<String> item = root;

        int index = 1;
        int size = classificationList.size();
        while(!que.isEmpty()){
            GoodsClassificationVO father = que.poll();
            String fatherId = father.getID();
            String fatherName = father.getName();

           if (index != 1) {
               item = getCheckItem(root, fatherName);
           }

            for (;index < size; index++){
                GoodsClassificationVO son = classificationList.get(index);

                if (son.getFatherID().equals(fatherId)){
                    CheckBoxTreeItem<String> sonTreeItem = new CheckBoxTreeItem<>(son.getName());

                    item.getChildren().add(sonTreeItem);
                    que.offer(son);

                    if(son.getGoodsID() != null)
                        sonTreeItem = buildLeaf(sonTreeItem,son);
                }else {
                    break;
                }
            }
        }

        return root;
    }

    private CheckBoxTreeItem<String> buildLeaf(CheckBoxTreeItem<String> sonTreeItem, GoodsClassificationVO son) {
        List<String> goods = son.getGoodsID();
        for (String name: goods) {
            CheckBoxTreeItem<String> goodTreeItem = new CheckBoxTreeItem<>(name);
            sonTreeItem.getChildren().add(goodTreeItem);
        }
        return sonTreeItem;
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
