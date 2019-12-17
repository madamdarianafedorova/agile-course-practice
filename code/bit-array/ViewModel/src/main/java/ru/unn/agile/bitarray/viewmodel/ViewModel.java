package ru.unn.agile.bitarray.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.bitarray.model.BitArray;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewModel {
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private final StringProperty inputBitArray = new SimpleStringProperty();
    private final StringProperty inputBit = new SimpleStringProperty();

    private final StringProperty fieldInputStatus = new SimpleStringProperty();
    private final StringProperty fieldBitArray = new SimpleStringProperty();

    private String patternInput = "[0,1]+";

    private BitArray bitArray;

    public ViewModel() {
        inputBit.set("");
        inputBitArray.set("");
        fieldBitArray.set("");

        fieldInputStatus.set(Status.WAITING.toString());

        final List<StringProperty> triggers = new ArrayList<>() {
            {
                add(inputBitArray);
                add(inputBit);
            }
        };

        for (StringProperty trigger : triggers) {
            final ValueChangeListener listener = new ValueChangeListener();
            trigger.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty inputBitArrayProperty() {
        return inputBitArray;
    }

    public StringProperty inputBitProperty() {
        return inputBit;
    }

    public StringProperty fieldBitArrayProperty() {
        return fieldBitArray;
    }

    public StringProperty fieldInputStatusProperty() {
        return fieldInputStatus;
    }

    private boolean patternMatch(final String inputString, final String patternString) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }

    private String getFieldInputStatus() {
        Status inputStatus = Status.READY;
        String arrayInputStr = inputBitArray.get();
        boolean matchArrayInput = patternMatch(arrayInputStr, patternInput);
        if (!matchArrayInput) {
            inputStatus = Status.BAD_FORMAT_ARRAY;
            return inputStatus.toString();
        }
        return inputStatus.toString();
    }

    public void create() {

    }

    public void setBit() {

    }

    public void unsetBit() {

    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            fieldInputStatus.set(getFieldInputStatus());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Create BitArray' or Enter"),
    BAD_FORMAT_ARRAY("Incorrect format. Required: 01001 (size is not limited)"),
    BAD_FORMAT_BIT("Incorrect format. Index required."),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
