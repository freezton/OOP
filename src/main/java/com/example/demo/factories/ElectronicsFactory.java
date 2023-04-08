package com.example.demo.factories;

import com.example.demo.classes.Electronics;
import com.example.demo.classes.Food;
import com.example.demo.classes.Product;
import com.example.demo.controllers.ControllerManager;
import com.example.demo.controllers.ElectronicsController;
import com.example.demo.controllers.FoodController;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class ElectronicsFactory implements AbstractProductFactory{

    @Override
    public Product create(String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcesRoot + fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        stage.showAndWait();
        return ((ElectronicsController)controller).getElectronics();
    }

    @Override
    public void edit(Product electronics, String fxmlFilename, String formTitle) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFilename));
        Stage stage = new Stage();
        Object controller = ControllerManager.getController(formTitle, loader, stage);
        ((ElectronicsController)controller).setElectronicsProperties((Electronics)electronics);
        stage.showAndWait();
    }
}
