package ru.unn.agile.bitarray.viewmodel;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.bitarray.model.BitArray;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    private final StringProperty bitArrayInput = new SimpleStringProperty();
    private final StringProperty inputBit = new SimpleStringProperty();

    private final StringProperty inputStatusField = new SimpleStringProperty();
    private final StringProperty bitArrayField = new SimpleStringProperty();

    private BitArray bitArray;

    public ViewModel() {
        inputBit.set("");
        bitArrayInput.set("");
        bitArrayField.set("");

        inputStatusField.set(Status.WAITING.toString());

        final List<StringProperty> fields = new ArrayList<>() {
            {
                add(bitArrayInput);
                add(inputBit);
            }
        };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public StringProperty bitArrayInputProperty() {
        return bitArrayInput;
    }
    public StringProperty inputBitProperty() {
        return inputBit;
    }
    public StringProperty bitArrayFieldProperty() {
        return bitArrayField;
    }
    public StringProperty inputStatusFieldProperty() {
        return inputStatusField;
    }

    private String getInputStatusField() {
        return inputStatusField.get();
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
            inputStatusField.set(getInputStatusField());
        }
    }
}

enum Status {
    WAITING("Please provide input data"),
    READY("Press 'Calculate' or Enter"),
    BAD_FORMAT("Bad format"),
    SUCCESS("Success");

    private final String name;
    Status(final String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
