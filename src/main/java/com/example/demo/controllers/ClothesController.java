package com.example.demo.controllers;

import com.example.demo.classes.Book;
import com.example.demo.classes.Clothes;
import com.example.demo.enums.ClothesType;
import com.example.demo.enums.Genre;
import com.example.demo.enums.Material;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClothesController extends AbstractProductController {

    private Clothes clothes;
    @FXML
    private ComboBox<Material> materialField;
    @FXML
    private ComboBox<ClothesType> typeField;
    @FXML
    private TextField sizeField;

    public void initialize() {
        ObservableList<ClothesType> types = FXCollections.observableArrayList(ClothesType.values());
        typeField.setItems(types);
        typeField.setValue(ClothesType.T_SHIRT);
        ObservableList<Material> materials = FXCollections.observableArrayList(Material.values());
        materialField.setItems(materials);
        materialField.setValue(Material.COTTON);
    }
    @Override
    void onConfirmButtonClick(ActionEvent event) {
        if (isCorrectForm()) {
            if (clothes == null) {
                if (descriptionField.getText().isBlank())
                    descriptionField.setText("None");
                clothes = new Clothes(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        descriptionField.getText(),
                        Integer.parseInt(sizeField.getText()),
                        materialField.getValue(),
                        typeField.getValue()
                );
            } else {
                clothes.setId(Integer.parseInt(idField.getText()));
                clothes.setName(nameField.getText());
                clothes.setPrice(Double.parseDouble(priceField.getText()));
                clothes.setDescription(descriptionField.getText());
                clothes.setMaterial(materialField.getValue());
                clothes.setType(typeField.getValue());
                clothes.setSize(Integer.parseInt(sizeField.getText()));
            }
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            stage.close();
        } else {
            //show error form
        }
    }

    @Override
    protected boolean isCorrectForm() {
        return super.isCorrectForm() && Validator.isValidInteger(sizeField.getText());
    }

    public void setClothesProperties(Clothes clothes) {
        setCommonProperties(clothes);
        sizeField.setText(String.valueOf(clothes.getSize()));
        typeField.setValue(clothes.getType());
        materialField.setValue(clothes.getMaterial());
    }

    public Clothes getClothes() {
        return clothes;
    }
}
