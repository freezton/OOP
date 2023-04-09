package com.example.demo.factories;

import com.example.demo.classes.Book;
import com.example.demo.classes.Food;
import com.example.demo.classes.Product;
import com.example.demo.controllers.BookController;
import com.example.demo.controllers.ControllerManager;
import com.example.demo.controllers.FoodController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class FoodFactory implements AbstractProductFactory {

    @Override
    public Food create(String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        stage.showAndWait();
        return ((FoodController)controller).getFood();
    }

    @Override
    public void edit(Product food, String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        ((FoodController)controller).setFoodProperties((Food)food);
        stage.showAndWait();
    }
}
