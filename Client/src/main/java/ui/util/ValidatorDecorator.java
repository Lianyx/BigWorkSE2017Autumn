package ui.util;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;


public class ValidatorDecorator {
    private static ValidationSupport validationSupport = new ValidationSupport();


    public static void RequireValid(TextField validationField){
        validationSupport.registerValidator(validationField, Validator.createEmptyValidator("Input Required"));
    }





    public static void RequireValid(JFXTextField validationField){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        validator.setMessage("Input Required");
        validator.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("1em")
                .styleClass("ERROR")
                .build());
        validationField.getValidators().add(validator);
        validationField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                validationField.validate();
            }
        });
    }




    public static void NumberValid(JFXTextField jfxTextField) {
        NumberValidator validator = new NumberValidator();
        validator.setMessage("Wrong Input");
        jfxTextField.getValidators().add(validator);
        jfxTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    try {
                        jfxTextField.validate();
                    } catch (Exception e) {
                    }
                }
            }
        });

    }


    public static void DoubleValid(JFXTextField jfxTextField) {
        DoubleValidator validator = new DoubleValidator();
        validator.setMessage("Wrong Input");
        jfxTextField.getValidators().add(validator);
        jfxTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (!newValue) {
                    try {
                        jfxTextField.validate();
                    } catch (Exception e) {
                    }
                }
            }
        });

    }

}
