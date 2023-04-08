package com.example.demo.factories;

import com.example.demo.classes.Book;
import com.example.demo.classes.Clothes;
import com.example.demo.classes.Product;
import com.example.demo.controllers.BookController;
import com.example.demo.controllers.ClothesController;
import com.example.demo.controllers.ControllerManager;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ClothesFactory implements AbstractProductFactory {

    @Override
    public Clothes create(String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        stage.showAndWait();
        return ((ClothesController)controller).getClothes();
    }

    @Override
    public void edit(Product clothes, String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        ((ClothesController)controller).setClothesProperties((Clothes)clothes);
        stage.showAndWait();
    }
}
