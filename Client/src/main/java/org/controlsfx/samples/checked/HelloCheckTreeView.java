/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.controlsfx.samples.checked;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.controlsfx.ControlsFXSample;
import org.controlsfx.control.CheckModel;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.control.CheckTreeView;
import org.controlsfx.samples.Utils;

import java.util.Iterator;

public class HelloCheckTreeView extends ControlsFXSample {
    
    private final Label checkedItemsLabel = new Label();
    private final Label selectedItemsLabel = new Label();
    
    private CheckTreeView<String> checkTreeView;
    
    private CheckBoxTreeItem<String> treeItem_Jonathan = new CheckBoxTreeItem<>("Jonathan");
    private CheckBoxTreeItem<String> treeItem_Eugene = new CheckBoxTreeItem<>("Eugene");
    private CheckBoxTreeItem<String> treeItem_Henry = new CheckBoxTreeItem<>("Henry");
    private CheckBoxTreeItem<String> treeItem_Samir = new CheckBoxTreeItem<>("Samir");
    private CheckBoxTreeItem<String> treeItem_Sam = new CheckBoxTreeItem<>("Sam");

    @Override public String getSampleName() {
        return "CheckTreeView";
    }
    
    @Override public String getJavaDocURL() {
        return Utils.JAVADOC_BASE + "org/controlsfx/control/CheckTreeView.html";
    }
    
    @Override public String getSampleDescription() {
        return "A simple UI control that makes it possible to select zero or "
                + "more items within a TreeView without the need to set a custom "
                + "cell factory or manually create boolean properties for each "
                + "row - simply use the check model property to request the "
                + "current selection state.";
    }
    
    @SuppressWarnings("unchecked")
    @Override public Node getPanel(Stage stage) {
        CheckBoxTreeItem<String> root = new CheckBoxTreeItem<String>("Root");
        root.setExpanded(true);
        root.getChildren().addAll(
                treeItem_Jonathan,
                treeItem_Eugene,
                treeItem_Henry,
                treeItem_Samir);

        treeItem_Samir.getChildren().add(treeItem_Sam);
        
        // lets check Eugene to make sure that it shows up in the tree,可以提前设置每个节点是否被选中
        treeItem_Eugene.setSelected(true);
        treeItem_Sam.setSelected(true);

        //这个button 用来 获取 勾选的item，结果在控制台打印
        Button btn=new Button("show selected");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //此处使用getSelectionModel().getSelectedItems()会取得 点选的条目，而不是勾选的条目
                //System.out.println(tree.getSelectionModel().getSelectedItems().get(0));

                //此处使用rootItem.getChildren().iterator() 后 得到的TreeItem强制转为CheckBoxTreeItem，利用CheckBoxTreeItem的isSelected()
                //将会取得勾选的条目 点选的不会取得
                for(Iterator<TreeItem<String>> iterator = root.getChildren().iterator(); iterator.hasNext();){
                    CheckBoxTreeItem<String> item=(CheckBoxTreeItem<String>) iterator.next();
                    if(item.isSelected()){
                        //获得勾选的item
                        System.out.println(item);
                    }
                }
            }
        });

        
        // CheckListView
        checkTreeView = new CheckTreeView<>(root);
        //对选中的返回SelectionMode，主要用于处理事件，MULTIPLE提供了多选监听
        checkTreeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //获取选中的item并监听
        checkTreeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c) {
                updateText(selectedItemsLabel, c.getList());
                //当选中整个子节点的时候可以得到
                System.out.println("hehehe1");
            }
        });
        
        checkTreeView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TreeItem<String>>() {
            @Override public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> change) {
                updateText(checkedItemsLabel, change.getList());
                //当选中框的时候可以得到
                System.out.println("hehehe2");
                while (change.next()) {
                    System.out.println("============================================");
                    System.out.println("Change: " + change);
                    System.out.println("Added sublist " + change.getAddedSubList());
                    System.out.println("Removed sublist " + change.getRemoved());
                    System.out.println("List " + change.getList());
                    System.out.println("Added " + change.wasAdded() + " Permutated " + change.wasPermutated() + " Removed " + change.wasRemoved() + " Replaced "
                            + change.wasReplaced() + " Updated " + change.wasUpdated());
                    System.out.println("============================================");
                }
            }
        });
        
        StackPane stackPane = new StackPane(checkTreeView);
        stackPane.setPadding(new Insets(30));
        return stackPane;
    }

    //右边的控制模块
    @Override public Node getControlPanel() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));
        
        int row = 0;

        //勾选中的项目
        Label label1 = new Label("Checked items: ");
        label1.getStyleClass().add("property");
        grid.add(label1, 0, row);
        grid.add(checkedItemsLabel, 1, row++);
        updateText(checkedItemsLabel, checkTreeView.getCheckModel().getCheckedItems());

        //选中的项目
        Label label2 = new Label("Selected items: ");
        label2.getStyleClass().add("property");
        grid.add(label2, 0, row);
        grid.add(selectedItemsLabel, 1, row++);
        updateText(selectedItemsLabel, checkTreeView.getSelectionModel().getSelectedItems());

        //
        Label checkItem2Label = new Label("Check 'Jonathan': ");
        checkItem2Label.getStyleClass().add("property");
        grid.add(checkItem2Label, 0, row);
        final CheckBox checkItem2Btn = new CheckBox();
        checkItem2Btn.selectedProperty().bindBidirectional(treeItem_Jonathan.selectedProperty());
        grid.add(checkItem2Btn, 1, row++);
        
        return grid;
    }
    
    protected void updateText(Label label, ObservableList<? extends TreeItem<String>> list) {
        final StringBuilder sb = new StringBuilder();
        
        if (list != null) {
            for (int i = 0, max = list.size(); i < max; i++) {
                sb.append(list.get(i).getValue());
                if (i < max - 1) {
                    sb.append(", ");
                }
            }
        }
        
        final String str = sb.toString();
        label.setText(str.isEmpty() ? "<empty>" : str);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
