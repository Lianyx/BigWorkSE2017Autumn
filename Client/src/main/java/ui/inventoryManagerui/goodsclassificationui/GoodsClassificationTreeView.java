package ui.inventoryManagerui.goodsclassificationui;

import blService.goodsClassificationblService.GoodsClassificationblService;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.controlsfx.control.CheckTreeView;
import ui.userui.usermanagerui.BoardController;
import vo.inventoryVO.GoodsClassificationVO;

import java.util.*;

public class GoodsClassificationTreeView extends CheckTreeView<String>{
    private BoardController boardController;
    private StackPane mainpane;
    private GoodsClassificationblService goodsClassificationblService;
    private List<GoodsClassificationVO> list;
    private CheckBoxTreeItem<String> root = new CheckBoxTreeItem<>("root");
    private CheckBoxTreeItem<String>chosenItem;
    private JFXButton add = new JFXButton("增加");
    private JFXButton delete = new JFXButton("删除");
    private JFXButton modify = new JFXButton("修改");

    public GoodsClassificationTreeView() {
        super();
        root.setExpanded(true);
        root.setSelected(true);

    }

    public Node getPane(){
      /*  for (String vo: set) {
            root.getChildren().add(new CheckBoxTreeItem<>(vo));
        }*/
        root = buildTree();
        root.setExpanded(true);
        this.setRoot(root);

        //StackPane stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(this);


       // CheckBoxTreeItem<GoodsClassificationVO> selectedItem;
        String selected1;
        this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> change) {
                System.out.println("hehehe2");


                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        deleteItem(change.getList());
                    }
                });

                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addItem(change.getList());
                    }
                });

            }
        });


        HBox buttons = new HBox();
        buttons.getChildren().addAll(add,delete,modify);

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
        this.list = list;
    }

    protected void deleteItem(ObservableList<? extends TreeItem<String>> list){
        CheckBoxTreeItem<String> son = (CheckBoxTreeItem<String>) list.get(0);

        CheckBoxTreeItem<String> parent =(CheckBoxTreeItem<String>)  son.getParent();

        //连同changeList中的数据也要删掉，否则会报错
        parent.getChildren().remove(son);
        list.remove(son);
    }

    protected void addItem(ObservableList<? extends TreeItem<String>> list){
      /*  final String selected = list.get(0).getValue();

        CheckBoxTreeItem<String> item = getItem(list);

        System.out.println(item.getValue());

        item.getChildren().add(new CheckBoxTreeItem<>("shit"));*/
    }

    private CheckBoxTreeItem<String> buildTree(){
        Queue<GoodsClassificationVO> que = new LinkedList<>();
        que.offer(list.get(0));

        root = new CheckBoxTreeItem<>(list.get(0).getID());
        CheckBoxTreeItem<String> item = root;

        int index = 1;
        int size = list.size();
        while(!que.isEmpty()){
            GoodsClassificationVO father = que.poll();
            String fatherId = father.getID();

           if (index != 1) {
               item = getCheckItem(root, fatherId);
           }

            for (;index < size; index++){
                GoodsClassificationVO son = list.get(index);
                if (son.getFatherID() == fatherId){
                    item.getChildren().add(new CheckBoxTreeItem<>(son.getID()));
                    que.offer(son);
                }else {
                    break;
                }
            }
        }

        return root;
    }

    //递归调用获取fatherId对应的节点
    private CheckBoxTreeItem<String> getCheckItem(CheckBoxTreeItem<String> currentRoot,String fatherId){
        for(Iterator<TreeItem<String>> iterator = currentRoot.getChildren().iterator(); iterator.hasNext();){

            CheckBoxTreeItem tmp = (CheckBoxTreeItem<String>) iterator.next();

            if(tmp.getValue().equals(fatherId))
                return tmp;
            else {
                tmp = getCheckItem(tmp, fatherId);
                if(tmp != null)
                    return tmp;
            }
        }
        return null;
    }

  /*  protected CheckBoxTreeItem<String> getCheckItem(ObservableList<? extends TreeItem<String>> list){
        CheckBoxTreeItem<String> son = (CheckBoxTreeItem<String>) list.get(list.size()-1).getParent();
        CheckBoxTreeItem<String> parent =(CheckBoxTreeItem<String>)  son.getParent();
        parent.getChildren().remove(son);
        return lastItem;
    }*/

}
