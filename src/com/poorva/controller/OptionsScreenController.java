package com.poorva.controller;

import com.poorva.EmailManager;
import com.poorva.view.FontSize;
import com.poorva.view.Theme;
import com.poorva.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class OptionsScreenController extends BaseController implements Initializable {

    public OptionsScreenController(EmailManager emailManager, ViewFactory viewFactory, String fxmlName) {
        super(emailManager, viewFactory, fxmlName);
    }
    @FXML
    private Slider fontSizePicker;

    @FXML
    private ChoiceBox<Theme> themeSelectors;

    @FXML
    void applyButtonAction(ActionEvent event) {
        viewFactory.setTheme(themeSelectors.getValue());
        viewFactory.setFontSize(FontSize.values()[(int)fontSizePicker.getValue()]);
        viewFactory.updateAllStyles();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) fontSizePicker.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    // It is called right after the object is created
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpThemePicker();
        setUpSizePicker();
    }

    private void setUpSizePicker() {
        fontSizePicker.setMin(0);
        fontSizePicker.setMax(FontSize.values().length - 1);
        fontSizePicker.setValue(viewFactory.getFontSize().ordinal());
        fontSizePicker.setMajorTickUnit(1);
        fontSizePicker.setMinorTickCount(0);
        fontSizePicker.setBlockIncrement(1);
        fontSizePicker.setSnapToTicks(true);
        fontSizePicker.setShowTickMarks(true);
        fontSizePicker.setShowTickLabels(true);

        // It changes the values from number to the enums values on slider
        fontSizePicker.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double object) {
                int i = object.intValue();
                return FontSize.values()[i].toString();
            }

            @Override
            public Double fromString(String s) {
                return null;
            }
        });

        //snaps to the values directly
        fontSizePicker.valueProperty().addListener((obs, oldValue, newValue) -> {
            fontSizePicker.setValue(newValue.intValue());
        });
    }

    private void setUpThemePicker() {
        themeSelectors.setItems(FXCollections.observableArrayList(Theme.values()));
        themeSelectors.setValue(viewFactory.getTheme());
    }
}
