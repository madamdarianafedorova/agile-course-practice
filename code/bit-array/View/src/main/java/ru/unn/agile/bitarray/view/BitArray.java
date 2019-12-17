package ru.unn.agile.bitarray.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ru.unn.agile.bitarray.viewmodel.ViewModel;

public class BitArray {
    @FXML
    private ViewModel viewModel;

    @FXML
    private TextField bitArrayInput;
    @FXML
    private TextField inputBit;

    @FXML
    private Label labelStatus;

    @FXML
    private Button btnCreateBitArray;
    @FXML
    private Button btnSetBit;
    @FXML
    private Button btnUnsetBit;

    @FXML
    void initialize() {
        labelStatus.textProperty().bind(viewModel.inputStatusFieldProperty());

        bitArrayInput.textProperty().bindBidirectional(viewModel.bitArrayInputProperty());
        inputBit.textProperty().bindBidirectional(viewModel.inputBitProperty());

        btnCreateBitArray.setOnAction(event -> viewModel.create());
        btnSetBit.setOnAction(event -> viewModel.setBit());
        btnUnsetBit.setOnAction(event -> viewModel.unsetBit());
    }
}
