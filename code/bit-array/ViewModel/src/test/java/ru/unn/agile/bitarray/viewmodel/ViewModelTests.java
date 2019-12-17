package ru.unn.agile.bitarray.viewmodel;

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

    // Input field tests - getInputStatusField
    @Test
    public void inputFieldIsEmptyStatusIsWaiting() {
        viewModel.inputBitArrayProperty().set("");
        assertEquals(Status.WAITING.toString(), viewModel.fieldInputStatusProperty().get());
    }
}
