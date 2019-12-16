package ru.unn.agile.currencyconverter.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.unn.agile.currencyconverter.model.CurrencyPair;

import static org.junit.Assert.*;

public class CurrencyConverterViewModelTests {

    private CurrencyConverterViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new CurrencyConverterViewModel();
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    @Test
    public void canSetEmptyStringInTextFieldsByDefault() {
        assertEquals("", viewModel.getInputCurrency().get());
        assertEquals("", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canSetConvertButtonDisabledByDefault() {
        assertTrue(viewModel.isConvertButtonDisabled().get());
    }

    @Test
    public void canSetListFromPairsByDefault() {
        assertEquals(CurrencyPair.RUBLE_TO_DOLLAR, viewModel.getCurrencyPair().get());
    }

    @Test
    public void isLengthTypesListHasCorrectValues() {
        assertEquals(CurrencyPair.values()[5], viewModel.getCurrencyPairs().get(5));
    }

    @Test
    public void canSetConvertButtonEnabledAfterInput() {
        viewModel.getInputCurrency().set("1");

        assertFalse(viewModel.isConvertButtonDisabled().get());
    }

    @Test
    public void canSetConvertButtonDisabledAfterClearInput() {
        viewModel.getInputCurrency().set("1");
        viewModel.getInputCurrency().set("");

        assertTrue(viewModel.isConvertButtonDisabled().get());
    }

    @Test
    public void canShowErrorAfterInvalidInput() {
        viewModel.getInputCurrency().set("one");

        assertEquals("Incorrect Currency", viewModel.getError().get());
    }

    @Test
    public void canClearErrorAfterValidInput() {
        viewModel.getInputCurrency().set("one");
        viewModel.getInputCurrency().set("3");

        assertEquals("", viewModel.getError().get());
    }

    @Test
    public void canSetConvertButtonDisabledInvalidInput() {
        viewModel.getInputCurrency().set("one");

        assertTrue(viewModel.isConvertButtonDisabled().get());
    }

    @Test
    public void canClearErrorByDefault() {
        assertEquals("", viewModel.getError().get());
    }

    @Test
    public void canClearErrorAfterClearInput() {
        viewModel.getInputCurrency().set("one");
        viewModel.getInputCurrency().set("");

        assertEquals("", viewModel.getError().get());
    }

    @Test
    public void canSetEuroToDollarInCurrencyPair() {
        viewModel.getCurrencyPair().set(CurrencyPair.EURO_TO_DOLLAR);

        assertEquals(CurrencyPair.EURO_TO_DOLLAR, viewModel.getCurrencyPair().get());
    }

    @Test
    public void canSetDollarToEuroInCurrencyPair() {
        viewModel.getCurrencyPair().set(CurrencyPair.DOLLAR_TO_EURO);

        assertEquals(CurrencyPair.DOLLAR_TO_EURO, viewModel.getCurrencyPair().get());
    }

    @Test
    public void canSetDollarToRubleInCurrencyPair() {
        viewModel.getCurrencyPair().set(CurrencyPair.DOLLAR_TO_RUBLE);

        assertEquals(CurrencyPair.DOLLAR_TO_RUBLE, viewModel.getCurrencyPair().get());
    }

    @Test
    public void canSetEuroToRubleInCurrencyPair() {
        viewModel.getCurrencyPair().set(CurrencyPair.EURO_TO_RUBLE);

        assertEquals(CurrencyPair.EURO_TO_RUBLE, viewModel.getCurrencyPair().get());
    }

    @Test
    public void canSetRubleToEuroInCurrencyPair() {
        viewModel.getCurrencyPair().set(CurrencyPair.RUBLE_TO_EURO);

        assertEquals(CurrencyPair.RUBLE_TO_EURO, viewModel.getCurrencyPair().get());
    }

    @Test
    public void canConvert() {
        viewModel.getInputCurrency().set("10000");

        viewModel.convert();

        assertEquals("160", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canConvertEuroToRuble() {
        viewModel.getInputCurrency().set("1");

        viewModel.getCurrencyPair().set(CurrencyPair.EURO_TO_RUBLE);
        viewModel.convert();

        assertEquals("69.86", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canConvertDollarToRuble() {
        viewModel.getInputCurrency().set("10");

        viewModel.getCurrencyPair().set(CurrencyPair.DOLLAR_TO_RUBLE);
        viewModel.convert();

        assertEquals("625.5", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canConvertRubleToEuro() {
        viewModel.getInputCurrency().set("10000");

        viewModel.getCurrencyPair().set(CurrencyPair.RUBLE_TO_EURO);
        viewModel.convert();

        assertEquals("140", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canConvertDollarToEuro() {
        viewModel.getInputCurrency().set("10000");

        viewModel.getCurrencyPair().set(CurrencyPair.DOLLAR_TO_EURO);
        viewModel.convert();

        assertEquals("8900", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canConvertEuroToDollar() {
        viewModel.getInputCurrency().set("1");

        viewModel.getCurrencyPair().set(CurrencyPair.EURO_TO_DOLLAR);
        viewModel.convert();

        assertEquals("1.12", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canClearOutputAfterChangeInput() {
        viewModel.getInputCurrency().set("1");

        viewModel.convert();
        viewModel.getInputCurrency().set("2");

        assertEquals("", viewModel.getOutputCurrency().get());
    }

    @Test
    public void canClearOutputAfterChangeCurrencyPair() {
        viewModel.getInputCurrency().set("2");

        viewModel.convert();
        viewModel.getCurrencyPair().set(CurrencyPair.RUBLE_TO_EURO);

        assertEquals("", viewModel.getOutputCurrency().get());
    }
}
