package ru.unn.agile.range.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.unn.agile.range.model.Range;

import java.util.Arrays;

public class ViewModel {

    private BooleanProperty btnGetEndPointsDisabled = new SimpleBooleanProperty();
    private BooleanProperty btnGetAllPointsDisabled = new SimpleBooleanProperty();
    private BooleanProperty btnContainsDisabled = new SimpleBooleanProperty();
    private BooleanProperty btnEqualsDisabled = new SimpleBooleanProperty();
    private BooleanProperty btnOverlapsDisabled = new SimpleBooleanProperty();
    private StringProperty txtInput = new SimpleStringProperty();
    private StringProperty txtRange = new SimpleStringProperty();
    private StringProperty txtResult = new SimpleStringProperty();
    private final String YES = "Yes";
    private final String NO = "No";

    private Range range;

    public ViewModel() {
        btnGetEndPointsDisabled.setValue(true);
        btnGetAllPointsDisabled.setValue(true);
        btnOverlapsDisabled.setValue(true);
        btnEqualsDisabled.setValue(true);
        btnContainsDisabled.setValue(true);

        txtResult.setValue("");
        txtRange.setValue("");
        txtInput.setValue("");

        txtRange.addListener((observable, oldValue, newValue) -> {
            setRange(newValue);
        });
        txtInput.addListener((observable, oldValue, newValue) -> {
            setInput(newValue);
        });
    }

    public BooleanProperty isGetEndPointsButtonDisabled() {
        return btnGetEndPointsDisabled;
    }

    public BooleanProperty isGetAllPointsButtonDisabled() {
        return btnGetAllPointsDisabled;
    }

    public BooleanProperty isContainsButtonDisabled() {
        return btnContainsDisabled;
    }

    public BooleanProperty isEqualsButtonDisabled() {
        return btnEqualsDisabled;
    }

    public BooleanProperty isOverlapsButtonDisabled() {
        return btnOverlapsDisabled;
    }

    public StringProperty getTxtRange() {
        return txtRange;
    }

    public StringProperty getTxtResult() {
        return txtResult;
    }

    public StringProperty getTxtInput() {
        return txtInput;
    }

    public void containsInput() {
        String input = txtInput.get();

        if (isInteger(input)) {
            if (range.containsValue(Integer.parseInt(input))) {
                txtResult.setValue(YES);
            } else {
                txtResult.setValue(NO);
            }
        }
        if (isIntegerSet(input)) {
            if (range.containsSet(Arrays.stream(input.substring(1, input.length() - 1).split(","))
                    .map(String::trim).mapToInt(Integer::parseInt).toArray())) {
                txtResult.setValue(YES);
            } else {
                txtResult.setValue(NO);
            }
        }
        if (isRange(input)) {
            if (range.containsRange(new Range(input))) {
                txtResult.setValue(YES);
            } else {
                txtResult.setValue(NO);
            }
        }
    }

    public void overlapsRange() {
        String input = txtInput.get();
        if (isRange(input)) {
            if (range.overlapsRange(new Range(input))) {
                txtResult.setValue(YES);
            } else {
                txtResult.setValue(NO);
            }
        }
    }

    public void equalsRange() {
        String input = txtInput.get();
        if (isRange(input)) {
            if (range.equals(new Range(input))) {
                txtResult.setValue(YES);
            } else {
                txtResult.setValue(NO);
            }
        }
    }

    public void getAllPoints() {
        txtResult.setValue(Arrays.toString(range.getAllPoints()));
    }

    public void getEndPoints() {
        txtResult.setValue(Arrays.toString(range.endPoints()));
    }


    private void setRange(final String input) {

        var isCorrectInput = true;

        try {
            this.range = new Range(input);
        } catch (IllegalArgumentException ex) {
            isCorrectInput = false;
        }

        btnGetAllPointsDisabled.setValue(!isCorrectInput);
        btnGetEndPointsDisabled.setValue(!isCorrectInput);
        disableAllButtonsConnectedWithInput();
    }

    private void setInput(final String input) {
        disableAllButtonsConnectedWithInput();

        if (btnGetEndPointsDisabled.get()) {
            return;
        }

        if (isInteger(input) || isIntegerSet(input)) {
            setEnable(btnContainsDisabled);
        } else if (isRange(input)) {
            setEnable(btnContainsDisabled);
            setEnable(btnOverlapsDisabled);
            setEnable(btnEqualsDisabled);
        }
    }

    private void disableAllButtonsConnectedWithInput() {
        setDisable(btnContainsDisabled);
        setDisable(btnOverlapsDisabled);
        setDisable(btnEqualsDisabled);
    }

    private void setDisable(BooleanProperty booleanProperty){
        booleanProperty.setValue(true);
    }

    private void setEnable(BooleanProperty booleanProperty){
        booleanProperty.setValue(true);
    }

    private boolean isInteger(final String input) {
        return input.matches("-?\\d+");
    }

    private boolean isIntegerSet(final String input) {
        return input.matches("\\{-?\\d+(,-?\\d+)+?\\}");
    }

    private boolean isRange(final String input) {
        try {
            new Range(input);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }
}
