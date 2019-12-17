package ru.unn.agile.bitarray.viewmodel;

import javafx.fxml.FXML;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new ViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    // Constructor tests
    @Test
    public void constructorDefaultSetAllStringsEmpty() {
        assertEquals("", viewModel.inputBitArrayProperty().get());
        assertEquals("", viewModel.inputBitProperty().get());
        assertEquals("", viewModel.fieldBitArrayProperty().get());
    }

    @Test
    public void constructorDefaultSetStatusWaiting() {
        assertEquals(Status.WAITING.toString(), viewModel.fieldInputStatusProperty().get());
    }

    // Status field tests - getInputStatusField
    @Test
    public void statusFieldOnInputFieldIsEmptyIsWaiting() {
        viewModel.inputBitArrayProperty().set("");

        assertEquals(Status.WAITING.toString(), viewModel.fieldInputStatusProperty().get());
    }

    @Test
    public void statusFieldOnIncorrectBitArrayInputIsBadArrayFormat() {
        viewModel.inputBitArrayProperty().set("42");

        assertEquals(Status.BAD_FORMAT_ARRAY.toString(), viewModel.fieldInputStatusProperty().get());
    }

    @Test
    public void statusFieldOnCorrectInputIsReady() {
        viewModel.inputBitArrayProperty().set("01");

        assertEquals(Status.READY.toString(), viewModel.fieldInputStatusProperty().get());
    }

    @Test
    public void statusFieldAfterCreatingIsSUCCESS() {
        viewModel.inputBitArrayProperty().set("01");
        viewModel.create();

        assertEquals(Status.SUCCESS.toString(), viewModel.fieldInputStatusProperty().get());
    }

    // BitArray field tests
    @Test
    public void bitArrayFieldAfterCreatingSameAsInput() {
        String input = "0101";
        viewModel.inputBitArrayProperty().set(input);
        viewModel.create();

        assertEquals(input, viewModel.fieldBitArrayProperty().get().toString());
    }

    @Test
    public void bitArrayFieldAfterCreateOnIncorrectInputIsEmpty() {
        String incorrect_input = "42";
        viewModel.inputBitArrayProperty().set(incorrect_input);
        viewModel.create();

        assertEquals("", viewModel.fieldBitArrayProperty().get().toString());
    }


}
