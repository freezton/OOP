package com.example.demo.controllers;

import com.example.demo.classes.Book;
import com.example.demo.classes.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FoodController extends AbstractProductController {

    private Food food;
    @FXML
    private TextField caloriesField;
    @FXML
    private TextField expirationDateField;

    @Override
    void onConfirmButtonClick(ActionEvent event) {
        if (isCorrectForm()) {
            if (food == null) {
                if (descriptionField.getText().isBlank())
                    descriptionField.setText("None");
                food = new Food(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Double.parseDouble(priceField.getText()),
                        descriptionField.getText(),
                        Integer.parseInt(expirationDateField.getText()),
                        Integer.parseInt(caloriesField.getText())
                );
            } else {
                food.setId(Integer.parseInt(idField.getText()));
                food.setName(nameField.getText());
                food.setPrice(Double.parseDouble(priceField.getText()));
                food.setDescription(descriptionField.getText());
                food.setExpirationDate(Integer.parseInt(expirationDateField.getText()));
                food.setCalories(Integer.parseInt(caloriesField.getText()));
            }
            Stage stage = (Stage)cancelButton.getScene().getWindow();
            stage.close();
        }
    }

    public void setFoodProperties(Food food) {
        setCommonProperties(food);
        expirationDateField.setText(String.valueOf(food.getExpirationDate()));
        caloriesField.setText(String.valueOf(food.getCalories()));
    }

    public Food getFood() {
        return food;
    }


    @Override
    protected boolean isCorrectForm() {
        return super.isCorrectForm() && Validator.isValidInteger(expirationDateField.getText()) && Validator.isValidInteger(caloriesField.getText());
    }
}
