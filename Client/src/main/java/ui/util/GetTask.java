package ui.util;

import com.jfoenix.controls.JFXDialog;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class GetTask<T, S> extends Task<Boolean> {

    private SimpleIntegerProperty integerProperty = new SimpleIntegerProperty(-1);

    Runnable business;

    Predicate<Integer> p;

    public int getIntegerProperty() {
        return integerProperty.get();
    }

    public GetTask(Runnable runnable, JFXDialog dialog,Predicate<Integer> p) {
        this.business = business;
        this.p = p;
        this.valueProperty().addListener((b, o, n) -> {
            if (getIntegerProperty() == 1) {
                Platform.runLater(runnable);
            } else if (getIntegerProperty() == 0) {
                dialog.show();
            }
        });
    }

    @Override
    protected Boolean call() throws Exception {
        if(p.test(0)){
            Thread.sleep(1000);
            integerProperty.setValue(1);
            return true;
        } else {
            Thread.sleep(1000);
            integerProperty.set(0);
            return false;
        }
    }

}
