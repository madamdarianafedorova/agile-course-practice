package ru.unn.agile.crosssections.viewmodel;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import ru.unn.agile.crosssections.model.CrossChecker;
import ru.unn.agile.crosssections.model.Dot;
import ru.unn.agile.crosssections.model.Section;

import java.util.ArrayList;
import java.util.List;

public class ViewModel {
    private final StringProperty fstSectionStartX = new SimpleStringProperty();
    private final StringProperty fstSectionStartY = new SimpleStringProperty();
    private final StringProperty fstSectionFinishX = new SimpleStringProperty();
    private final StringProperty fstSectionFinishY = new SimpleStringProperty();
    private final StringProperty sndSectionStartX = new SimpleStringProperty();
    private final StringProperty sndSectionStartY = new SimpleStringProperty();
    private final StringProperty sndSectionFinishX = new SimpleStringProperty();
    private final StringProperty sndSectionFinishY = new SimpleStringProperty();

    private final StringProperty result = new SimpleStringProperty();
    private final StringProperty status = new SimpleStringProperty();

    private final BooleanProperty checkDisabled = new SimpleBooleanProperty();

    private final List<ValueChangeListener> valueChangedListeners = new ArrayList<>();

    public ViewModel() {
        fstSectionStartX.set("");
        fstSectionStartY.set("");
        fstSectionFinishX.set("");
        fstSectionFinishY.set("");
        sndSectionStartX.set("");
        sndSectionStartY.set("");
        sndSectionFinishX.set("");
        sndSectionFinishY.set("");
        result.set("");
        status.set(Status.WAITING.toString());

        BooleanBinding couldCheck = new BooleanBinding() {
            {
                super.bind(fstSectionStartX, fstSectionStartY, fstSectionFinishX, fstSectionFinishY,
                        sndSectionStartX, sndSectionStartY, sndSectionFinishX, sndSectionFinishY);
            }

            @Override
            protected boolean computeValue() {
                return getNewStatus() == Status.READY;
            }
        };
        checkDisabled.bind(couldCheck.not());

        final List<StringProperty> fields = new ArrayList<StringProperty>() {
            {
                add(fstSectionStartX);
                add(fstSectionStartY);
                add(fstSectionFinishX);
                add(fstSectionFinishY);
                add(sndSectionStartX);
                add(sndSectionStartY);
                add(sndSectionFinishX);
                add(sndSectionFinishY);
            }
        };

        for (StringProperty field : fields) {
            final ValueChangeListener listener = new ValueChangeListener();
            field.addListener(listener);
            valueChangedListeners.add(listener);
        }
    }

    public void check() {
        if (checkDisabled.get()) {
            return;
        }

        Dot a = new Dot(fstSectionStartX.get(), fstSectionStartY.get());
        Dot b = new Dot(fstSectionFinishX.get(), fstSectionFinishY.get());
        Dot c = new Dot(sndSectionStartX.get(), sndSectionStartY.get());
        Dot d = new Dot(sndSectionFinishX.get(), sndSectionFinishY.get());

        try {
            Section section1 = new Section(a, b);
            Section section2 = new Section(c, d);
            result.set(CrossChecker.check(section1, section2) ? "Crossed" : "Don't Crossed");
            status.set(Status.SUCCESS.toString());
        } catch (Exception ex) {
            status.set(ex.getMessage());
        }
    }

    public BooleanProperty checkDisabledProperty() {
        return checkDisabled;
    }

    public final boolean isCheckDisabled() {
        return checkDisabled.get();
    }

    public StringProperty fstSectionStartXProperty() {
        return fstSectionStartX;
    }

    public StringProperty fstSectionStartYProperty() {
        return fstSectionStartY;
    }

    public StringProperty fstSectionFinishXProperty() {
        return fstSectionFinishX;
    }

    public StringProperty fstSectionFinishYProperty() {
        return fstSectionFinishY;
    }

    public StringProperty sndSectionStartXProperty() {
        return sndSectionStartX;
    }

    public StringProperty sndSectionStartYProperty() {
        return sndSectionStartY;
    }

    public StringProperty sndSectionFinishXProperty() {
        return sndSectionFinishX;
    }

    public StringProperty sndSectionFinishYProperty() {
        return sndSectionFinishY;
    }

    public StringProperty resultProperty() {
        return result;
    }

    public final String getResult() {
        return result.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public final String getStatus() {
        return status.get();
    }

    private Status getNewStatus() {
        Status newStatus = Status.READY;
        if (fstSectionStartX.get().isEmpty() || fstSectionStartY.get().isEmpty()
                || fstSectionFinishX.get().isEmpty() || fstSectionFinishY.get().isEmpty()
                || sndSectionStartX.get().isEmpty() || sndSectionStartY.get().isEmpty()
                || sndSectionFinishX.get().isEmpty() || sndSectionFinishY.get().isEmpty()) {
            newStatus = Status.WAITING;
        }
        newStatus = isIncorrectInput(fstSectionStartX) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(fstSectionStartY) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(fstSectionFinishX) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(fstSectionFinishY) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(sndSectionStartX) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(fstSectionStartX) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(sndSectionStartY) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(sndSectionFinishX) ? Status.BAD_FORMAT : newStatus;
        newStatus = isIncorrectInput(sndSectionFinishY) ? Status.BAD_FORMAT : newStatus;

        return newStatus;
    }

    private boolean isIncorrectInput(final StringProperty property) {
        try {
            if (!property.get().isEmpty()) {
                Integer.parseInt(property.get());
            }
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    private class ValueChangeListener implements ChangeListener<String> {
        @Override
        public void changed(final ObservableValue<? extends String> observable,
                            final String oldValue, final String newValue) {
            status.set(getNewStatus().toString());
        }
    }
}

enum Status {
    WAITING("Please enter data"),
    READY("Press 'Check' or Enter"),
    BAD_FORMAT("Incorrect input"),
    SUCCESS("Success");

    private final String name;

    Status(final String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
