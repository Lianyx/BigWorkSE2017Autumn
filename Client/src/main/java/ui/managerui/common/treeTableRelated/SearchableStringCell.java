package ui.managerui.common.treeTableRelated;

import com.jfoenix.controls.cells.editors.base.JFXTreeTableCell;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.Set;

public class SearchableStringCell<T> extends JFXTreeTableCell<T, String> {
    private class OrdinaryLable extends Label {
        private OrdinaryLable(String text) {
            super(text);
            this.setStyle("-fx-background-color: white;-fx-text-fill: black");
        }
    }

    private class HighlightLabel extends Label {
        private HighlightLabel(String text) {
            super(text);
            this.setStyle("-fx-background-color: khaki;-fx-text-fill: black");
        }
    }

    /**
     *
     * */

    private FlowPane contentPane = new FlowPane();
    private StringProperty keyWordProperty; // 这个本来可以不持有引用的唉，再说再说
    private String item;

    public SearchableStringCell(StringProperty keyWordProperty) {
        this.keyWordProperty = keyWordProperty;
        keyWordProperty.addListener((observable, oldValue, newValue) -> {
            renderText(newValue);
        });
    }

    @Override
    // get called whenever it should be rendered。应该只有初始化的时候才需要render吧
    protected void updateItem(String item, boolean empty) {
        if (empty) {
            setGraphic(null);
        } else {
            this.item = item; // 这里很奇怪，他有一个itemProperty，但是感觉会有问题。所以就自己设了
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            setGraphic(contentPane);

            renderText(keyWordProperty.get());
        }
    }

    private void renderText(String newValue) {
        contentPane.getChildren().clear();

        String toBeProcessed = item;

        // 这个不得不加，不然会有nullPointerException，虽然我不知道为什么一定要加。而且下面也很奇怪，为什么外面的那个toBeProcessed就不会nullpointer了
        boolean satisfyKeywordFilter = false;
        if (toBeProcessed != null) {
            if (newValue != null && !newValue.equals("")) {
                int index;
                while ((index = toBeProcessed.indexOf(newValue)) != -1) {
                    satisfyKeywordFilter = true;
                    contentPane.getChildren().add(new OrdinaryLable(toBeProcessed.substring(0, index)));
                    contentPane.getChildren().add(new HighlightLabel(toBeProcessed.substring(index, index + newValue.length())));

                    toBeProcessed = toBeProcessed.substring(index + newValue.length());
                }
            } else {
                satisfyKeywordFilter = true;
            }
            contentPane.getChildren().add(new OrdinaryLable(toBeProcessed));
//            System.out.println(keywordFilterList);
//            if (satisfyKeywordFilter && keywordFilterList != null) {
//                keywordFilterList.add(getTreeTableRow().getTreeItem().getValue());
//            }
        }
    }
}
