package com.example.demo.controllers;

import com.example.demo.classes.Electronics;
import com.example.demo.enums.ElectronicsType;
import com.example.demo.enums.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ElectronicsController extends AbstractProductController {

    public Electronics electronics;
    @FXML
    private TextField modelField;
    @FXML
    private ComboBox<ElectronicsType> typeField;

    public void initialize() {
        ObservableList<ElectronicsType> types = FXCollections.observableArrayList(ElectronicsType.values());
        typeField.setItems(types);
        typeField.setValue(ElectronicsType.DESKTOP);
    }

    @Override
    void onConfirmButtonClick(ActionEvent event) {
        if (isCorrectForm()) {
            if (descriptionField.getText().isBlank())
                descriptionField.setText("None");
            if (electronics == null) {

                electronics = new Electronics(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        descriptionField.getText(),
                        typeField.getValue(),
                        modelField.getText()
                );
            } else {
                electronics.setId(Integer.parseInt(idField.getText()));
                electronics.setName(nameField.getText());
                electronics.setPrice(Double.parseDouble(priceField.getText()));
                electronics.setDescription(descriptionField.getText());
                electronics.setModel(modelField.getText());
                electronics.setType(typeField.getValue());
            }
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            stage.close();
        } else {
            // show error message
        }
    }

    public void setElectronicsProperties(Electronics electronics) {
        setCommonProperties(electronics);
        typeField.setValue(electronics.getType());
        modelField.setText(electronics.getModel());
        this.electronics = electronics;
    }

    public Electronics getElectronics() {
        return electronics;
    }
}
