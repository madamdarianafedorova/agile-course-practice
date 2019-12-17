package ru.unn.agile.bitarray.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.bitarray.model.BitArray;

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
        assertEquals("", viewModel.bitArrayInputProperty().get());
        assertEquals("", viewModel.inputBitProperty().get());
        assertEquals("", viewModel.bitArrayFieldProperty().get());
    }

    @Test
    public void constructorDefaultSetStatusWaiting() {
        assertEquals(Status.WAITING.toString(), viewModel.statusFieldProperty().get());
    }
}
