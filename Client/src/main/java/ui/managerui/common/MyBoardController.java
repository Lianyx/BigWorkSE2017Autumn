package ui.managerui.common;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import ui.util.*;

public class MyBoardController extends BoardController {
    private static class HistoryRecord { // 希望所有的和HistoryRecord有关的都做到BoardController里面
        // TODO 哪里observe了…
        private static ObservableList<Refreshable> record = FXCollections.observableArrayList();
        private static int index = -1;
        private static SimpleBooleanProperty canBack = new SimpleBooleanProperty(false);
        private static SimpleBooleanProperty canForward = new SimpleBooleanProperty(false);

        private static void setBackAndForwardProperty() {
            canBack.setValue(index > 0);
            canForward.setValue(index != record.size() - 1);
        }


        static boolean addRecord(Refreshable pane) {
            if (index == -1) {
                record.add(pane);
                index++;
                return false; // 返回值只是为了是否有动画，因此false符合反回值原意
            }
//            System.out.println(index);
            if (record.get(index).getId().equals(pane.getId())) {
//            setBackAndForwardProperty(); // 这里什么都没干，应该不用更新吧。
                return false;
            }

            if (index != (record.size() - 1)) {
                record.remove(index + 1, record.size());
            }

            record.add(pane);
            index++;

            setBackAndForwardProperty();
            return true;
        }


        static Refreshable pop() {
            index--;
            setBackAndForwardProperty();
            return record.get(index);
        }

        // 这个叫push真的好吗？又不是stack
        static Refreshable push() {
            index++;
            setBackAndForwardProperty();
            return record.get(index);
        }
    }

    /**
     * From here
     */
    private static MyBoardController myBoardController;

    private AnchorPane savePane;

    private MyBoardController(BoardController boardController) {
        this.board = boardController.board;
//        System.out.println(board);

        setPaneSwitchAnimation(new PaneSwitchAnimation(Duration.millis(250), board));
        paneSwitchAnimation.setAnimation(ContainerAnimations.FADE);
        // TODO 这里应该有默认的才对吧，而且我继承过来应该是正确的啊
    }


    public void switchTo(Refreshable pane) {
        System.out.println(pane);
        Platform.runLater(() -> {
            if (HistoryRecord.addRecord(pane)) {
                paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);
                paneSwitchAnimation.setNode(pane);
            } else {
                board.getChildren().setAll(pane);
            }
        });
    }

    // 这两个还好暂时不override，refresh也还行，现在只是为了不要忘了所以放这。
    // 但是可以封装吗？
    public void Loading() {
        if (!board.getChildren().isEmpty() && !(board.getChildren().get(0) instanceof Loading)) {
            savePane = (AnchorPane) board.getChildren().get(0);
        }
        Loading loading = new Loading();
        board.getChildren().setAll(loading);
        loading.setTranslateX(280);
    }

    public void Ret() {
        if (savePane != null) {
            board.getChildren().setAll(savePane);
//            System.out.println("ret to " + savePane);
        }
    }

    public void refresh() {
        ((Refreshable) board.getChildren().get(0)).refresh(false);
    }


    private void notSupport() {
        System.err.println("not support !!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                "!!!!!!!!!!!!!");
    }


    public void setAll(Node node) {
        notSupport();
        System.out.println("not support setAll");
    }

    // 这两个也不应该有，自己内部解决就可以了啊！！至少也可以private
    // 但是这样不能测外面有没有用，所以先notSupport了
    // 而且override不能throw exception
    public void setLeftAnimation() {
        notSupport();
        System.out.println("not support setLeftAnimation");
    }

    public void setRightAnimation() {
        notSupport();
        System.out.println("not support setRightAnimation");
    }

    public void historicalSwitchTo(AnchorPane pane) {
        notSupport();
        System.out.println("not support historicalSwitchTo");
    }

    public void goBack() {
        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_RIGHT);
        paneSwitchAnimation.setNode(HistoryRecord.pop());
        refresh();
    }

    public void goForward() {
        paneSwitchAnimation.setAnimation(ContainerAnimations.SWIPE_LEFT);
        paneSwitchAnimation.setNode(HistoryRecord.push());
        refresh();
    }

    // 像这种是专供给MyTopBar用的。
    // 好像上面两个也是…
    public BooleanProperty canBackProperty() {
        return HistoryRecord.canBack;
    }

    public BooleanProperty canForwardProperty() {
        return HistoryRecord.canForward;
    }


    public static MyBoardController getMyBoardController() {
        if (myBoardController == null) {
            return myBoardController = new MyBoardController(BoardController.getBoardController());
        }
        return myBoardController;
    }


}
