package ru.unn.agile.range.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ViewModelTests {

    private ViewModel viewModel;
    private final String yes = "Yes";
    private final String no = "No";

    @Before
    public void setUp() {
        setViewModel(new ViewModel());
    }

    @After
    public void tearDown() {
        viewModel = null;
    }

    protected void setViewModel(final ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Test
    public void hasContainsButtonIsDisabledByDefault() {
        assertTrue(viewModel.isContainsButtonDisabled().get());
    }

    @Test
    public void hasEqualsButtonIsDisabledByDefault() {
        assertTrue(viewModel.isEqualsButtonDisabled().get());
    }

    @Test
    public void hasOverlapsButtonIsDisabledByDefault() {
        assertTrue(viewModel.isOverlapsButtonDisabled().get());
    }

    @Test
    public void hasGetAllPointsButtonIsDisabledByDefault() {
        assertTrue(viewModel.isGetAllPointsButtonDisabled().get());
    }

    @Test
    public void hasGetEndPointsButtonIsDisabledByDefault() {
        assertTrue(viewModel.isGetEndPointsButtonDisabled().get());
    }

    @Test
    public void hasContainsButtonIsDisabledWithIncorrectRange() {
        viewModel.getTxtRange().setValue("word");
        assertTrue(viewModel.isContainsButtonDisabled().get());
    }

    @Test
    public void hasEqualsButtonIsDisabledWithIncorrectRange() {
        viewModel.getTxtRange().setValue("word");
        assertTrue(viewModel.isEqualsButtonDisabled().get());
    }

    @Test
    public void hasOverlapsButtonIsDisabledWithIncorrectRange() {
        viewModel.getTxtRange().setValue("word");
        assertTrue(viewModel.isOverlapsButtonDisabled().get());
    }

    @Test
    public void hasGetAllPointsButtonIsDisabledWithIncorrectRange() {
        viewModel.getTxtRange().setValue("word");
        assertTrue(viewModel.isGetAllPointsButtonDisabled().get());
    }

    @Test
    public void hasGetEndPointsButtonIsDisabledWithIncorrectRange() {
        viewModel.getTxtRange().setValue("word");
        assertTrue(viewModel.isGetEndPointsButtonDisabled().get());
    }

    @Test
    public void hasGetAllPointsButtonIsEnabledWithCorrectRange() {
        viewModel.getTxtRange().setValue("[1,2]");
        assertFalse(viewModel.isGetAllPointsButtonDisabled().get());
    }

    @Test
    public void hasGetEndPointsButtonIsEnabledWithCorrectRange() {
        viewModel.getTxtRange().setValue("(-1,3)");
        assertFalse(viewModel.isGetEndPointsButtonDisabled().get());
    }

    @Test
    public void hasEqualsButtonIsDisabledWithIncorrectInput() {
        viewModel.getTxtRange().setValue("(-1,3)");
        viewModel.getTxtInput().setValue("word");
        assertTrue(viewModel.isEqualsButtonDisabled().get());
    }

    @Test
    public void hasOverlapsButtonIsDisabledWithIncorrectInput() {
        viewModel.getTxtRange().setValue("(-1,3)");
        viewModel.getTxtInput().setValue("word");
        assertTrue(viewModel.isOverlapsButtonDisabled().get());
    }

    @Test
    public void hasContainsButtonIsDisabledWithIncorrectInput() {
        viewModel.getTxtRange().setValue("(-1,3)");
        viewModel.getTxtInput().setValue("word");
        assertTrue(viewModel.isContainsButtonDisabled().get());
    }

    @Test
    public void hasEqualsButtonIsDisabledWithIncorrectRangeAndCorrectInput() {
        viewModel.getTxtRange().setValue("word");
        viewModel.getTxtInput().setValue("(-1,3)");
        assertTrue(viewModel.isEqualsButtonDisabled().get());
    }

    @Test
    public void hasOverlapsButtonIsDisabledWithIncorrectRangeAndCorrectInput() {
        viewModel.getTxtRange().setValue("word");
        viewModel.getTxtInput().setValue("(-1,3)");
        assertTrue(viewModel.isOverlapsButtonDisabled().get());
    }

    @Test
    public void hasContainsButtonIsDisabledWithIncorrectRangeAndCorrectInput() {
        viewModel.getTxtRange().setValue("word");
        viewModel.getTxtInput().setValue("(-1,3)");
        assertTrue(viewModel.isContainsButtonDisabled().get());
    }

    @Test
    public void hasGetAllPointsButtonReturnsCorrectResult() {
        viewModel.getTxtRange().setValue("[1,4]");

        viewModel.getAllPoints();

        assertEquals(Arrays.toString(new int[]{1, 2, 3, 4}), viewModel.getTxtResult().get());
    }

    @Test
    public void hasGetEndPointsButtonReturnsCorrectResult() {
        viewModel.getTxtRange().setValue("[1,4]");

        viewModel.getEndPoints();

        assertEquals(Arrays.toString(new int[]{1, 4}), viewModel.getTxtResult().get());
    }

    @Test
    public void overlapsWithRangeWhenOverlapsIsPresent() {
        viewModel.getTxtRange().setValue("[11,15]");
        viewModel.getTxtInput().setValue("(9,19)");

        viewModel.overlapsRange();

        assertEquals(yes, viewModel.getTxtResult().get());
    }

    @Test
    public void cantOverlapsWithRangeWhenOverlapsIsMissing() {
        viewModel.getTxtRange().setValue("[11,15]");
        viewModel.getTxtInput().setValue("(5,9)");

        viewModel.overlapsRange();

        assertEquals(no, viewModel.getTxtResult().get());
    }

    @Test
    public void canEqualsWithRangeWithNonInclusiveBoundaries() {
        viewModel.getTxtRange().setValue("[11,20]");
        viewModel.getTxtInput().setValue("(10,21)");

        viewModel.equalsRange();

        assertEquals(yes, viewModel.getTxtResult().get());
    }

    @Test
    public void cantEqualsWithAnotherRange() {
        viewModel.getTxtRange().setValue("[11,20]");
        viewModel.getTxtInput().setValue("(11,20)");

        viewModel.equalsRange();

        assertEquals(no, viewModel.getTxtResult().get());
    }

    @Test
    public void containsIntegerValue() {
        viewModel.getTxtRange().setValue("[10,11)");
        viewModel.getTxtInput().setValue("10");

        viewModel.containsInput();

        assertEquals(yes, viewModel.getTxtResult().get());
    }

    @Test
    public void notContainsIntegerValue() {
        viewModel.getTxtRange().setValue("[10,11)");
        viewModel.getTxtInput().setValue("11");

        viewModel.containsInput();

        assertEquals(no, viewModel.getTxtResult().get());
    }

    @Test
    public void containsSetOfIntegers() {
        viewModel.getTxtRange().setValue("[10,15)");
        viewModel.getTxtInput().setValue("{10,11,13}");

        viewModel.containsInput();

        assertEquals(yes, viewModel.getTxtResult().get());
    }

    @Test
    public void notContainsSetWhenOneElementContains() {
        viewModel.getTxtRange().setValue("[10,15)");
        viewModel.getTxtInput().setValue("{10,16}");

        viewModel.containsInput();

        assertEquals(no, viewModel.getTxtResult().get());
    }

    @Test
    public void canContainLesserRange() {
        viewModel.getTxtRange().setValue("[10,15)");
        viewModel.getTxtInput().setValue("[11,13]");

        viewModel.containsInput();

        assertEquals(yes, viewModel.getTxtResult().get());
    }

    @Test
    public void cantContainLargerRange() {
        viewModel.getTxtRange().setValue("[10,15)");
        viewModel.getTxtInput().setValue("[11,16]");

        viewModel.containsInput();
        assertEquals(no, viewModel.getTxtResult().get());
    }


}
