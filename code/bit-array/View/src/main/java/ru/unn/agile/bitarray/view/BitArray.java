package ru.unn.agile.bitarray.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ru.unn.agile.bitarray.model.*;
import ru.unn.agile.bitarray.viewmodel.ViewModel;

public class BitArray {
    @FXML
    private ViewModel viewModel;
    @FXML
    private TextField bitArrayInput;
    @FXML
    private TextField inputBit;
    @FXML
    private Button btnCreateBitArray;
    @FXML
    private Button btnSetBit;
    @FXML
    private Button btnUnsetBit;

    @FXML
    void initialize() {
        // Two-way binding hasn't supported by FXML yet, so place it in code-behind
        bitArrayInput.textProperty().bindBidirectional(viewModel.bitArrayInputProperty());
        inputBit.textProperty().bindBidirectional(viewModel.inputBitProperty());

        btnCreateBitArray.setOnAction(event -> viewModel.create());
        btnSetBit.setOnAction(event -> viewModel.setBit());
        btnUnsetBit.setOnAction(event -> viewModel.unsetBit());
    }
}
