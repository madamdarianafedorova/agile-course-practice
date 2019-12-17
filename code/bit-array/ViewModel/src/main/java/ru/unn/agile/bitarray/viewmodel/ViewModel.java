package ru.unn.agile.bitarray.viewmodel;

import javafx.beans.property.*;

public class ViewModel {
    private final StringProperty bitArrayInput = new SimpleStringProperty();
    private final StringProperty inputBit = new SimpleStringProperty();

    private final StringProperty statusField = new SimpleStringProperty();
    private final StringProperty bitArrayField = new SimpleStringProperty();

    public ViewModel() {
        inputBit.set("");
        bitArrayInput.set("");
        bitArrayField.set("");

        statusField.set(Status.WAITING.toString());
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
    public StringProperty statusFieldProperty() {
        return statusField;
    }

    public void create() {

    }

    public void setBit() {

    }

    public void unsetBit() {

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
