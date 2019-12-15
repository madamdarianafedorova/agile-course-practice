package ru.unn.agile.arabicromanconverter.infrastructure;

import ru.unn.agile.arabicromanconverter.viewmodel.ViewModel;
import ru.unn.agile.arabicromanconverter.viewmodel.ArabicRomanConverterViewModelTests;

public class ViewModelWithTxtLoggerTests extends ArabicRomanConverterViewModelTests {
    @Override
    public void setUp() {
        TxtLogger realLogger =
            new TxtLogger("./ViewModel_with_TxtLogger_Tests-lab3.log");
        super.setExternalViewModel(new ViewModel(realLogger));
    }
}
