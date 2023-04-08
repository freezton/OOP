package com.example.demo.controllers;

import com.example.demo.classes.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

abstract public class AbstractProductController {

    @FXML
    protected TextField descriptionField;
    @FXML
    protected TextField idField;
    @FXML
    protected TextField nameField;
    @FXML
    protected TextField priceField;

    @FXML
    protected Button cancelButton;

    @FXML
    abstract void onConfirmButtonClick(ActionEvent event);

    protected boolean isCorrectForm() {
        return Validator.isValidInteger(idField.getText()) && Validator.isValidPrice(priceField.getText());
    }

    protected void setCommonProperties(Product product) {
        idField.setText(String.valueOf(product.getId()));
        nameField.setText(product.getName());
        priceField.setText(String.valueOf(product.getPrice()));
        descriptionField.setText(product.getDescription());
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        Stage stage = (Stage)cancelButton.getScene().getWindow();
        stage.close();
    }
}
